package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class DetalleNotaFragment  extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.nota_detalle, container, false);
        TextView txtTituloDetalle = view.findViewById(R.id.txtTituloD);
        TextView txtContenidoDetalle = view.findViewById(R.id.txtContenidoD);
    Bundle args = getArguments();
    if(args != null){
        String titulo = args.getString("titulo");
        String contenido = args.getString("contenido");

        txtTituloDetalle.setText(titulo);
        txtContenidoDetalle.setText(contenido);
    }
    requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_volverNotas);
        }
    });


    return view;
    }
}
