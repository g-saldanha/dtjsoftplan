package com.gbldev.backend.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gbldev.backend.dto.LoginDTO;
import com.google.gson.Gson;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	void setUp() {
		this.gson = new Gson();
	}

	@Test
	void cadastrarUsuario() {
	}

	@Test
	void logarUsuario() {
	}

	@Test
	@DisplayName("Deve retornar o login")
	void logar() throws Exception {
		final var login = LoginDTO.builder().login("admin").senha("admin").build();
		final String loginJson = this.gson.toJson(login);
		final var mvcResult = this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/usuario/logar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		final LoginDTO loginDTO = this.gson.fromJson(mvcResult.getResponse().getContentAsString(), LoginDTO.class);
		Assertions.assertThat(loginDTO.getLogin()).isEqualTo(login.getLogin());
		Assertions.assertThat(loginDTO.getSenha()).isEqualTo("$2a$10$ss15XovK3uhWCtu.TCI.gutmRg6EJIgVKAd84X/U/6rMH2bQCbFUa");
	}

	@Test
	@DisplayName("Não deve retornar o login inexistente")
	void naoLogarUserInexistente() throws Exception {
		final var login = LoginDTO.builder().login("naosouumlogin").senha("naosouumasenha").build();
		final String loginJson = this.gson.toJson(login);
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/usuario/logar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginJson))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> {
					Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo("404 NOT_FOUND \"Usuário não encontrado!\"");
				});
	}

	@Test
	@DisplayName("Não deve retornar o login com senha errada")
	void naoLogarUserSenhaErrada() throws Exception {
		final var login = LoginDTO.builder().login("admin").senha("naosouumasenha").build();
		final String loginJson = this.gson.toJson(login);
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/usuario/logar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginJson))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> {
					Assertions.assertThat(result.getResponse().getContentAsString()).contains("Usuário e senha não encontrado");
				});
	}

	@Test
	@DisplayName("Não deve retornar o login com username errado")
		//	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	void naoLogarUserLoginErrado() throws Exception {
		final var login = LoginDTO.builder().login("admin").senha("naosouumasenha").build();
		final String loginJson = this.gson.toJson(login);
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/usuario/logar")
						.contentType(MediaType.APPLICATION_JSON)
						.content(loginJson))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(result -> {
					Assertions.assertThat(result.getResponse().getContentAsString()).contains("Usuário e senha não encontrado");
				});
	}
}