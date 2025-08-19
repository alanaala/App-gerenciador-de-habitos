package com.alana.gerenciadorhabitos.model;

public class Habito {

    private int id;
    private String nome;
    private String descricao;
    private String frequencia;
    private boolean favorito;

    public Habito(String nome, String descricao, String frequencia) {
        this.nome = nome;
        this.descricao = descricao;
        this.frequencia = frequencia;
        this.favorito = false;
    }

    public Habito(int id, String nome, String descricao, String frequencia, boolean favorito) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.frequencia = frequencia;
        this.favorito = favorito;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getFrequencia() { return frequencia; }
    public boolean isFavorito() { return favorito; }
    public void setFavorito(boolean favorito) { this.favorito = favorito; }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }
}
