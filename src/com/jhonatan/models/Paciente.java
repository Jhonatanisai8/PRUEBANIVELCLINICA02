package com.jhonatan.models;

import java.util.HashMap;

public class Paciente
        extends Persona {

    //PARA EL HISTORIAL MEDICO 
    private HashMap<String, String> historialMedico;

    public Paciente() {
        this.historialMedico = new HashMap<>();
    }

    public Paciente(String nombre, int edad, String genero, String identificacion) {
        super(nombre, edad, genero, identificacion);
    }

    //metodo para agregar al historial 
    public void agregarHistorial(String motivo, String detalle) {
        this.historialMedico.put(motivo, detalle);
    }

    public HashMap<String, String> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(HashMap<String, String> historialMedico) {
        this.historialMedico = historialMedico;
    }

}
