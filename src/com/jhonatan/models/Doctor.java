package com.jhonatan.models;

import java.util.ArrayList;
import java.util.List;

public class Doctor
        extends Persona {

    private String especialidad;
    private String numeroLicencia;
    private List<Paciente> listaPacientes;

    public Doctor(String especialidad, String numeroLicencia) {
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.listaPacientes = new ArrayList<>();
    }

    public Doctor(String especialidad, String numeroLicencia, String nombre, int edad, String genero, String identificacion) {
        super(nombre, edad, genero, identificacion);
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
    }

    //metodos
    public void agregarPaciente(Paciente paciente) {
        this.listaPacientes.add(paciente);
        Mensaje.M1("Paciente agregado");
    }
    
    
}
