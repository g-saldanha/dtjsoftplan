package com.gbldev.backend.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/source")
@Tag(name = "Source", description = "Grupo que retorna link do projeto no github com o código fonte do projeto desenvolvido.")
public class SourceController {

	public static final String GITHUB_REP_GABRIEL = "https://github.com/g-saldanha/dtjsoftplan";

	/**
	 * Enpoint responsável por retornar o link do projeto no github com o código-fonte do projeto desenvolvido.
	 *
	 * @return O repositório Git do projeto
	 */
	@GetMapping(produces = { MediaType.TEXT_PLAIN_VALUE })
	@Operation(summary = "Endpoint acessível sem autenticação que retorna link do projeto no github com o código fonte do projeto desenvolvido.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(type = "string", example = "https://github.com/g-saldanha"))),
	})
	public ResponseEntity<String> obterRepoGithub() {
		return ResponseEntity.ok(GITHUB_REP_GABRIEL);
	}
}
