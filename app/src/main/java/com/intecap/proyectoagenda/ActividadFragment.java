package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActividadFragment  extends Fragment {

   private SharedViewModel viewModel;
   private ActividadesAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_actividades, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.rcvActividades);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        List<String> listaActividades = new ArrayList<>();
        adapter = new ActividadesAdapter(listaActividades, viewModel);
        recyclerView.setAdapter(adapter);

        viewModel.getActividadFragment().observe(getViewLifecycleOwner(), lista -> {
            listaActividades.clear();
            listaActividades.addAll(lista);
            adapter.notifyDataSetChanged();
        });

        return view;
    }


}
