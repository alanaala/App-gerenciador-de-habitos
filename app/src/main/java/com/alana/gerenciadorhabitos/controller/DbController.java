package com.alana.gerenciadorhabitos.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.alana.gerenciadorhabitos.model.Db;

public class DbController {

    private SQLiteDatabase db;

    private Db register;

    public DbController(Context context) {
        register = new Db(context);
    }

    public void insert(String nome, String descricao, String frequencia) {
        ContentValues data;
        data = new ContentValues();
        data.put(Db.NOME, nome);
        data.put(Db.DESCRICAO, descricao);
        data.put(Db.FREQUENCIA, frequencia);
        data.get(Db.TABLE);
    }

}
