package com.alana.gerenciadorhabitos.controller;

import android.content.Context;

import com.alana.gerenciadorhabitos.model.Habito;

import java.util.ArrayList;

public class HabitoController {

    private DbController dbController;

    public HabitoController(Context context) {
        dbController = new DbController(context);
    }

    public void adicionarHabito(Habito h) {
        dbController.insert(h);
    }

    public ArrayList<Habito> listarHabitos() {
        return dbController.listarHabitos();
    }

    public void atualizarHabito(Habito h) {
        dbController.atualizar(h);
    }

    public void removerHabito(int id) {
        dbController.remover(id);
    }
    public Habito buscarPorId(int id) {
        for (Habito h : listarHabitos()) {
            if (h.getId() == id) {
                return h;
            }
        }
        return null;
    }

}
