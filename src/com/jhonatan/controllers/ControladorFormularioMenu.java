package com.jhonatan.controllers;

import com.jhonatan.procesos.ProcesosFormularioMenu;
import com.jhonatan.views.frmAdministrarCita;
import com.jhonatan.views.frmAdministrarDoctor;
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
        this.frmMenu.jmnAdministrarDoc.addActionListener(this);
        this.frmMenu.jmnAdmnCita.addActionListener(this);
        ProcesosFormularioMenu.presentarFormulario(this.frmMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmMenu.jmnAdmPersonas) {
            frmAdministrarPersona frmPersona = new frmAdministrarPersona();
            ControladorFormularioPersona cfp = new ControladorFormularioPersona(frmPersona, this.frmMenu);
            System.out.println("Has dado click sobre el menu de administrar Personas");
        }
        if (e.getSource() == this.frmMenu.jmnAdministrarDoc) {
            frmAdministrarDoctor frmAdministrarDoctor = new frmAdministrarDoctor();
            ControladorFormularioDoctor cfd = new ControladorFormularioDoctor(frmAdministrarDoctor, this.frmMenu);
            System.out.println("Has dado click sobre el menu de administrar doctores");
        }

        if (e.getSource() == this.frmMenu.jmnAdmnCita) {
            frmAdministrarCita ac = new frmAdministrarCita();
            ControladorFormularioCita cfc = new ControladorFormularioCita(ac, frmMenu);
        }
    }

}
