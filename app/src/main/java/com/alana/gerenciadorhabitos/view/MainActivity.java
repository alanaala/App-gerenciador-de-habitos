package com.alana.gerenciadorhabitos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.controller.HabitoController;
import com.alana.gerenciadorhabitos.model.Habito;

public class MainActivity extends AppCompatActivity {

    private EditText editNome, editDescricao;
    private Spinner spinnerFrequencia;
    private HabitoController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        spinnerFrequencia = findViewById(R.id.spinnerFrequencia);
        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnVerLista = findViewById(R.id.btnVerLista);

        controller = new HabitoController(this);

        // Spinner Frequência
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.frequencia_array, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrequencia.setAdapter(adapter);

        btnSalvar.setOnClickListener(v -> {
            String nome = editNome.getText().toString().trim();
            String descricao = editDescricao.getText().toString().trim();
            String frequencia = spinnerFrequencia.getSelectedItem().toString();

            if (nome.isEmpty()) {
                Toast.makeText(this, "Por favor, informe o nome do hábito", Toast.LENGTH_SHORT).show();
                return;
            }

            Habito novoHabito = new Habito(nome, descricao, frequencia);
            controller.adicionarHabito(novoHabito);

            Toast.makeText(this, "Hábito salvo!", Toast.LENGTH_SHORT).show();

            editNome.setText("");
            editDescricao.setText("");
            spinnerFrequencia.setSelection(0);
        });

        btnVerLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaHabitosActivity.class);
            startActivity(intent);
        });
    }
}
