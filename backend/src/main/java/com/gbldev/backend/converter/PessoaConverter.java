package com.gbldev.backend.converter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.gbldev.backend.dto.PessoaDTO;
import com.gbldev.backend.entity.Pessoa;
import com.gbldev.backend.enums.Sexo;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PessoaConverter {

	public static Pessoa fromDtoToEntity(final PessoaDTO dto, final String... camposIgnorar) {
		final Pessoa pessoa = new Pessoa();
		BeanUtils.copyProperties(dto, pessoa, camposIgnorar);
		pessoa.setSexo(dto.getSexo().getNomeSexo());
		return pessoa;
	}

	public static PessoaDTO fromEntityToDto(final Pessoa pessoa, final String... camposIgnorar) {
		final PessoaDTO pessoaDTO = PessoaDTO.builder().build();
		BeanUtils.copyProperties(pessoa, pessoaDTO, camposIgnorar);
		if (StringUtils.isNotEmpty(pessoa.getSexo())) {
			pessoaDTO.setSexo(Sexo.valueOf(pessoa.getSexo()));
		}
		return pessoaDTO;
	}
}
