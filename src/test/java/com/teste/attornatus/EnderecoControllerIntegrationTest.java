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

import com.teste.attornatus.controller.EnderecoController;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnderecoControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EnderecoController enderecoController;

	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.enderecoController).build();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	@Order(1)
	public void listarTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/pessoas")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.criarPessoa("Ciclano de Tal"))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	@Order(2)
	public void inserirTest() throws Exception {		
		mockMvc.perform(MockMvcRequestBuilders.post("/enderecos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.criarEndereco("Rua de Tal", false))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	@Order(3)
	public void buscarPeloIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.cep", is("06190120")))
				.andExpect(jsonPath("$.logradouro", is("Rua de Tal")))
				.andExpect(jsonPath("$.numero", is("154")))
				.andExpect(jsonPath("$.cidade", is("Osasco")))
				.andExpect(jsonPath("$.principal", is(false)));
	}
	
	@Test
	@Order(4)
	public void atualizarTest() throws Exception {		
		mockMvc.perform(MockMvcRequestBuilders.put("/enderecos/1/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.criarEndereco("Rua do Ok", true))))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.cep", is("06190120")))
				.andExpect(jsonPath("$.logradouro", is("Rua do Ok")))
				.andExpect(jsonPath("$.numero", is("154")))
				.andExpect(jsonPath("$.cidade", is("Osasco")))
				.andExpect(jsonPath("$.principal", is(true)));		
	}
	
	@Test
	@Order(4)
	public void tornarEnderecoPrincipalTest() throws Exception {		
		mockMvc.perform(MockMvcRequestBuilders.post("/enderecos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtils.convertObjectToJsonBytes(TestUtils.criarEndereco("Rua de Baixo", false))))
                .andExpect(MockMvcResultMatchers.status().isCreated());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.logradouro", is("Rua de Baixo")))
				.andExpect(jsonPath("$.principal", is(false)));
		
		mockMvc.perform(MockMvcRequestBuilders.put("/enderecos/1/2/principal"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/1/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(jsonPath("$.principal", is(true)));
		
	}
}
