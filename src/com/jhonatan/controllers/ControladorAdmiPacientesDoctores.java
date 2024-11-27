package com.jhonatan.controllers;

import com.jhonatan.models.Doctor;
import com.jhonatan.procesos.ProcesosFormularioPacientesDoctores;
import com.jhonatan.views.frmAdministrarDoctorPacientes;
import com.jhonatan.views.frmMenu;

public class ControladorAdmiPacientesDoctores {
    
    private frmAdministrarDoctorPacientes frmAdministrarDoctorPacientes;
    private frmMenu frmMenu;
    private Doctor miDoctor;
    
    public ControladorAdmiPacientesDoctores(frmAdministrarDoctorPacientes frmAdministrarDoctorPacientes, frmMenu frmMenu, Doctor miDoctor) {
        this.frmAdministrarDoctorPacientes = frmAdministrarDoctorPacientes;
        this.frmMenu = frmMenu;
        this.miDoctor = miDoctor;
        ProcesosFormularioPacientesDoctores.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarDoctorPacientes, this.miDoctor);
        ProcesosFormularioPacientesDoctores.listarPacientes(this.frmAdministrarDoctorPacientes.jTable1);
    }
    
}
