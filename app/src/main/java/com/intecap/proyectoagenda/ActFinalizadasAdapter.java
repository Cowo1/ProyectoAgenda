package com.intecap.proyectoagenda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ActFinalizadasAdapter extends RecyclerView.Adapter<ActFinalizadasAdapter.ActividadViewHolder> {
private final List<String> actividadesFinalizadas;

    public ActFinalizadasAdapter(List<String> actividadesFinalizadas) {
        this.actividadesFinalizadas = actividadesFinalizadas;
    }


    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actfinalizada,parent,false);
       return new ActividadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActFinalizadasAdapter.ActividadViewHolder holder, int position) {
    String actividad = actividadesFinalizadas.get(position);
    holder.txtContenido.setText(actividad);
    }

    @Override
    public int getItemCount() {
        return actividadesFinalizadas.size();
    }
    static class ActividadViewHolder extends RecyclerView.ViewHolder {
        TextView txtContenido;
        ImageView imgCheckFinalizado;
        public ActividadViewHolder(@NonNull View itemView) {
            super(itemView);
            txtContenido = itemView.findViewById(R.id.txtContenidoF);
            imgCheckFinalizado = itemView.findViewById(R.id.imgCheckFinalizado);

        }
    }
}
