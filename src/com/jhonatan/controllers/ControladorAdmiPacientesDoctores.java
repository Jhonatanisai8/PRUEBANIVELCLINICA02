package com.jhonatan.controllers;

import com.jhonatan.models.Doctor;
import com.jhonatan.procesos.ProcesosFormularioPacientesDoctores;
import com.jhonatan.views.frmAdministrarDoctorPacientes;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAdmiPacientesDoctores
        implements ActionListener {
    
    private frmAdministrarDoctorPacientes frmAdministrarDoctorPacientes;
    private frmMenu frmMenu;
    private Doctor miDoctor;
    
    public ControladorAdmiPacientesDoctores(frmAdministrarDoctorPacientes frmAdministrarDoctorPacientes, frmMenu frmMenu, Doctor miDoctor) {
        this.frmAdministrarDoctorPacientes = frmAdministrarDoctorPacientes;
        this.frmMenu = frmMenu;
        this.miDoctor = miDoctor;
        this.frmAdministrarDoctorPacientes.btnAsignar.addActionListener(this);
        ProcesosFormularioPacientesDoctores.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarDoctorPacientes, this.miDoctor);
        ProcesosFormularioPacientesDoctores.listarPacientes(this.frmAdministrarDoctorPacientes.tblDatos);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmAdministrarDoctorPacientes.btnAsignar) {
            ProcesosFormularioPacientesDoctores.asigarPacientes(miDoctor, frmAdministrarDoctorPacientes);
        }
    }
    
}
