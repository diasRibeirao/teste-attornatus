package com.teste.attornatus;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Calendar;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.attornatus.entity.Endereco;
import com.teste.attornatus.entity.Pessoa;

public class TestUtils {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static Pessoa criarPessoa(String nome) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 1983);
		calendar.set(Calendar.MONTH, Calendar.JULY);
		calendar.set(Calendar.DAY_OF_MONTH, 21);

		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setDataNascimento(calendar.getTime());

		return pessoa;
	}

	public static Endereco criarEndereco(String logradouro, Boolean principal) {
		Endereco endereco = new Endereco();
						
		endereco.setCep("06190120");		
		endereco.setLogradouro(logradouro);	
		endereco.setNumero("154");
		endereco.setCidade("Osasco");
		endereco.setPrincipal(principal);
		
		return endereco;
	}
}
