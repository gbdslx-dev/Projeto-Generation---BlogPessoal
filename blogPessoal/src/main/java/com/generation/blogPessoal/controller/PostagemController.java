package com.generation.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogPessoal.model.PostagemModel;
import com.generation.blogPessoal.repository.PostagemRepository;
import com.generation.blogPessoal.util.Tipo;

@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repository;

	@GetMapping
	public ResponseEntity<List<PostagemModel>> FindAllPostagemModel() {
		List<PostagemModel> list = repository.findAll();
		if (list.isEmpty()) {

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

		} else {

		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/{id}")
	public Optional<PostagemModel> findByIDPostagemModel(@PathVariable Long id) {
		return repository.findById(id);
	}

	@GetMapping("/titulo/{titulo}")
	public Object getByTitulo(@PathVariable String titulo) {
		List<PostagemModel> list = repository.findAllByTituloContainingIgnoreCase(titulo);
		
			if (list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				} 
		
			else {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}
}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<PostagemModel>> findAllByTipo(@PathVariable @Valid Long tipo) {
		List<PostagemModel> list = repository.findAllByTipo(tipo);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} 
		
		else {
			return ResponseEntity.ok(list);
		}
}

	@PostMapping
	public ResponseEntity<Object> postPostagem(@RequestBody PostagemModel postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}

	@PutMapping
	public ResponseEntity<PostagemModel> putPostagem(@Valid @RequestBody PostagemModel postagem){
		return repository.findById(postagem.GetId())
				.map(resp -> ResponseEntity.ok(repository.save(postagem)))
				.orElse(ResponseEntity.notFound().build());

	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity deletePostagem(@PathVariable Long id) {
		Optional<PostagemModel> optional = Optional.empty();
		if (optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}