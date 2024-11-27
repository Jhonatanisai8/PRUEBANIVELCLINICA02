package com.jhonatan.controllers;

import com.jhonatan.models.Paciente;
import com.jhonatan.models.Persona;
import com.jhonatan.procesos.ProcesosFormularioPacientes;
import com.jhonatan.views.frmAdministrarPaciente;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFormularioAdmPacientes
        implements ActionListener {

    private frmAdministrarPaciente frmAdministrarPaciente;
    private final frmMenu frmMenu;
    private Persona persona;
    private Paciente paciente;

    public ControladorFormularioAdmPacientes(frmAdministrarPaciente frmAdministrarPaciente, frmMenu frmMenu, Persona miPersona) {
        this.frmAdministrarPaciente = frmAdministrarPaciente;
        this.frmMenu = frmMenu;
        this.persona = miPersona;
        this.frmAdministrarPaciente.btnGuardar.addActionListener(this);
        ProcesosFormularioPacientes.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarPaciente, this.persona);
        ProcesosFormularioPacientes.mostrarHistorialPaciente(this.frmAdministrarPaciente, this.persona.getNombre());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmAdministrarPaciente.btnGuardar) {
            this.paciente = new Paciente();
            //establecemos los atributos 
            this.paciente.setNombre(this.persona.getNombre());
            this.paciente.setEdad(this.persona.getEdad());
            this.paciente.setGenero(this.persona.getGenero());
            this.paciente.setIdentificacion(this.persona.getIdentificacion());
            ProcesosFormularioPacientes.leerDatosHistorial(frmAdministrarPaciente, this.paciente);
            ProcesosFormularioPacientes.guardarHistorialEnArchivo(paciente, this.paciente.getHistorialMedico());
            ProcesosFormularioPacientes.mostrarHistorialPaciente(this.frmAdministrarPaciente, this.paciente.getNombre());
        }
    }

}
