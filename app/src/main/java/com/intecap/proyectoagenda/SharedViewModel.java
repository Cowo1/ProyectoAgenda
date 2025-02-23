package com.intecap.proyectoagenda;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<List<String>> actividades = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<String>> actividadesFinalizadas = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<String>> notas = new MutableLiveData<>(new ArrayList<>());
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public MutableLiveData<List<String>> getNotas() {
        return notas;
    }

    public LiveData<List<String>> getActividadFragment() {

        return actividades;
    }

    public LiveData<List<String>> getActividadesFinalizadas() {
        return actividadesFinalizadas;

    }

    public void agregarActividad(String actividad) {
        List<String> listaActual = actividades.getValue();


        if (listaActual != null) {
            listaActual = new ArrayList<>();


        }
        listaActual.add(actividad);
        actividades.setValue(listaActual);
    }


    public void agregarActividadFinalizada(String actividad) {
        List<String> listaActual = actividadesFinalizadas.getValue();
        if (listaActual == null) {
            listaActual = new ArrayList<>();

        }
        listaActual.add(actividad);
        actividadesFinalizadas.setValue(listaActual);
    }

    public void Notas(String titulo, String nota) {
        List<String> listaActual = notas.getValue();
        if (listaActual == null) {
            listaActual = new ArrayList<>();


        }
        listaActual.add(titulo + ": " + nota);
        notas.setValue(listaActual);
    }

    public void cargarNotas() {
        db.collection("notas")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<String> listaNotas = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String titulo = document.getString("titulo");
                            String contenido = document.getString("contenido");
                            listaNotas.add(titulo + ": " + contenido);

                        }
                        notas.setValue(listaNotas);
                    }
                });
    }

    public void agregarNota(String titulo, String contenido) {
        Map<String, Object> nota = new HashMap<>();
        nota.put("titulo", titulo);
        nota.put("contenido", contenido);

        db.collection("notas").add(nota)
                .addOnSuccessListener(documentReference -> {
                    cargarNotas();
                });
    }

    public void eliminarNotas(String notaCompleta) {
        String[] partes = notaCompleta.split(": ", 2);
        String titulo = partes[0];

        db.collection("notas")
                .whereEqualTo("titulo", titulo)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            db.collection("notas").document(document.getId()).delete();
                        }
                        cargarNotas();
                    }
                });
    }
    public LiveData<List<String>> getNota(){
        return notas;
    }


    public void elimpiarActividades(String actividad) {
        List<String> lista = actividades.getValue();
        if (lista != null) {
            lista.remove(actividad);
            actividades.setValue(lista);
        }
    }

    public void eliminarNota(String nota) {
        List<String> listaActual = notas.getValue();
        if (listaActual != null) {
            listaActual.remove(nota);
            notas.setValue(listaActual);
        }
    }


}
