package com.generation.gamestore.repository;

import com.generation.gamestore.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findAllByCatNomeIsContainingIgnoreCase(@Param("catNome")String catNome);
}
