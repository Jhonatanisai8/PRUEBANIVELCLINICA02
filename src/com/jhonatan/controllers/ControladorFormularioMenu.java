package com.jhonatan.controllers;

import com.jhonatan.procesos.ProcesosFormularioMenu;
import com.jhonatan.views.frmAdministrarPersona;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFormularioMenu
        implements ActionListener {

    private frmMenu frmMenu;

    public ControladorFormularioMenu(frmMenu frmMenu) {
        this.frmMenu = frmMenu;
        this.frmMenu.jmnAdmPersonas.addActionListener(this);
        ProcesosFormularioMenu.presentarFormulario(this.frmMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmMenu.jmnAdmPersonas) {
            frmAdministrarPersona frmPersona = new frmAdministrarPersona();
            ControladorFormularioPersona cfp = new ControladorFormularioPersona(frmPersona, this.frmMenu);
            System.out.println("Has dado click sobre el menu de administrar Personas");
        }
    }

}