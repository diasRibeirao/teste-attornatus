package com.teste.attornatus.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.attornatus.dto.EnderecoInputDTO;
import com.teste.attornatus.dto.EnderecoOutputDTO;
import com.teste.attornatus.entity.Endereco;
import com.teste.attornatus.service.PessoaService;

@Service
public class EnderecoConverter {
	
	@Autowired
	private PessoaService pessoaService;

	public EnderecoOutputDTO parse(Endereco origin) {
		if (origin == null)
			return null;

		return new EnderecoOutputDTO(origin.getId(), origin.getPessoa().getId(), origin.getCep(), origin.getLogradouro(),
				origin.getNumero(), origin.getCidade(), origin.getPrincipal());
	}

	public List<EnderecoOutputDTO> parse(List<Endereco> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> parse(obj)).collect(Collectors.toList());
	}

	public Endereco parsePessoaInputDTO(Long pessoaId, EnderecoInputDTO origin) {
		if (origin == null)
			return null;
				
		Endereco endereco = new Endereco();
		endereco.setPessoa(pessoaService.buscarPeloId(pessoaId));
		endereco.setCep(origin.cep());
		endereco.setLogradouro(origin.logradouro());
		endereco.setNumero(origin.numero());
		endereco.setCidade(origin.cidade());
		endereco.setPrincipal(origin.principal());
		
		return endereco;
	}

}
