package com.gbldev.backend.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gbldev.backend.dao.UsuarioRepository;
import com.gbldev.backend.entity.Usuario;

@Component
public class ComercialUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Usuario usuario = this.usuarioRepository.findByLogin(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}

		return new UsuarioSistema(usuario.getNome(), usuario.getLogin(), usuario.getSenha(), this.authorities());
	}

	public Collection<? extends GrantedAuthority> authorities() {
		final Collection<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return auths;
	}
}