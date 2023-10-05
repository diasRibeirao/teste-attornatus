package com.teste.attornatus;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.teste.attornatus.controller.PessoaController;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PessoaControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PessoaController pessoaController;

	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.pessoaController).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Order(1)
	public void listarTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pessoas")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	@Order(2)
	public void inserirTest() throws Exception {		
		mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.criarPessoa("Fulano de Tal"))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	@Order(3)
	public void buscarPeloIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.nome", is("Fulano de Tal")))
				.andExpect(jsonPath("$.dataNascimento", is("22/07/1983")));
	}
	
	@Test
	@Order(4)
	public void atualizarTest() throws Exception {		
		mockMvc.perform(MockMvcRequestBuilders.put("/pessoas/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.criarPessoa("Fulano de Ok"))))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.nome", is("Fulano de Ok")))
				.andExpect(jsonPath("$.dataNascimento", is("22/07/1983")));		
	}
}
