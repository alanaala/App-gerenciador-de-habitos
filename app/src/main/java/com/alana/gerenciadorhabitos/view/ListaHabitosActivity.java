package com.alana.gerenciadorhabitos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.controller.HabitoController;
import com.alana.gerenciadorhabitos.model.Habito;

import java.util.ArrayList;

public class ListaHabitosActivity extends AppCompatActivity {

    private HabitoController controller;

    private LinearLayout layoutDiario, layoutSemanal, layoutMensal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_habitos);

        controller = new HabitoController(this);

        layoutDiario = findViewById(R.id.layoutDiarioLista);
        layoutSemanal = findViewById(R.id.layoutSemanalLista);
        layoutMensal = findViewById(R.id.layoutMensalLista);

        Button btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(v -> finish());

        carregarHabitos();
    }

    private void carregarHabitos() {
        layoutDiario.removeAllViews();
        layoutSemanal.removeAllViews();
        layoutMensal.removeAllViews();

        ArrayList<Habito> listaHabitos = controller.listarHabitos();

        for (Habito h : listaHabitos) {
            View item = LayoutInflater.from(this).inflate(R.layout.item_habito, null);

            TextView nome = item.findViewById(R.id.txtNomeHabito);
            TextView frequencia = item.findViewById(R.id.txtDescricaoHabito);

            nome.setText(h.getNome());
            frequencia.setText(h.getFrequencia());

            switch (h.getFrequencia()) {
                case "Diária":
                    item.setBackgroundColor(getResources().getColor(R.color.diario));
                    layoutDiario.addView(item);
                    break;
                case "Semanal":
                    item.setBackgroundColor(getResources().getColor(R.color.semanal));
                    layoutSemanal.addView(item);
                    break;
                case "Mensal":
                    item.setBackgroundColor(getResources().getColor(R.color.mensal));
                    layoutMensal.addView(item);
                    break;
                default:
                    item.setBackgroundColor(getResources().getColor(R.color.cinza));
            }

            item.setOnClickListener(v -> {
                Intent intent = new Intent(ListaHabitosActivity.this, DetalhesActivity.class);
                intent.putExtra("id", h.getId());
                startActivityForResult(intent, 1);
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            carregarHabitos();
        }
    }
}
