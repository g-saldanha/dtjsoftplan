package com.gbldev.backend.dto;

import java.util.Date;

import com.gbldev.backend.enums.Sexo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO que representa as informações referentes à Pessoa.")
public class PessoaDTO {

	@Schema(name = "cpf", description = "Cpf da Pessoa", example = "96773550076")
	private String cpf;

	@Schema(name = "nome", description = "Nome da Pessoa", example = "João da Silva")
	private String nome;

	@Schema(name = "sexo", description = "Sexo da Pessoa")
	private Sexo sexo;

	@Schema(name = "email", description = "Nome da Pessoa", example = "exemplo@softplan.com.br")
	private String email;

	@Schema(name = "dataNascimento", description = "Data de Nascimento da Pessoa")
	private Date dataNascimento;

	@Schema(name = "naturalidade", description = "Naturalidade", example = "Florianopolitano")
	private String naturalidade;

	@Schema(name = "nacionalidade", description = "Nacionalidade", example = "Brasileiro")
	private String nacionalidade;
}

