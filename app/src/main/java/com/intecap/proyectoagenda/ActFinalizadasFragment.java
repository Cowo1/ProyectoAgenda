package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActFinalizadasFragment extends Fragment {
    private ActFinalizadasAdapter adapter;

    private SharedViewModel viewModel;





    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
     View view =  inflater.inflate(R.layout.fragment_actfinalizadas, container, false);

     viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
     RecyclerView recyclerView = view.findViewById(R.id.rcvActFinalizada);
     recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

     List<String> actividadesFinalizadas = new ArrayList<>();
     adapter = new ActFinalizadasAdapter(actividadesFinalizadas);
     recyclerView.setAdapter(adapter);

        viewModel.getActividadesFinalizadas().observe(getViewLifecycleOwner(), actividades -> {
            actividadesFinalizadas.clear();
            actividadesFinalizadas.addAll(actividades);
            adapter.notifyDataSetChanged();
        });



        return view;

    }

}
