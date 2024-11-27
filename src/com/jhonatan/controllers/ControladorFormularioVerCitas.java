package com.jhonatan.controllers;

import com.jhonatan.procesos.ProcesosFormularioVerCitas;
import com.jhonatan.views.frmMenu;
import com.jhonatan.views.frmVerCitas;

public class ControladorFormularioVerCitas {
    
    private frmVerCitas frmVerCitas;
    private frmMenu frmMenu;
    
    public ControladorFormularioVerCitas(frmVerCitas frmVerCitas, frmMenu frmMenu) {
        this.frmVerCitas = frmVerCitas;
        this.frmMenu = frmMenu;
        ProcesosFormularioVerCitas.presentarFormulario(this.frmMenu.dskEscritorio, this.frmVerCitas);
    }
    
}
