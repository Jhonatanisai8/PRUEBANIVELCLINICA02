package com.jhonatan.models;

import java.time.LocalTime;
import java.util.Date;

public class Cita {

    private Paciente paciente;
    private Doctor doctor;
    private Date fecha;
    private LocalTime hora;
    private String motivo;

    public Cita() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return fecha;
    }

    public void setDate(Date date) {
        this.fecha = date;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return "===CITA==="
                + "\n =DATOS DEL PACIENTE= "
                + "\n Nombre Paciente: " + paciente.getNombre()
                + "\n Edad: " + paciente.getEdad()
                + "\n Genero: " + paciente.getGenero()
                + "\n Identificacion: " + paciente.getIdentificacion()
                + "=DATOS DEL DOCTOR="
                + "\n Nombre Paciente: " + doctor.getNombre()
                + "\n Edad: " + doctor.getEdad()
                + "\n Genero: " + doctor.getGenero()
                + "\n Identificacion: " + doctor.getIdentificacion()
                + "\n Especialidad: " + doctor.getEspecialidad()
                + "\n Numero de Licencia: " + doctor.getNumeroLicencia()
                + "\n Fecha: " + fecha
                + " \nHora: " + hora
                + "\n Motivo: " + motivo;
    }

}
