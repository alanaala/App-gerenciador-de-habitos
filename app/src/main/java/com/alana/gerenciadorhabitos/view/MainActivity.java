package com.alana.gerenciadorhabitos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.controller.DbController;
import com.alana.gerenciadorhabitos.controller.HabitoController;
import com.alana.gerenciadorhabitos.model.Habito;

public class MainActivity extends AppCompatActivity {
    private EditText editNome, editDescricao;
    private Spinner spinnerFrequencia;
    private HabitoController controller;
    private DbController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        spinnerFrequencia = findViewById(R.id.spinnerFrequencia);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        controller = new HabitoController(this);
        dbController = new DbController(this);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            String frequencia = spinnerFrequencia.getSelectedItem().toString().trim();

            if (nome.isEmpty() || descricao.isEmpty()) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            Habito novoHabito = new Habito(nome, descricao, frequencia);
            controller.adicionarHabito(novoHabito);
            dbController.insert(nome, descricao, frequencia);
            startActivity(new Intent(MainActivity.this, ListaHabitosActivity.class));
            Toast.makeText(this, "Hábito criado!", Toast.LENGTH_SHORT).show();
        });

    }
}