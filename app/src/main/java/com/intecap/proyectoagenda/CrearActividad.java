package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class CrearActividad extends Fragment {
    private SharedViewModel viewModel;
    private final MutableLiveData<List<Item>> actividades = new MutableLiveData<>(new ArrayList<>());
    private List<String> listaActividades;
    private ActividadesAdapter adapter;
    private boolean agregarLista;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        View view = inflater.inflate(R.layout.fragment_crearactividad, container, false);

        EditText etTitulo = view.findViewById(R.id.etTitulo);
        EditText etDescripcion = view.findViewById(R.id.etDescripcion);

        Button btnAgregarActividad = view.findViewById(R.id.btnAgregar);

        Button btnVerActividad = view.findViewById(R.id.btnVerActividad);

      listaActividades = new ArrayList<>();







        btnAgregarActividad.setOnClickListener(v -> {
            String actividad = etTitulo.getText().toString();


            if (!actividad.isEmpty()) {
                if (viewModel != null) {
                    Log.d("CrearActividad", "ViewModel no es null, agregando actividad");
                    viewModel.agregarActividad(actividad);
                    etTitulo.setText("");
                }
            }
        });


        btnVerActividad.setOnClickListener(v -> {
            NavHostFragment.findNavController(CrearActividad.this)
                    .navigate(R.id.action_crearActividad);
        });
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_volverA);
            }
        });


        return view;
    }


}
