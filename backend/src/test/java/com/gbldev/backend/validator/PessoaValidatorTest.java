package com.gbldev.backend.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.enums.Sexo;
import com.gbldev.backend.exception.PessoaException;
import com.gbldev.backend.exception.PessoaExceptionMessages;

class PessoaValidatorTest {

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

	@Test
	@DisplayName("Não deve validar sem nome")
	void cadastroSemNome() {
		this.pessoaDTOTeste.setNome(null);
		final var pessoaException = Assertions.assertThrows(PessoaException.class, () -> PessoaValidator.validarDados(this.pessoaDTOTeste));
		Assertions.assertTrue(Objects.requireNonNull(pessoaException.getMessage()).contains(PessoaExceptionMessages.NOME_OBRIGATORIO));
	}

	@Test
	@DisplayName("Não deve validar com cpf invalido")
	void cadastroComCpfInvalido() {
		this.pessoaDTOTeste.setCpf("00000099911");
		final var pessoaException = Assertions.assertThrows(PessoaException.class, () -> PessoaValidator.validarDados(this.pessoaDTOTeste));
		Assertions.assertTrue(Objects.requireNonNull(pessoaException.getMessage()).contains(PessoaExceptionMessages.CPF_INVALIDO));
	}

	@Test
	@DisplayName("Não deve validar com email não valido")
	void cadastroComEmailInvalido() {
		this.pessoaDTOTeste.setEmail("issonaoehumemail");
		final var pessoaException = Assertions.assertThrows(PessoaException.class, () -> PessoaValidator.validarDados(this.pessoaDTOTeste));
		Assertions.assertTrue(Objects.requireNonNull(pessoaException.getMessage()).contains(PessoaExceptionMessages.EMAIL_INVALIDO));
	}

	@Test
	@DisplayName("Não deve validar Data de nascimento nula")
	void cadastroComDataDeNascimentoRequerida() {
		this.pessoaDTOTeste.setDataNascimento(null);
		final var pessoaException = Assertions.assertThrows(PessoaException.class, () -> PessoaValidator.validarDados(this.pessoaDTOTeste));
		Assertions.assertTrue(Objects.requireNonNull(pessoaException.getMessage()).contains(PessoaExceptionMessages.DATA_NASCIMENTO_OBRIGATORIA));
	}

	@Test
	@DisplayName("Não deve validar Data de nascimento menor que 10 anos")
	void cadastroComDataDeNascimentoMenor() {
		this.pessoaDTOTeste.setDataNascimento(new Date());
		final var pessoaException = Assertions.assertThrows(PessoaException.class, () -> PessoaValidator.validarDados(this.pessoaDTOTeste));
		Assertions.assertTrue(Objects.requireNonNull(pessoaException.getMessage()).contains(PessoaExceptionMessages.DATA_NASCIMENTO_INVALIDA));
	}
}