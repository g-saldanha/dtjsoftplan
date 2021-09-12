package com.gbldev.backend.service;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;

import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.enums.Sexo;

class PessoaServiceTest {

	private final PessoaService pessoaService = new PessoaService();
	private PessoaDTO pessoaDTOTeste;

	@BeforeEach
	void setUp() {
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
}