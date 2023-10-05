package com.teste.attornatus.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.attornatus.dto.PessoaInputDTO;
import com.teste.attornatus.dto.PessoaOutputDTO;
import com.teste.attornatus.entity.Pessoa;

@Service
public class PessoaConverter {

	@Autowired
	private EnderecoConverter enderecoConverter;

	public PessoaOutputDTO parse(Pessoa origin) {
		if (origin == null)
			return null;

		return new PessoaOutputDTO(origin.getId(), origin.getNome(), origin.getDataNascimento(),
				enderecoConverter.parse(origin.getEnderecos()));
	}

	public List<PessoaOutputDTO> parse(List<Pessoa> origin) {
		if (origin == null)
			return null;

		return origin.stream().map(obj -> parse(obj)).collect(Collectors.toList());
	}

	public Pessoa parsePessoaInputDTO(PessoaInputDTO origin) {
		if (origin == null)
			return null;

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(origin.getNome());
		pessoa.setDataNascimento(origin.getDataNascimento());
		return pessoa;
	}

	
}
