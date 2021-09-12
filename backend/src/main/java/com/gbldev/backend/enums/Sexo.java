package com.gbldev.backend.enums;

import lombok.Getter;

@Getter
public enum Sexo {
	MASCULINO("MASCULINO"),
	FEMININO("FEMININO"),
	NAO_INFORMAR("PREFIRO NÃO INFORMAR");

	private final String nomeSexo;

	Sexo(final String nomeSexo) {
		this.nomeSexo = nomeSexo;
	}
}
