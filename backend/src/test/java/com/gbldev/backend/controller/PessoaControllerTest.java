package com.gbldev.backend.controller;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.enums.Sexo;
import com.gbldev.backend.exception.PessoaExceptionMessages;
import com.gbldev.backend.utils.PessoaMessages;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PessoaControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private Gson gson;

	private PessoaDTO pessoaDTOTeste;

	@BeforeEach
	void setUp() {
		this.gson = new Gson();
		final Calendar cal = Calendar.getInstance();
		cal.set(1994, Calendar.NOVEMBER, 4);
		this.pessoaDTOTeste = PessoaDTO.builder()
				.nome("Pessoa Teste")
				.cpf("78546227012")
				.dataNascimento(cal.getTime())
				.sexo(Sexo.MASCULINO)
				.email("teste@softplan.com.br")
				.nacionalidade("BRASILEIRO")
				.naturalidade("IMAGINOLANDIA")
				.build();
	}

	@Test
	@DisplayName("Deve trazer a lista de pessoas")
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
	@DisplayName("Deve Cadastrar uma pessoa válida")
	void cadastrarPessoa() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(this.pessoaDTOTeste.toJson()))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(PessoaMessages.CADASTRO_SUCESSO));
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Não deve cadastrar uma pessoa inválida")
	void cadastrarPessoaInvalida() throws Exception {
		this.pessoaDTOTeste.setNome("");
		final var content = this.pessoaDTOTeste.toJson();
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(result ->
						Assertions.assertThat(result.getResponse().getContentAsString()).contains(PessoaExceptionMessages.NOME_OBRIGATORIO)
				)
				.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Não deve cadastrar uma pessoa existente")
	void cadastrarPessoaExistente() throws Exception {
		this.pessoaDTOTeste.setCpf("96773550076");
		final var content = this.pessoaDTOTeste.toJson();
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.post("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(result ->
						Assertions.assertThat(result.getResponse().getContentAsString()).contains(PessoaExceptionMessages.JA_EXISTE)
				)
				.andExpect(MockMvcResultMatchers.status().isConflict());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Deve atualizar uma pessoa válida")
	void atualizaPessoa() throws Exception {
		this.pessoaDTOTeste.setCpf("96773550076");
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.put("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(this.pessoaDTOTeste.toJson()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(result -> Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(PessoaMessages.ATUALIZADO_SUCESSO));
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Deve não atualizar uma pessoa inválida")
	void atualizaPessoaInvalida() throws Exception {
		this.pessoaDTOTeste.setCpf("96773550076");
		this.pessoaDTOTeste.setEmail("aaa");
		final var content = this.pessoaDTOTeste.toJson();
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.put("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(result ->
						Assertions.assertThat(result.getResponse().getContentAsString()).contains(PessoaExceptionMessages.EMAIL_INVALIDO)
				)
				.andExpect(MockMvcResultMatchers.status().isNotAcceptable());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Deve não atualizar uma pessoa inexistente")
	void atualizaPessoaInexistente() throws Exception {
		this.pessoaDTOTeste.setCpf("62744828068");
		final var content = this.pessoaDTOTeste.toJson();
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.put("/pessoa")
						.contentType(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(result ->
						Assertions.assertThat(result.getResponse().getContentAsString()).contains(PessoaExceptionMessages.NAO_EXISTE)
				)
				.andExpect(MockMvcResultMatchers.status().isConflict());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Deve remover uma pessoa")
	void removePessoa() throws Exception {
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.delete("/pessoa/%s".formatted("86780356093")))
				.andExpect(result ->
						Assertions.assertThat(result.getResponse().getContentAsString()).contains(PessoaMessages.DELETADO_SUCESSO)
				)
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "USER")
	@DisplayName("Deve não remover uma pessoa inexistente")
	void removePessoaInexistente() throws Exception {
		final var cpfInexistente = "44266141041";
		this.mockMvc
				.perform(MockMvcRequestBuilders
						.delete("/pessoa/%s".formatted(cpfInexistente)))
				.andExpect(result ->
						Assertions.assertThat(result.getResponse().getContentAsString()).contains(PessoaExceptionMessages.NAO_EXISTE)
				)
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}