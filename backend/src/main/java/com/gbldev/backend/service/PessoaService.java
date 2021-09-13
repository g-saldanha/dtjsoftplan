package com.gbldev.backend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.gbldev.backend.converter.PessoaConverter;
import com.gbldev.backend.dao.PessoaRepository;
import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.entity.Pessoa;
import com.gbldev.backend.exception.PessoaException;
import com.gbldev.backend.exception.PessoaExceptionMessages;
import com.gbldev.backend.utils.PessoaMessages;
import com.gbldev.backend.validator.PessoaValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public String cadastro(final PessoaDTO pessoaDTO) throws PessoaException {
		PessoaValidator.validarDados(pessoaDTO);
		this.validarExiste(pessoaDTO.getCpf());

		final var pessoa = PessoaConverter.fromDtoToEntity(pessoaDTO);
		pessoa.setDataCadastro(new Date());
		pessoa.setDataAtualizacao(new Date());
		this.pessoaRepository.save(pessoa);
		return PessoaMessages.CADASTRO_SUCESSO;
	}

	public String atualizar(final PessoaDTO pessoaDTO) throws PessoaException {
		final Optional<Pessoa> pessoaOptional = this.pessoaRepository.findById(pessoaDTO.getCpf());
		if (pessoaOptional.isEmpty()) {
			throw new PessoaException(HttpStatus.CONFLICT, PessoaExceptionMessages.NAO_EXISTE);
		}
		PessoaValidator.validarDados(pessoaDTO);

		final var pessoa = PessoaConverter.fromDtoToEntity(pessoaDTO);
		pessoa.setDataCadastro(pessoaOptional.get().getDataCadastro());
		pessoa.setDataAtualizacao(new Date());
		this.pessoaRepository.save(pessoa);
		return PessoaMessages.ATUALIZADO_SUCESSO;
	}

	public String deletar(final String cpf) throws PessoaException {
		try {
			this.pessoaRepository.deleteById(cpf);
			return PessoaMessages.DELETADO_SUCESSO;
		} catch (final IllegalArgumentException e) {
			throw new PessoaException(HttpStatus.NOT_FOUND, PessoaExceptionMessages.NAO_EXISTE);
		}
	}

	private void validarExiste(final String cpf) {
		final Optional<Pessoa> byId = this.pessoaRepository.findById(cpf);
		if (byId.isPresent()) {
			throw new PessoaException(HttpStatus.CONFLICT, PessoaExceptionMessages.JA_EXISTE);
		}
	}

	public List<PessoaDTO> listarPessoas() {
		final List<Pessoa> all = this.pessoaRepository.findAll();
		return all.stream().map(PessoaConverter::fromEntityToDto).toList();
	}

}
