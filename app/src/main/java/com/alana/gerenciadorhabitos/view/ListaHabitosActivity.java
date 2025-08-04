package com.alana.gerenciadorhabitos.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.controller.DbController;
import com.alana.gerenciadorhabitos.controller.HabitoController;
import com.alana.gerenciadorhabitos.model.Habito;

import java.util.ArrayList;

public class ListaHabitosActivity extends AppCompatActivity {

    private ArrayList<Habito> habitos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_habitos);

        ListView listView = findViewById(R.id.listViewHabitos);
        Button btnNovoHabito = findViewById(R.id.btnNovoHabito);
        HabitoController controller = new HabitoController(this);
        DbController dbController = new DbController(this);

        habitos = controller.listarHabitos();
        ArrayList<String> dadosHabitos = new ArrayList<>();

        for (Habito h : habitos) {
            dadosHabitos.add(h.getNome().trim());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dadosHabitos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Habito habitoSelecionado = habitos.get(position);
            Intent intent = new Intent(ListaHabitosActivity.this, DetalhesActivity.class);
            intent.putExtra("id", habitoSelecionado.getId());
            startActivity(intent);

        });

        btnNovoHabito.setOnClickListener(v -> {
            Intent intent = new Intent(ListaHabitosActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}