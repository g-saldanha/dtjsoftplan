package com.gbldev.backend.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.gbldev.backend.controller.SourceController.GITHUB_REP_GABRIEL;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class SourceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
	}

	@Test
	@DisplayName("Deve retornar o repositório git do projeto")
	void obterRepoGithub() throws Exception {
		final var mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/source")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final var githubRepo = mvcResult.getResponse().getContentAsString();
		Assertions.assertThat(githubRepo).isEqualTo(GITHUB_REP_GABRIEL);
	}
}