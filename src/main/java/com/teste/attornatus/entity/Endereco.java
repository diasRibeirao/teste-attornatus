package com.teste.attornatus.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ENDERECO")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Endereco {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private Long id;
	
	@ManyToOne
	@JoinColumn(name = "PESSOA_ID")
	private Pessoa pessoa;
	
	@Column(name = "CEP")
    private String cep;
	
	@Column(name = "LOGRADOURO")
    private String logradouro;
	
	@Column(name = "NUMERO")
    private String numero;
	
	@Column(name = "CIDADE")
    private String cidade;
	
	@Column(name = "IS_PRINCIPAL")
	private Boolean principal;
	
}
