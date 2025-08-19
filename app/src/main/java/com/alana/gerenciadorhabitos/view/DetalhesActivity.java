package com.alana.gerenciadorhabitos.view;

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

public class DetalhesActivity extends AppCompatActivity {

    private EditText editNome, editDescricao;
    private Spinner spinnerFrequencia;
    private HabitoController controller;
    private Habito habitoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        editNome = findViewById(R.id.editNome);
        editDescricao = findViewById(R.id.editDescricao);
        spinnerFrequencia = findViewById(R.id.spinnerFrequencia);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        Button btnExcluir = findViewById(R.id.btnExcluir);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        controller = new HabitoController(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.frequencia_array, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrequencia.setAdapter(adapter);

        int id = getIntent().getIntExtra("id", -1);
        habitoSelecionado = controller.buscarPorId(id);

        if (habitoSelecionado != null) {
            editNome.setText(habitoSelecionado.getNome());
            editDescricao.setText(habitoSelecionado.getDescricao());

            String[] frequencias = getResources().getStringArray(R.array.frequencia_array);
            for (int i = 0; i < frequencias.length; i++) {
                if (frequencias[i].equals(habitoSelecionado.getFrequencia())) {
                    spinnerFrequencia.setSelection(i);
                    break;
                }
            }
        }

        btnSalvar.setOnClickListener(v -> {
            if (habitoSelecionado != null) {
                habitoSelecionado.setNome(editNome.getText().toString().trim());
                habitoSelecionado.setDescricao(editDescricao.getText().toString().trim());
                habitoSelecionado.setFrequencia(spinnerFrequencia.getSelectedItem().toString());

                controller.atualizarHabito(habitoSelecionado);

                Toast.makeText(this, "Hábito atualizado!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnExcluir.setOnClickListener(v -> {
            if (habitoSelecionado != null) {
                controller.removerHabito(habitoSelecionado.getId());

                Toast.makeText(this, "Hábito removido!", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            }
        });

        btnVoltar.setOnClickListener(v -> finish());
    }
}
