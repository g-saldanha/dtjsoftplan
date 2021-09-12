package com.gbldev.backend.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.exception.PessoaException;
import com.gbldev.backend.service.PessoaService;
import com.gbldev.backend.utils.PessoaMessages;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pessoa")
@Tag(name = "Pessoa", description = "Grupo de Endpoints reponsáveis pelo cadastro, alteração, remoção e consulta de pessoas.")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	/**
	 * Endpoint responsável por obter as pessoas
	 *
	 * @return Pessoas cadastradas {@link List<PessoaDTO>}
	 */
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Endpoint que lista as pessoas.", security = @SecurityRequirement(name = "basic"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PessoaDTO.class)))),
			@ApiResponse(responseCode = "502", description = "Erro de gateway", content = @Content(schema = @Schema(implementation = ResponseStatusException.class)))
	})
	public ResponseEntity<List<PessoaDTO>> obterPessoas() {
		try {
			return ResponseEntity.ok(this.pessoaService.listarPessoas());
		} catch (final Exception exception) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Não foi possível listar as pessoas", exception);
		}
	}

	/**
	 * Endpoint responsável por criar uma pessoa.
	 *
	 * @param pessoaDTO informações da pessoa.
	 * @return a pessoa criada, convertido em {@link String}.
	 */
	@PostMapping(produces = { MediaType.TEXT_PLAIN_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Endpoint que cadastra pessoa.", security = @SecurityRequirement(name = "basic"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Criado", content = @Content(schema = @Schema(implementation = String.class, example = PessoaMessages.CADASTRO_SUCESSO))),
			@ApiResponse(responseCode = "406", description = "Problema na criacão", content = @Content(schema = @Schema(implementation = PessoaException.class))),
			@ApiResponse(responseCode = "502", description = "Erro de gateway", content = @Content(schema = @Schema(implementation = ResponseStatusException.class)))
	})
	public ResponseEntity<String> cadastrarPessoa(
			@Parameter(description = "informações da pessoa.", schema = @Schema(implementation = PessoaDTO.class))
			@Valid
			@RequestBody
			final PessoaDTO pessoaDTO
	) throws PessoaException {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pessoaService.cadastro(pessoaDTO));
		} catch (final ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
	}

	/**
	 * Endpoint responsável por atualizar uma pessoa.
	 *
	 * @param pessoaDTO informações da pessoa.
	 * @return a notificação criada, convertido em {@link String}.
	 */
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	@Operation(summary = "Endpoint que atualiza pessoa.", security = @SecurityRequirement(name = "basic"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(type = "string",
					example = PessoaMessages.ATUALIZADO_SUCESSO))),
			@ApiResponse(responseCode = "406", description = "Problema na atualizacão", content = @Content(schema = @Schema(implementation = PessoaException.class))),
			@ApiResponse(responseCode = "502", description = "Erro de gateway", content = @Content(schema = @Schema(implementation = ResponseStatusException.class)))
	})
	public ResponseEntity<String> atualizaPessoa(
			@Parameter(description = "informações da pessoa.", schema = @Schema(implementation = PessoaDTO.class))
			@Valid
			@RequestBody
			final PessoaDTO pessoaDTO
	) throws PessoaException {
		try {
			return ResponseEntity.ok(this.pessoaService.atualizar(pessoaDTO));
		} catch (final ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
	}

	/**
	 * Endpoint responsável por remoção uma pessoa.
	 *
	 * @param cpf ID CPF da pessoa.
	 * @return a notificação criada, convertido em {@link String}.
	 */
	@DeleteMapping(value = "{cpf}", produces = { MediaType.TEXT_PLAIN_VALUE })
	@Operation(summary = "Endpoint que remove a pessoa.", security = @SecurityRequirement(name = "basic"))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(type = "string",
					example = PessoaMessages.DELETADO_SUCESSO))),
			@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(schema = @Schema(implementation = PessoaException.class))),
			@ApiResponse(responseCode = "502", description = "Erro de gateway", content = @Content(schema = @Schema(implementation = ResponseStatusException.class)))
	})
	public ResponseEntity<String> removePessoa(
			@Parameter(description = "ID da pessoa", required = true, schema = @Schema(type = "string"))
			@PathVariable
			final String cpf
	) throws PessoaException {
		try {
			return ResponseEntity.ok(this.pessoaService.deletar(cpf));
		} catch (final ResponseStatusException exception) {
			return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
		}
	}
}
