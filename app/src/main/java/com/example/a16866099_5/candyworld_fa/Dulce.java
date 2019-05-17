package com.example.a16866099_5.candyworld_fa;

public class Dulce {
    private String nombre;
    private String descripcion;
    private int icono;

    public Dulce(String nombre, String descripcion, int icono) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icono = icono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }

    @Override
    public String toString() {
        return nombre + "\n" + descripcion;
    }
}
