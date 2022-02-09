package com.generation.blogPessoal.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface TemaRepository extends JpaRepository<TemaRepository, Long> {

	public List<TemaRepository> findAllByDescricaoContainingIgnoreCase(String descricao);

}
