package com.jhonatan.controllers;

import com.jhonatan.models.Persona;
import com.jhonatan.procesos.ProcesosFormularioPersona;
import com.jhonatan.views.frmAdministrarPersona;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorFormularioPersona implements ActionListener, MouseListener {

    private final frmAdministrarPersona frmAdministrarPersona;
    private final frmMenu frmMenu;
    private Persona miPersona;

    public ControladorFormularioPersona(frmAdministrarPersona frmAdministrarPersona, frmMenu frmMenu) {
        this.frmAdministrarPersona = frmAdministrarPersona;
        this.frmMenu = frmMenu;
        this.frmAdministrarPersona.btnGuardar.addActionListener(this);
        this.frmAdministrarPersona.tblDatos.addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.frmAdministrarPersona.tblDatos) {
            ProcesosFormularioPersona.mostrarDatosPersonaCampos(frmAdministrarPersona);
            System.out.println("Click sobre la tabla..");
        }
    }

    // Métodos vacíos o eliminados si no se necesitan
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
