package com.teste.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.attornatus.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
