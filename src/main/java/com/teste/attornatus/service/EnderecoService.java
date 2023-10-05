package com.teste.attornatus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.attornatus.entity.Endereco;
import com.teste.attornatus.entity.Pessoa;
import com.teste.attornatus.repository.EnderecoRepository;
import com.teste.attornatus.service.exceptions.ObjectNotFoundException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class EnderecoService {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Endereco> listar(Long pessoaId) {
		Pessoa pessoa = pessoaService.buscarPeloId(pessoaId);
		return enderecoRepository.findByPessoaId(pessoa.getId());
	}
	
	public Endereco buscarPeloId(Long pessoaId, Long id) {
		pessoaService.buscarPeloId(pessoaId);
		return buscarPeloId(id);
	}

	public Endereco buscarPeloId(Long id) {
		Optional<Endereco> obj = enderecoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado! Id: " + id));
	}

	@Transactional
	public Endereco inserir(Endereco endereco) {		
		Endereco enderecoSalvo = enderecoRepository.save(endereco);
		
		if (endereco.getPrincipal()) {
			tornarEnderecoPrincipal(endereco.getPessoa().getId(), endereco.getId());
		}
		
		return enderecoSalvo;
	}

	@Transactional
	public Endereco atualizar(Endereco endereco) {
		buscarPeloId(endereco.getId());
		Endereco enderecoSalvo =  enderecoRepository.save(endereco);
		
		if (endereco.getPrincipal()) {
			tornarEnderecoPrincipal(endereco.getPessoa().getId(), endereco.getId());
		}
		
		return enderecoSalvo;
	}

	public void tornarEnderecoPrincipal(@Valid Long pessoaId, Long enderecoId) {
		Endereco endereco = buscarPeloId(pessoaId, enderecoId);
		
		List<Endereco> enderecosDaPessoa = enderecoRepository.findByPessoaId(pessoaId);
        for (Endereco e : enderecosDaPessoa) {
            e.setPrincipal(false);
        }

        endereco.setPrincipal(true);
        
        enderecoRepository.saveAll(enderecosDaPessoa);
	}

}
