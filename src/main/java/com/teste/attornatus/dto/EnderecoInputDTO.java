package com.teste.attornatus.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EnderecoInputDTO(
		@NotNull(message = "O cep é obrigatório!")
		@Size(min = 8, max = 8, message = "O cep deve possuir {max} caracteres!")
		String cep, 
		
		@NotNull(message = "O logradouro é obrigatório!")
		@Size(min = 5, max = 100, message = "O logradouro deve possuir entre {min} e {max} caracteres!")
		String logradouro, 
		
		@NotNull(message = "O número é obrigatório!")
		String numero, 
		
		@NotNull(message = "A cidade é obrigatório!")
		@Size(min = 5, max = 100, message = "A cidade deve possuir entre {min} e {max} caracteres!")
		String cidade, 
		
		@NotNull(message = "O principal é obrigatório!")
		Boolean principal) {

}
