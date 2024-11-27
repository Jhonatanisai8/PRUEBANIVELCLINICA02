package com.jhonatan.controllers;

import com.jhonatan.procesos.ProcesosFormularioAdministrarCita;
import com.jhonatan.views.frmAdministrarCita;
import com.jhonatan.views.frmMenu;

public class ControladorFormularioCita {
    
    private frmAdministrarCita frmAdministrarCita;
    private frmMenu frmMenu;
    
    public ControladorFormularioCita(frmAdministrarCita frmAdministrarCita, frmMenu frmMenu) {
        this.frmAdministrarCita = frmAdministrarCita;
        this.frmMenu = frmMenu;
        ProcesosFormularioAdministrarCita.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarCita);
    }
    
}
