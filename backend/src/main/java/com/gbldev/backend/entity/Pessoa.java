package com.gbldev.backend.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "PESSOA")
@AllArgsConstructor
@NoArgsConstructor
public class Pessoa {

	@Id
	@Column(name = "CPF", nullable = false)
	private String cpf;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "SEXO")
	private String sexo;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DATA_NASCIMENTO", nullable = false)
	private Date dataNascimento;

	@Column(name = "NATURALIDADE")
	private String naturalidade;

	@Column(name = "NACIONALIDADE")
	private String nacionalidade;

	@Column(name = "DATA_CADASTRO")
	private Date dataCadastro;

	@Column(name = "DATA_ATUALIZACAO")
	private Date dataAtualizacao;

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
			return false;
		}
		final Pessoa pessoa = (Pessoa) o;
		return Objects.equals(this.cpf, pessoa.cpf);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}

