package com.jhonatan.controllers;

import com.jhonatan.models.Doctor;
import com.jhonatan.procesos.ProcesosFormularioDoctor;
import com.jhonatan.views.frmAdministrarDoctor;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorFormularioDoctor
        implements ActionListener, MouseListener {

    private final frmAdministrarDoctor frmAdministrarDoctor;
    private final frmMenu frmenu;
    private Doctor miDoctor;

    public ControladorFormularioDoctor(frmAdministrarDoctor frmAdministrarDoctor, frmMenu frmenu) {
        this.frmAdministrarDoctor = frmAdministrarDoctor;
        this.frmenu = frmenu;
        this.frmAdministrarDoctor.btnGuardar.addActionListener(this);
        this.frmAdministrarDoctor.btnDirigirmePacientes.addActionListener(this);
        this.frmAdministrarDoctor.btnEliminar.addActionListener(this);
        this.frmAdministrarDoctor.btnModifica.addActionListener(this);
        this.frmAdministrarDoctor.btnBuscar.addActionListener(this);
        this.frmAdministrarDoctor.tblDatos.addMouseListener(this);
        ProcesosFormularioDoctor.presentarFormulario(this.frmenu.dskEscritorio, this.frmAdministrarDoctor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //para el boton guardar 
        if (e.getSource() == this.frmAdministrarDoctor.btnGuardar) {
            miDoctor = ProcesosFormularioDoctor.crearDoctorDesdeFormulario(this.frmAdministrarDoctor);
            ProcesosFormularioDoctor.guardarDoctorArchivo(miDoctor);
            ProcesosFormularioDoctor.mostrarDoctoresEnTabla(this.frmAdministrarDoctor.tblDatos);
            ProcesosFormularioDoctor.limpiarCampos(this.frmAdministrarDoctor);
            System.out.println("Click sobre el boton guardar..");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.frmAdministrarDoctor.tblDatos) {
            ProcesosFormularioDoctor.mostrarDatosDoctorCampos(this.frmAdministrarDoctor);
            System.out.println("Has dado click sobre la tabla..");
        }
    }

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
