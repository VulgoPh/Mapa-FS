package com.mapa.model;

import com.mapa.exception.LivroException;
import com.mapa.extra.GerarISBN;

public class Livro {

    static int contador = 1;

    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String isbn = GerarISBN.gerar();

    public Livro() {
        this.id = contador++;
        this.isbn = GerarISBN.gerar();
    }

    public Livro(String titulo, String autor, int ano, String isbn) {
        this.id = contador++;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.isbn = GerarISBN.gerar();
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public void validar() throws LivroException {
        if (titulo == null || titulo.trim().isEmpty()) {
            contador--;
            throw new LivroException("O título é obrigatório.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            contador--;
            throw new LivroException("O autor é obrigatório.");
        }
        if (ano < 1000) {
            contador--;
            throw new LivroException("Ano inválido.");
        }
        if (ano > 2025) {
            contador--;
            throw new LivroException("Ano inválido.");
        }
    }
}