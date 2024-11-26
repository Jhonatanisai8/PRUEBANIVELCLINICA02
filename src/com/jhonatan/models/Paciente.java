package com.jhonatan.models;

import java.util.HashMap;

public class Paciente
        extends Persona {

    //PARA EL HISTORIAL MEDICO 
    private HashMap<String, String> historialMedico;

    public Paciente() {
    }

    public Paciente(String nombre, int edad, String genero, String identificacion) {
        super(nombre, edad, genero, identificacion);
        this.historialMedico = new HashMap<>();
    }

    //metodo para agregar al historial 
    public void agregarHistorial(String motivo, String detalle) {
        this.historialMedico.put(motivo, detalle);
    }

}
