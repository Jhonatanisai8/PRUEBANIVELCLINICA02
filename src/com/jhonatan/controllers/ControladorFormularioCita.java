package com.jhonatan.controllers;

import com.jhonatan.models.Cita;
import com.jhonatan.procesos.ProcesosFormularioAdministrarCita;
import com.jhonatan.views.frmAdministrarCita;
import com.jhonatan.views.frmMenu;
import com.jhonatan.views.frmVerCitas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorFormularioCita
        implements ActionListener {

    private frmAdministrarCita frmAdministrarCita;
    private frmMenu frmMenu;
    private Cita cita;

    public ControladorFormularioCita(frmAdministrarCita frmAdministrarCita, frmMenu frmMenu) {
        this.frmAdministrarCita = frmAdministrarCita;
        this.frmMenu = frmMenu;
        this.frmAdministrarCita.btnRegistrar.addActionListener(this);
        this.frmAdministrarCita.btnVerCitas.addActionListener(this);
        ProcesosFormularioAdministrarCita.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarCita);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmAdministrarCita.btnRegistrar) {
            cita = ProcesosFormularioAdministrarCita.crearCitaDesdeFormulario(frmAdministrarCita);
            frmAdministrarCita.txtInformacion.append(cita.toString());
            ProcesosFormularioAdministrarCita.guardarCitaEnArchivo(cita);
        }

        if (e.getSource() == this.frmAdministrarCita.btnVerCitas) {
            frmVerCitas citas = new frmVerCitas();
            this.frmAdministrarCita.dispose();
            ControladorFormularioVerCitas cfvc = new ControladorFormularioVerCitas(citas, this.frmMenu);
        }
    }

}
