package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotasFragment extends Fragment {
    private NotasAdapter adapter;
    private SharedViewModel viewModel;

    public NotasFragment() {

    }

    public static NotasFragment newInstance() {
        NotasFragment Nfragment = new NotasFragment();
        return Nfragment;
    }

    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        if (getArguments() != null) {

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notas, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel.cargarNotas();

        Button btnAgregarNota = view.findViewById(R.id.btnAgregarNota);

        RecyclerView recyclerView = view.findViewById(R.id.rcvNotas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> listaNotas = new ArrayList<>();
        adapter = new NotasAdapter(listaNotas, viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getNota().observe(getViewLifecycleOwner(), lista -> {
            adapter.actualizarNotas(lista);
        });
        btnAgregarNota.setOnClickListener(v -> {
            NavHostFragment.findNavController(NotasFragment.this).navigate(R.id.action_notas);

        });
        return view;


    }

}
