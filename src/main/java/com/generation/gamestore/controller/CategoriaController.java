package com.generation.gamestore.controller;

import com.generation.gamestore.model.Categoria;
import com.generation.gamestore.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll(){

        return ResponseEntity.ok(categoriaRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable Long id){
        Optional<Categoria> resposta = categoriaRepository.findById(id);
        if (resposta.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(resposta.get());
    }

    @GetMapping("/nome/{catNome}")
    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String catNome){
        List<Categoria> resposta = categoriaRepository.findAllByCatNomeIsContainingIgnoreCase(catNome);

        if (resposta.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.ok(resposta);
    }
    @PostMapping
    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }

    @PutMapping
    public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){
        Optional res = categoriaRepository.findById(categoria.getId());
        if (res.isPresent()){
            categoriaRepository.save(categoria);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @DeleteMapping
    public void removerCategoria(@PathVariable Long id){
        Optional res = categoriaRepository.findById(id);

        if (res.isPresent()){
            categoriaRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
