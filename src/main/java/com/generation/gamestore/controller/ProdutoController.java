package com.generation.gamestore.controller;

import com.generation.gamestore.model.Produto;
import com.generation.gamestore.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable Long id){
        Optional<Produto> resposta = produtoRepository.findById(id);
        if (resposta.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        //return new ResponseEntity<Produto>(resposta.get(), HttpStatus.OK);
        return ResponseEntity.ok(resposta.get());
    }
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Produto>>getByTitulo(@PathVariable String titulo){
        List<Produto> resposta = produtoRepository.findAllByTituloIsContainingIgnoreCase(titulo);
        if (resposta.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/categoria/{catNome}")
    public ResponseEntity<List<Produto>>getByCatNome(@PathVariable String catNome){
        List<Produto> resposta = produtoRepository.findAllByCategoriaCatNomeIsContainingIgnoreCase(catNome);
        if (resposta.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        //return new ResponseEntity<List<Produto>>(resposta, HttpStatus.OK);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }
    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
        Optional res = produtoRepository.findById(produto.getId());

        if (res.isPresent()) {
            produtoRepository.save(produto);
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public void removerProduto(@PathVariable Long id){
        Optional res = produtoRepository.findById(id);

        if (res.isPresent()){
            produtoRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
