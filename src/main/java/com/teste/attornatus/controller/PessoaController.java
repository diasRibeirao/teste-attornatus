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

import com.teste.attornatus.dto.PessoaInputDTO;
import com.teste.attornatus.dto.PessoaOutputDTO;
import com.teste.attornatus.dto.converter.PessoaConverter;
import com.teste.attornatus.entity.Pessoa;
import com.teste.attornatus.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
@Tag(name = "Pessoas")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaConverter pessoaConverter;

	@GetMapping
	@Operation(summary = "Listar todas as pessoas")
	public ResponseEntity<List<PessoaOutputDTO>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		List<PessoaOutputDTO> list = pessoaConverter.parse(pessoaService.listar());

		if (list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok().body(list);
	}

	@Operation(summary = "Buscar uma pessoa pelo id")
	@GetMapping(value = "/{pessoaId}")
	public ResponseEntity<PessoaOutputDTO> buscarPeloId(@PathVariable Long pessoaId) {
		PessoaOutputDTO obj = pessoaConverter.parse(pessoaService.buscarPeloId(pessoaId));
		return ResponseEntity.ok().body(obj);
	}
	
	@Operation(summary = "Adicionar uma pessoa")
	@PostMapping
	public ResponseEntity<Void> inserir(@Valid @RequestBody PessoaInputDTO pessoaInputDTO) {
		Pessoa pessoa = pessoaConverter.parsePessoaInputDTO(pessoaInputDTO);
		pessoa = pessoaService.inserir(pessoa);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{pessoaId}")
					.buildAndExpand(pessoa.getId())
					.toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(summary = "Atualizar uma pessoa")
	@PutMapping(value = "/{pessoaId}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO,
			@Valid @PathVariable Long pessoaId) {
		Pessoa pessoa = pessoaConverter.parsePessoaInputDTO(pessoaInputDTO);
		pessoa.setId(pessoaId);
		pessoa = pessoaService.atualizar(pessoa);
		return ResponseEntity.noContent().build();
	}
	
}
