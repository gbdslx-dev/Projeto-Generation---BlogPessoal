package com.generation.blogPessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogPessoal.model.PostagemModel;
import com.generation.blogPessoal.util.Tipo;

@Repository
public interface PostagemRepository extends JpaRepository<PostagemModel,Long> {
	public List<PostagemModel> findAllByTituloContainingIgnoreCase (String titulo);
	
	public List<PostagemModel> findAllByTipo (Tipo tipo);
}
