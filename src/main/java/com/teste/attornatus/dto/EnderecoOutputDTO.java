package com.teste.attornatus.dto;

public record EnderecoOutputDTO(
		Long id, 
		Long pessoaId,
		String cep, 
		String logradouro, 
		String numero, 
		String cidade, 
		Boolean principal) {
}
