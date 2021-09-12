package com.gbldev.backend.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.caelum.stella.validation.InvalidStateException;

public class PessoaException extends ResponseStatusException {

	@Serial
	private static final long serialVersionUID = 739724904742376969L;

	public PessoaException(final HttpStatus status, final String message) {
		super(status, message);
	}

	public PessoaException(final HttpStatus status, final String message, final InvalidStateException e) {
		super(status, message, e);
	}
}
