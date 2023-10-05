package com.teste.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.attornatus.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	List<Endereco> findByPessoaId(Long pessoaId);
	
}
