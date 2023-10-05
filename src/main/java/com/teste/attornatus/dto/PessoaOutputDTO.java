package com.teste.attornatus.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PessoaOutputDTO(
		Long id, 
		String nome,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") 
		Date dataNascimento,
		List<EnderecoOutputDTO> enderecos) {
}
