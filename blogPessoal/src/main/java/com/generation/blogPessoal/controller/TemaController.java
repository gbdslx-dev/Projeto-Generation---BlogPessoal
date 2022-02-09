package com.generation.blogPessoal.controller;
	
	import java.util.List;

import org.generation.blogPessoal.model.PostagemModel;
import org.generation.blogPessoal.model.TemaModel;
import org.generation.blogPessoal.repository.TemaRepository;
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
import org.springframework.web.bind.annotation.RestController;

	@RestController
	@CrossOrigin(origins = "", allowedHeaders = "")
	public class TemaController {
		
		@Autowired
		private TemaRepository repository;
		
		@GetMapping
		public ResponseEntity<Object> getAll(){
			return ResponseEntity.ok(PostagemModel.findAll());
		}

		@GetMapping("/{id}")
		public ResponseEntity<TemaModel> getById(@PathVariable long id) {
			return PostagemModel.findById(id).map (resp -> ResponseEntity.ok(resp))
					.orElse(ResponseEntity.notFound().build());		
		}
		@GetMapping("/nome/{nome}")
		public ResponseEntity<List<TemaModel>> GetByName(@PathVariable String nome){
			return ResponseEntity.ok(repository.FindByAll(nome));
		}
		
		@PostMapping
		
		public ResponseEntity<Object> post (@RequestBody TemaModel tema){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(PostagemModel.save(tema));	
		}
		
		@PutMapping
		
		public ResponseEntity<TemaModel> put (@RequestBody TemaModel tema){
			return ResponseEntity.ok(repository.save(tema));		
		}
		
		@DeleteMapping("/{id}")
			
		public void delete (@PathVariable long id) {
			repository.deleteById(id);
		}
		
	}