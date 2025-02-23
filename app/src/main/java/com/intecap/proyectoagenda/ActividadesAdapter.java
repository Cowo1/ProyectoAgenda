package com.intecap.proyectoagenda;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActividadesAdapter extends RecyclerView.Adapter<ActividadesAdapter.ActividadViewHolder> {
    private final List<String> actividades;
    private final SharedViewModel viewModel;

    public ActividadesAdapter(List<String> actividades, SharedViewModel viewModel) {
        this.actividades = actividades;
        this.viewModel = viewModel;


    }



    @NonNull
    @Override
    public ActividadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actividad, parent,false);

        return new ActividadViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ActividadViewHolder holder, int position) {
        String actividad = actividades.get(position);
        holder.txtContenido.setText(actividad);

        holder.cbActividad.setOnCheckedChangeListener(null);
        holder.cbActividad.setChecked(false);

        holder.cbActividad.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                holder.cbActividad.setOnClickListener(v -> {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Eliminar nota")
                            .setMessage("¿Deseas finalizar esta actividad?")
                            .setPositiveButton("Eliminar", (dialog, wich)->{
                                actividades.remove(position);
                                notifyItemRemoved(position);

                                if(viewModel != null){
                                    viewModel.agregarActividadFinalizada(actividad);
                                    viewModel.elimpiarActividades(actividad);

                                    NavController navController = Navigation.findNavController(holder.itemView);
                                    navController.navigate(R.id.action_finalizada);
                                }
                                    })
                            .setNegativeButton("Cancelar", (dialog, wich) ->{
                                holder.cbActividad.setChecked(false);
                            })
                            .show();
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return actividades.size();
    }

   static class ActividadViewHolder extends RecyclerView.ViewHolder {
        TextView txtContenido;
        CheckBox cbActividad;
      public ActividadViewHolder(@NonNull View itemView) {
           super(itemView);
           txtContenido = itemView.findViewById(R.id.txtContenido);
           cbActividad = itemView.findViewById(R.id.cbActividad);


        }
    }




}
