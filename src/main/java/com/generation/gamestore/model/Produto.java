package com.generation.gamestore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O título do jogo é obrigatório")
    @Size(min = 1, max = 100, message = "O título deve ter entre 1 e 100 caracteres")
    private String titulo;
    @NotNull
    @Size(min = 10, max = 1000, message = "O tamanho da sinopse não pode ultrapssar 1000 caracteres")
    private String sinopse;
    @NotNull
    private String midia;
    @NotNull
    private LocalDate dataLancamento;
    @UpdateTimestamp
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    @JsonIgnore
    private Usuario usuario;

    public Produto(Long id, String titulo, String sinopse, String midia, LocalDate dataLancamento) {
        this.id = id;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.midia = midia;
        this.dataLancamento = dataLancamento;
    }

    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getMidia() { return midia;  }

    public void setMidia(String midia) { this.midia = midia; }

    public LocalDate getDataLancamento() { return dataLancamento;  }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Categoria getCategoria() { return categoria; }

    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
