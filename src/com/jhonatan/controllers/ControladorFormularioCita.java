package com.jhonatan.controllers;

import com.jhonatan.models.Cita;
import com.jhonatan.procesos.ProcesosFormularioAdministrarCita;
import com.jhonatan.views.frmAdministrarCita;
import com.jhonatan.views.frmMenu;
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
        this.frmAdministrarCita.btnEliminar.addActionListener(this);
        ProcesosFormularioAdministrarCita.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarCita);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmAdministrarCita.btnEliminar) {
            cita = ProcesosFormularioAdministrarCita.crearCitaDesdeFormulario(frmAdministrarCita);

            frmAdministrarCita.txtInformacion.append(cita.toString());
        }
    }

}
