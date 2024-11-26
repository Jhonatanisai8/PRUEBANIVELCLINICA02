package com.jhonatan.controllers;

import com.jhonatan.models.Persona;
import com.jhonatan.procesos.ProcesosFormularioPersona;
import com.jhonatan.views.frmAdministrarPersona;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFormularioPersona
        implements ActionListener {

    private final frmAdministrarPersona frmAdministrarPersona;
    private final frmMenu frmMenu;
    private Persona miPersona;

    public ControladorFormularioPersona(frmAdministrarPersona frmAdministrarPersona, frmMenu frmMenu) {
        this.frmAdministrarPersona = frmAdministrarPersona;
        this.frmMenu = frmMenu;
        this.frmAdministrarPersona.btnGuardar.addActionListener(this);
        ProcesosFormularioPersona.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarPersona);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmAdministrarPersona.btnGuardar) {
            miPersona = ProcesosFormularioPersona.crearPersonaDesdeFormulario(this.frmAdministrarPersona);
            ProcesosFormularioPersona.guardarPersonaEnArchivo(miPersona);
            ProcesosFormularioPersona.mostrarPersonasEnTabla(this.frmAdministrarPersona.tblDatos);
            System.out.println("Click sobre el boton guardar.");
        }
    }

}
