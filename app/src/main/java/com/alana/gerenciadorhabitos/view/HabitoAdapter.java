package com.alana.gerenciadorhabitos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alana.gerenciadorhabitos.R;
import com.alana.gerenciadorhabitos.model.Habito;

import java.util.ArrayList;

public class HabitoAdapter extends RecyclerView.Adapter<HabitoAdapter.HabitoViewHolder> {

    private ArrayList<Habito> listaHabitos;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Habito habito);
    }

    public HabitoAdapter(Context context, ArrayList<Habito> listaHabitos, OnItemClickListener listener) {
        this.context = context;
        this.listaHabitos = listaHabitos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HabitoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_habito, parent, false);
        return new HabitoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitoViewHolder holder, int position) {
        Habito habito = listaHabitos.get(position);
        holder.txtNome.setText(habito.getNome());
        holder.txtFrequencia.setText(habito.getFrequencia());

        switch (habito.getFrequencia()) {
            case "Diária":
                holder.layout.setBackgroundColor(context.getResources().getColor(R.color.diario));
                break;
            case "Semanal":
                holder.layout.setBackgroundColor(context.getResources().getColor(R.color.semanal));
                break;
            case "Mensal":
                holder.layout.setBackgroundColor(context.getResources().getColor(R.color.mensal));
                break;
            default:
                holder.layout.setBackgroundColor(context.getResources().getColor(R.color.cinza));
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(habito));
    }

    @Override
    public int getItemCount() {
        return listaHabitos.size();
    }

    public static class HabitoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNome, txtFrequencia;
        LinearLayout layout;

        public HabitoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNomeHabito);
            txtFrequencia = itemView.findViewById(R.id.txtDescricaoHabito);
            layout = itemView.findViewById(R.id.layout_habito);
        }
    }
}
