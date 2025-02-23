package com.intecap.proyectoagenda;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotaViewHolder> {
    private final List<String> notas;
    private final SharedViewModel viewModel;

    public NotasAdapter(List<String> notas, SharedViewModel viewModel) {
        this.notas = notas;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public NotasAdapter.NotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notas, parent, false);

        return new NotaViewHolder(view);

    }
    public void actualizarNotas(List<String> nuevasNotas) {
        notas.clear();
        notas.addAll(nuevasNotas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull NotasAdapter.NotaViewHolder holder, int position) {
        String notaCompleta = notas.get(position);
       String[] partes = notaCompleta.split(": ", 2);
       String titulo = partes.length > 0? partes[0]: "Sin titulo";
       String contenidoN= partes.length > 1 ? partes[1]: "sin Contenido";

       holder.txtTitulo.setText(titulo);


       holder.itemView.setOnClickListener(v ->{
           Bundle bundle = new Bundle();
           bundle.putString("titulo", titulo);
           bundle.putString("contenido",contenidoN);

           NavController navController = Navigation.findNavController(v);
           navController.navigate(R.id.action_detalle, bundle);
       });

       holder.itemView.setOnLongClickListener(v -> {
           new AlertDialog.Builder(v.getContext())
                   .setTitle("Eliminar nota")
                   .setMessage("¿Estas seguro de que deseas eliminar esta nota?")
                   .setPositiveButton("Eliminar",(dialog, wich)->{
                       notas.remove(position);
                       notifyItemRemoved(position);

                       if(viewModel != null){
                           viewModel.eliminarNotas(notaCompleta);
                       }
                   })
                   .setNegativeButton("Cancelar", null)
                   .show();

           return true;

       });
    }


    @Override
    public int getItemCount() {
      return notas.size();
    }

   public static class NotaViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;

        public NotaViewHolder(@NonNull View itemView) {
            super(itemView);
           txtTitulo = itemView.findViewById(R.id.txtTitulo);

        }
    }
}
