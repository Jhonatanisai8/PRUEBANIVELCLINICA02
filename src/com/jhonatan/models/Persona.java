package com.jhonatan.models;

public class Persona {

    private String nombre;
    private int edad;
    private String genero;
    private String identificacion;

    public Persona() {

    }

    public Persona(String nombre, int edad, String genero, String identificacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return "Nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", identificacion=" + identificacion + '}';
    }

}
