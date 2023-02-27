package com.generation.gamestore.repository;

import com.generation.gamestore.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findAllByTituloIsContainingIgnoreCase(@Param("titulo") String titulo);
    List<Produto> findAllByCategoriaCatNomeIsContainingIgnoreCase(@Param("categoria") String catNome);

}
