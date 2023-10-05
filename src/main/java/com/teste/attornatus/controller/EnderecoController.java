package com.teste.attornatus.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teste.attornatus.dto.EnderecoInputDTO;
import com.teste.attornatus.dto.EnderecoOutputDTO;
import com.teste.attornatus.dto.converter.EnderecoConverter;
import com.teste.attornatus.entity.Endereco;
import com.teste.attornatus.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos/{pessoaId}")
@Tag(name = "Enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private EnderecoConverter enderecoConverter;

	@GetMapping
	@Operation(summary = "Listar endereços da pessoa")
	public ResponseEntity<List<EnderecoOutputDTO>> listar(@Valid @PathVariable Long pessoaId,
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		List<EnderecoOutputDTO> list = enderecoConverter.parse(enderecoService.listar(pessoaId));

		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().body(list);
	}
	
	@Operation(summary = "Buscar um endereço pelo id")
	@GetMapping(value = "/{enderecoId}")
	public ResponseEntity<EnderecoOutputDTO> buscarPeloId(@Valid @PathVariable Long pessoaId, @PathVariable Long enderecoId) {
		EnderecoOutputDTO obj = enderecoConverter.parse(enderecoService.buscarPeloId(pessoaId, enderecoId));
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Adicionar endereço da pessoa")
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @PathVariable Long pessoaId, @Valid @RequestBody EnderecoInputDTO enderecoInputDTO) {
		Endereco endereco = enderecoConverter.parsePessoaInputDTO(pessoaId, enderecoInputDTO);
		endereco = enderecoService.inserir(endereco);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{enderecoId}")
					.buildAndExpand(endereco.getId()) 
					.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Atualizar endereço da pessoa")
	@PutMapping(value = "/{enderecoId}")
	public ResponseEntity<Void> atualizar(@Valid @PathVariable Long pessoaId,
			@Valid @RequestBody EnderecoInputDTO enderecoInputDTO, @Valid @PathVariable Long enderecoId) {
		Endereco endereco = enderecoConverter.parsePessoaInputDTO(pessoaId, enderecoInputDTO);
		endereco.setId(enderecoId);
		endereco = enderecoService.atualizar(endereco);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Tornar um endereço principal da pessoa")
	@PutMapping("/{enderecoId}/principal")
    public ResponseEntity<Void> tornarEnderecoPrincipal(@Valid @PathVariable Long pessoaId, @PathVariable Long enderecoId) {
        enderecoService.tornarEnderecoPrincipal(pessoaId, enderecoId);
        return ResponseEntity.noContent().build();
    }
}
