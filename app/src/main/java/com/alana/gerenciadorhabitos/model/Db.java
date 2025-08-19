package com.alana.gerenciadorhabitos.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {

    private static final String DB_NAME = "Gerenciador";
    private static final int VERSION = 1;
    public static final String TABLE = "Habito";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String DESCRICAO = "descricao";
    public static final String FREQUENCIA = "frequencia";

    public Db(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criarDB = "CREATE TABLE " + TABLE +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOME + " TEXT, " +
                DESCRICAO + " TEXT, " +
                FREQUENCIA + " TEXT, " +
                "favorito INTEGER DEFAULT 0)";
        db.execSQL(criarDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}
