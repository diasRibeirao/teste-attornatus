package com.teste.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.attornatus.entity.Pessoa;
import com.teste.attornatus.repository.PessoaRepository;
import com.teste.attornatus.service.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }
	
	public Pessoa buscarPeloId(Long id) {
		Optional<Pessoa> obj = pessoaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Pessoa não encontrada! Id: " + id));
	}
	
	@Transactional
    public Pessoa inserir(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }
	
	@Transactional
    public Pessoa alterar(Pessoa pessoa) {
		buscarPeloId(pessoa.getId());
        return pessoaRepository.save(pessoa);
    }
	
}
