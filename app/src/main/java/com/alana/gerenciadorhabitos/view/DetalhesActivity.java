package com.alana.gerenciadorhabitos.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.controller.DbController;
import com.alana.gerenciadorhabitos.controller.HabitoController;
import com.alana.gerenciadorhabitos.model.Habito;

public class DetalhesActivity extends AppCompatActivity {

    private EditText editNome, editDescricao;
    private Spinner spinnerFrequencia;
    private HabitoController controller;
    private DbController dbController;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnVoltar = findViewById(R.id.btnVoltar);
        Button btnExcluir = findViewById(R.id.btnExcluir);
        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        spinnerFrequencia = findViewById(R.id.spinnerFrequencia);
        controller = new HabitoController(this);
        dbController = new DbController(this);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            String frequencia = spinnerFrequencia.getSelectedItem().toString().trim();

            Habito habitoAtualizado = new Habito(nome, descricao, frequencia);

            controller.atualizarHabito(habitoAtualizado);
            dbController.insert(nome,descricao, frequencia);
        });

        btnExcluir.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            String frequencia = spinnerFrequencia.getSelectedItem().toString().trim();

            Habito habitoAtualizado = new Habito(nome, descricao, frequencia);

            controller.removerHabito(habitoAtualizado);
            dbController.insert(nome,descricao, frequencia);
        });

        btnVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesActivity.this, ListaHabitosActivity.class);
            startActivity(intent);
        });

    }
}