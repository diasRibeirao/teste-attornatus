package com.teste.attornatus.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PessoaInputDTO {
	@NotNull(message = "O nome é obrigatório!")
	@Size(min = 5, max = 100, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

	@NotNull(message = "A data de nascimento é obrigatória!")
	@Schema(type = "string", pattern = "dd/MM/yyyy", example = "22/07/1983")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

}
