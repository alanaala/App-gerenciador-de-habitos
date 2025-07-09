package com.alana.gerenciadorhabitos.model;

import java.util.ArrayList;
import java.util.Date;

public class Habito {
    private static int contadorId = 0;

    private int id;
    private String nome;
    private String descricao;
    private String frequencia;
    private ArrayList<Long> diasCumpridos;

    public Habito(String nome, String descricao, String frequencia) {
        this.id = ++contadorId;
        this.nome = nome;
        this.descricao = descricao;
        this.frequencia = frequencia;
        this.diasCumpridos = new ArrayList<>();
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

    public ArrayList<Long> getDiasCumpridos() {
        return diasCumpridos;
    }

    public int getDiasCumpridosCount() {
        return diasCumpridos.size();
    }

    private long getTimestampHoje() {
        Date now = new Date();

        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        return now.getTime();
    }
}