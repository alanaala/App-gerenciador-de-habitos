package com.alana.gerenciadorhabitos.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alana.gerenciadorhabitos.model.Db;
import com.alana.gerenciadorhabitos.model.Habito;

import java.util.ArrayList;

public class DbController {

    private Db dbHelper;

    public DbController(Context context) {
        dbHelper = new Db(context);
    }

    public void insert(Habito h) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Db.NOME, h.getNome());
        values.put(Db.DESCRICAO, h.getDescricao());
        values.put(Db.FREQUENCIA, h.getFrequencia());
        values.put("favorito", h.isFavorito() ? 1 : 0);
        db.insert(Db.TABLE, null, values);
        db.close();
    }

    public ArrayList<Habito> listarHabitos() {
        ArrayList<Habito> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Db.TABLE, null, null, null, null, null, Db.ID + " ASC");
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(Db.ID));
                String nome = cursor.getString(cursor.getColumnIndexOrThrow(Db.NOME));
                String descricao = cursor.getString(cursor.getColumnIndexOrThrow(Db.DESCRICAO));
                String frequencia = cursor.getString(cursor.getColumnIndexOrThrow(Db.FREQUENCIA));
                boolean favorito = cursor.getInt(cursor.getColumnIndexOrThrow("favorito")) == 1;
                lista.add(new Habito(id, nome, descricao, frequencia, favorito));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public void atualizar(Habito h) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Db.NOME, h.getNome());
        values.put(Db.DESCRICAO, h.getDescricao());
        values.put(Db.FREQUENCIA, h.getFrequencia());
        values.put("favorito", h.isFavorito() ? 1 : 0);
        db.update(Db.TABLE, values, Db.ID + "=?", new String[]{String.valueOf(h.getId())});
        db.close();
    }

    public void remover(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Db.TABLE, Db.ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
