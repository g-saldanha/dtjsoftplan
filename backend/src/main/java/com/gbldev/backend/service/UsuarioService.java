package com.gbldev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gbldev.backend.dao.UsuarioRepository;
import com.gbldev.backend.dto.UsuarioDTO;
import com.gbldev.backend.entity.Usuario;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

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
}
