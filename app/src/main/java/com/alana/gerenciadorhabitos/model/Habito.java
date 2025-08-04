package com.alana.gerenciadorhabitos.model;

import java.util.ArrayList;
import java.util.Date;

public class Habito {
    private static int contadorId = 0;

    private int id;
    private String nome;
    private String descricao;
    private String frequencia;

    public Habito(String nome, String descricao, String frequencia) {
        this.id = ++contadorId;
        this.nome = nome;
        this.descricao = descricao;
        this.frequencia = frequencia;
    }

    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getFrequencia() {
        return frequencia;
    }
}