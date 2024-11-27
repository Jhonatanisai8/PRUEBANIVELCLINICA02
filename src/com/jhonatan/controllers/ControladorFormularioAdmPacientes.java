package com.jhonatan.controllers;

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

    public ControladorFormularioAdmPacientes(frmAdministrarPaciente frmAdministrarPaciente, frmMenu frmMenu, Persona miPersona) {
        this.frmAdministrarPaciente = frmAdministrarPaciente;
        this.frmMenu = frmMenu;
        this.persona = miPersona;
        ProcesosFormularioPacientes.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarPaciente, this.persona);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
