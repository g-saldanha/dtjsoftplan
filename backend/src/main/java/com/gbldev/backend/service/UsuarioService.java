package com.gbldev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gbldev.backend.dao.UsuarioRepository;
import com.gbldev.backend.dto.LoginDTO;
import com.gbldev.backend.dto.UsuarioDTO;
import com.gbldev.backend.entity.Usuario;
import com.gbldev.backend.security.ComercialUserDetailsService;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ComercialUserDetailsService userDetailsService;

	public String cadastro(final UsuarioDTO usuarioDTO) {
		try {
			final Usuario build = Usuario
					.builder()
					.ativo(true)
					.login(usuarioDTO.getEmail())
					.nome(usuarioDTO.getNome())
					.senha(new BCryptPasswordEncoder().encode(usuarioDTO.getSenha()))
					.email(usuarioDTO.getEmail())
					.build();
			final Usuario save = this.usuarioRepository.save(build);
			return save.toString();
		} catch (final Exception exception) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Não foi possível criar usuário", exception);
		}
	}

	public LoginDTO logar(final LoginDTO loginDTO) {
		try {
			final UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginDTO.getLogin());
			if (userDetails.isEnabled() && new BCryptPasswordEncoder().matches(loginDTO.getSenha(), userDetails.getPassword())) {
				loginDTO.setSenha(userDetails.getPassword());
				return loginDTO;
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário e senha não encontrado");
		} catch (final UsernameNotFoundException | ResponseStatusException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		} catch (final Exception ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu algum problema, tente novamente");
		}
	}
}
