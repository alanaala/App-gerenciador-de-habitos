package com.alana.gerenciadorhabitos.controller;

import android.content.Context;

import com.alana.gerenciadorhabitos.model.Habito;

import java.util.ArrayList;

public class HabitoController {

    private static ArrayList<Habito> listaHabitos = new ArrayList<>();
    private Habito habito;
    private Context context;

    public HabitoController(Context context) {
        this.context = context;
    }

    public void adicionarHabito(Habito habito) {
        listaHabitos.add(habito);
    }

    public ArrayList<Habito> listarHabitos() {
        return listaHabitos;
    }

    public Habito buscarPorId(int id) {
        for (Habito h : listaHabitos) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

    public void atualizarHabito(Habito habitoAtualizado) {
        for (int i = 0; i < listaHabitos.size(); i++) {
            if (listaHabitos.get(i).getId() == habitoAtualizado.getId()) {
                listaHabitos.set(i, habitoAtualizado);
                break;
            }
        }
    }

    public void removerHabito(Habito habitoAtualizado) {
        for (int i = 0; i < listaHabitos.size(); i++) {
            if (listaHabitos.get(i).getId() == habitoAtualizado.getId()) {
                listaHabitos.remove(i);
                break;
            }
        }
    }
}