package com.gbldev.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "DTO que representa as informações de login do usuário.")
public class LoginDTO {
	@Schema(name = "login", description = "Login do Usuário", example = "joaosilva", required = true)
	private String login;

	@Schema(name = "senha", description = "Senha do Usuário", example = "asenha", required = true)
	private String senha;
}
