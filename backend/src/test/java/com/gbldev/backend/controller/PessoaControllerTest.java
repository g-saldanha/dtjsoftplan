package com.gbldev.backend.controller;

import java.lang.reflect.Type;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gbldev.backend.dto.PessoaDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private Gson gson;

	@BeforeEach
	void setUp() {
		this.gson = new Gson();
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	void obterPessoas() throws Exception {
		final var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/pessoa")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final var pessoas = mvcResult.getResponse().getContentAsString();
		final Type listType = new TypeToken<List<PessoaDTO>>() {
		}.getType();

		final List<PessoaDTO> list = this.gson.fromJson(pessoas, listType);
		Assertions.assertThat(list).doesNotContainNull();
		Assertions.assertThat(list.size()).isEqualTo(3);
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	void cadastrarPessoa() {
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	void atualizaPessoa() {
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	void removePessoa() {
	}
}