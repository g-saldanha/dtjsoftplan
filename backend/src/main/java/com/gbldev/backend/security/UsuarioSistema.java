package com.gbldev.backend.security;

import java.io.Serial;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class UsuarioSistema extends User {

	@Serial
	private static final long serialVersionUID = 1262341651702424443L;
	private final String nome;

	public UsuarioSistema(final String nome, final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);

		this.nome = nome;
	}

}