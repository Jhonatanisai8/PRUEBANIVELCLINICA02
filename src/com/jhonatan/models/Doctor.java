package com.jhonatan.models;

import java.util.ArrayList;
import java.util.List;

public class Doctor
        extends Persona {

    private String especialidad;
    private String numeroLicencia;
    private List<Paciente> listaPacientes;

    public Doctor() {
        this.listaPacientes = new ArrayList<>();
    }

    public Doctor(String especialidad, String numeroLicencia) {
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.listaPacientes = new ArrayList<>();
    }

    public Doctor(String especialidad, String numeroLicencia, String nombre, int edad, String genero, String identificacion) {
        super(nombre, edad, genero, identificacion);
        this.especialidad = especialidad;
        this.numeroLicencia = numeroLicencia;
        this.listaPacientes = new ArrayList<>();
    }

    //metodos
    public void agregarPaciente(Paciente paciente) {
        this.listaPacientes.add(paciente);
        Mensaje.M1("Paciente agregado");
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public void setListaPacientes(List<Paciente> listaPacientes) {
        this.listaPacientes = listaPacientes;
    }

}
