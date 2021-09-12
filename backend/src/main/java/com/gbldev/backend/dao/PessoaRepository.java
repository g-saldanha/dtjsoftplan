package com.gbldev.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbldev.backend.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, String> {
}
