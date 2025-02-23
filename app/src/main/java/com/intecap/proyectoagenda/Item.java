package com.intecap.proyectoagenda;

public class Item {
    private String contenido;
    private boolean isChecked;

    public Item(String contenidom, boolean isChecked){
        this.contenido = contenido;
        this.isChecked = isChecked;

    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
