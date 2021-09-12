package com.gbldev.backend.validator;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.exception.PessoaException;
import com.gbldev.backend.exception.PessoaExceptionMessages;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PessoaValidator {

	public static void validarDados(final PessoaDTO dto) throws PessoaException {
		validarCpf(dto.getCpf());
		validarNome(dto.getNome());
		validarEmail(dto.getEmail());
		validarData(dto.getDataNascimento());
	}

	private static void validarData(final Date dataNascimento) throws PessoaException {
		final Calendar instance = Calendar.getInstance();
		instance.add(Calendar.YEAR, -10);
		if (dataNascimento == null) {
			throw new PessoaException(HttpStatus.NOT_ACCEPTABLE, PessoaExceptionMessages.DATA_NASCIMENTO_OBRIGATORIA);
		}

		if (dataNascimento.after(instance.getTime())) {
			throw new PessoaException(HttpStatus.NOT_ACCEPTABLE, PessoaExceptionMessages.DATA_NASCIMENTO_INVALIDA);
		}
	}

	private static void validarEmail(final String email) throws PessoaException {
		if (StringUtils.isNotEmpty(email) && !isValidEmailAddress(email)) {
			throw new PessoaException(HttpStatus.NOT_ACCEPTABLE, PessoaExceptionMessages.EMAIL_INVALIDO);
		}
	}

	private static void validarCpf(final String cpf) throws PessoaException {
		final var cpfValidator = new CPFValidator();
		try {
			cpfValidator.assertValid(cpf);
		} catch (final InvalidStateException e) { // exception lançada quando o documento é inválido
			throw new PessoaException(HttpStatus.NOT_ACCEPTABLE, MessageFormat.format("{0} {1}", PessoaExceptionMessages.CPF_INVALIDO, StringUtils.join(e.getInvalidMessages())),
					e);
		}
	}

	private static void validarNome(final String nome) throws PessoaException {
		if (StringUtils.isEmpty(nome)) {
			throw new PessoaException(HttpStatus.NOT_ACCEPTABLE, PessoaExceptionMessages.NOME_OBRIGATORIO);
		}
	}

	public static boolean isValidEmailAddress(final String email) {
		boolean result = true;
		try {
			final var emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (final AddressException ex) {
			result = false;
		}
		return result;
	}
}
