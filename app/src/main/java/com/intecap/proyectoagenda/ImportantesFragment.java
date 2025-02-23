package com.intecap.proyectoagenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ImportantesFragment extends Fragment {

    public ImportantesFragment(){

    }

    public static ImportantesFragment newInstance(){
        ImportantesFragment Ifragment = new ImportantesFragment();
        return Ifragment;
    }



    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        if(getArguments() != null){

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_importantes, container, false);
    }
}
