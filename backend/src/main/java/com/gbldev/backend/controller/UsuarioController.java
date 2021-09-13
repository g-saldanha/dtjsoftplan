package com.gbldev.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gbldev.backend.dto.LoginDTO;
import com.gbldev.backend.dto.UsuarioDTO;
import com.gbldev.backend.exception.PessoaException;
import com.gbldev.backend.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Grupo de endpoints responsáveis pelos acessos usuários")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	/**
	 * Endpoint responsável por criar uma pessoa.
	 *
	 * @param usuarioDTO informações da pessoa.
	 * @return o usuário criada, convertido em {@link String}.
	 */
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Endpoint que cadastra usuário.", security = @SecurityRequirement(name = "basic"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Criado", content = @Content(schema = @Schema(type = "string"))),
			@ApiResponse(responseCode = "406", description = "Problema na criacão", content = @Content(schema = @Schema(implementation = ResponseStatusException.class))),
			@ApiResponse(responseCode = "502", description = "Erro de gateway", content = @Content(schema = @Schema(implementation = ResponseStatusException.class))) })
	public ResponseEntity<String> cadastrarUsuario(
			@Parameter(description = "informações do usuário.", schema = @Schema(implementation = UsuarioDTO.class)) @Valid @RequestBody final UsuarioDTO usuarioDTO)
			throws PessoaException {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.cadastro(usuarioDTO));
		} catch (final ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
	}

	/**
	 * Endpoint responsável por criar uma pessoa.
	 *
	 * @param loginDTO informações da pessoa.
	 * @return o usuário criada, convertido em {@link LoginDTO}.
	 */
	@PostMapping(value = "/logar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Endpoint que busca o login do usuário.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = LoginDTO.class))),
			@ApiResponse(responseCode = "404", description = "Problema na criacão", content = @Content(schema = @Schema(implementation = ResponseStatusException.class))),
			@ApiResponse(responseCode = "502", description = "Erro de gateway", content = @Content(schema = @Schema(implementation = ResponseStatusException.class))) })
	public ResponseEntity<Object> logarUsuario(
			@Parameter(description = "informações do login do usuário.", schema = @Schema(implementation = LoginDTO.class)) @Valid @RequestBody final LoginDTO loginDTO) {
		try {
			return ResponseEntity.ok(this.usuarioService.logar(loginDTO));
		} catch (final ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
	}

}
