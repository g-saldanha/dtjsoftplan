package com.gbldev.backend.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PessoaExceptionMessages {

	public static final String NOME_OBRIGATORIO = "Nome da pessoa é obrigatório";
	public static final String CPF_INVALIDO = "CPF tem que ser um cpf válido";
	public static final String EMAIL_INVALIDO = "Email tem que ser um email válido";
	public static final String DATA_NASCIMENTO_OBRIGATORIA = "Data de Nascimento é obrigatória";
	public static final String DATA_NASCIMENTO_INVALIDA = "Data de Nascimento inválida";
	public static final String JA_EXISTE = "Pessoa já existe";
	public static final String NAO_EXISTE = "Pessoa não existe";
}
