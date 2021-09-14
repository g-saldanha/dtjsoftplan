package com.gbldev.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

	@Mock
	private UsuarioService usuarioService;

	@BeforeEach
	void setUp() {
	}

	@Test
	@DisplayName("Deve cadastrar usuario")
	void cadastro() {
	}

	@Test
	@DisplayName("Deve logar usuario")
	void logar() {
	}

	@Test
	@DisplayName("Não deve logar usuário inexistente")
	void naoLogar() {
	}
}