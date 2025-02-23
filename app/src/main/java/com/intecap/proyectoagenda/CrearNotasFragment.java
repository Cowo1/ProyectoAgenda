package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class CrearNotasFragment extends Fragment {
    private SharedViewModel viewModel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        View view = inflater.inflate(R.layout.fragment_crearnota,container,false);

        EditText txtTitulo = view.findViewById(R.id.txtTitulo);
        EditText txtContenido = view.findViewById(R.id.txtContenidoN);
        Button btnGuardarNota = view.findViewById(R.id.btnGuardarNota);

        btnGuardarNota.setOnClickListener(v ->{
            String titulo = txtTitulo.getText().toString();
            String contenido = txtContenido.getText().toString();
            if(!titulo.isEmpty() && !contenido.isEmpty()){
                if(viewModel != null){
                    viewModel.agregarNota(titulo,contenido);
                    txtTitulo.setText("");
                    txtContenido.setText("");
                }
            }
                    NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.actionGuardar);
                }
                );



            requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                @Override
                public void handleOnBackPressed() {
                    NavController navController = Navigation.findNavController(view);
                    navController.navigate(R.id.action_volverN);
                }
            });



        return view;

    }



}
