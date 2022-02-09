package com.generation.blogPessoal.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogPessoal.controller.PostagemController;
import com.generation.blogPessoal.model.PostagemModel;
import com.generation.blogPessoal.util.Tipo;



@Repository

public interface PostagemRepository extends JpaRepository<PostagemModel, Long> {

	public List<PostagemModel> findAllByTituloContainingIgnoreCase(String titulo);

	public List<PostagemModel> findAll(@Valid Long id);
	public List<PostagemModel> findAllByTipo(@Valid Long tipo);

	public Object save(PostagemController postagem);

}