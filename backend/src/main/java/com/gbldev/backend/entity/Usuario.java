package com.gbldev.backend.entity;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements Serializable {

	@Serial
	private static final long serialVersionUID = 5914398410223267973L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@Column(name = "LOGIN", nullable = false)
	private String login;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ATIVO", nullable = false)
	private boolean ativo;
}
