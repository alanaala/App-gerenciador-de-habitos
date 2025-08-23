package com.alana.gerenciadorhabitos.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.controller.HabitoController;
import com.alana.gerenciadorhabitos.model.Habito;
import com.alana.gerenciadorhabitos.model.HabitoNotificacao;

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

            HabitoNotificacao.exibirNotificacao(this, "Novo hábito criado!", "Você adicionou um novo hábito para acompanhar");

            editNome.setText("");
            editDescricao.setText("");
            spinnerFrequencia.setSelection(0);
        });

        btnVerLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaHabitosActivity.class);
            startActivity(intent);
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }
    }
}
