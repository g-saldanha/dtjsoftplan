package com.gbldev.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbldev.backend.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByLogin(String login);

	Usuario findByEmail(String email);

	Optional<Usuario> findByLoginAndSenha(String login, String senha);
}
