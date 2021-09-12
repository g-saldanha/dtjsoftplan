package com.gbldev.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO que representa as informações referentes à Pessoa.")
public class UsuarioDTO {

	@Schema(name = "nome", description = "Nome do Usuário", example = "Admin", required = true)
	private String nome;

	@Schema(name = "login", description = "Login do Usuário", example = "joaosilva", required = true)
	private String login;

	@Schema(name = "senha", description = "Senha do Usuário", example = "joaosilva", required = true)
	private String senha;

	@Schema(name = "email", description = "Email do Usuário", example = "joaosilva@johndoe.com")
	private String email;

}
