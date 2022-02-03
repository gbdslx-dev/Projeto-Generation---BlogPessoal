package com.generation.BlogGabi.Repository;


	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.BlogGabi.Model.Tema;


	public interface TemaRepository extends JpaRepository<TemaModel, Long> {
		public List<TemaModel> findAllByDescricaoContainingIgnoreCase (String descricao);

	}