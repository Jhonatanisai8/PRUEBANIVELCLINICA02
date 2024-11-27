package com.jhonatan.controllers;

import com.jhonatan.models.Doctor;
import com.jhonatan.procesos.ProcesosVerAsignaciones;
import com.jhonatan.views.frmMenu;
import com.jhonatan.views.frmVerAsignacionesPacientesDoctor;

public class ControladorFormularioVerAsigacion {

    private frmVerAsignacionesPacientesDoctor frmAsignacionesPacientesDoctor;
    private frmMenu frmMenu1;

    public ControladorFormularioVerAsigacion(frmVerAsignacionesPacientesDoctor frmAsignacionesPacientesDoctor, frmMenu frmMenu1, Doctor doctor) {
        this.frmAsignacionesPacientesDoctor = frmAsignacionesPacientesDoctor;
        this.frmMenu1 = frmMenu1;
        ProcesosVerAsignaciones.presentarFormulario(this.frmMenu1.dskEscritorio, this.frmAsignacionesPacientesDoctor, doctor);
    }

}
