package com.jhonatan.controllers;

import com.jhonatan.models.Doctor;
import com.jhonatan.models.Mensaje;
import com.jhonatan.procesos.ProcesosFormularioDoctor;
import com.jhonatan.views.frmAdministrarDoctor;
import com.jhonatan.views.frmAdministrarDoctorPacientes;
import com.jhonatan.views.frmMenu;
import com.jhonatan.views.frmVerAsignacionesPacientesDoctor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.print.Doc;

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
        this.frmAdministrarDoctor.btnVerAsiganciones.addActionListener(this);
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
        //para el boton buscar 
        if (e.getSource() == this.frmAdministrarDoctor.btnBuscar) {
            if (frmAdministrarDoctor.txtBuscarNombre.getText().isBlank()) {
                Mensaje.M1("Por favor ingrese un nombre por el cual.");
            } else {
                ProcesosFormularioDoctor.buscarDoctorPorNombre(frmAdministrarDoctor, frmAdministrarDoctor.txtBuscarNombre.getText());
            }
            System.out.println("Click sobre el boton modificar..");
        }
        //para eliminar
        if (e.getSource() == this.frmAdministrarDoctor.btnEliminar) {
            int fila = frmAdministrarDoctor.tblDatos.getSelectedRow();
            if (fila != -1) {
                String identificaicion = frmAdministrarDoctor.tblDatos.getValueAt(fila, 3).toString();
                ProcesosFormularioDoctor.eliminarPersonaPorIdentificacion(frmAdministrarDoctor, identificaicion);
                ProcesosFormularioDoctor.actualizarArchivoTabla(frmAdministrarDoctor);
                ProcesosFormularioDoctor.limpiarCampos(frmAdministrarDoctor);
            } else {
                Mensaje.M1("Por favor seleccione una fila para poder eliminar. ");
            }
            System.out.println("Click sobre boton eliminar");
        }
        if (e.getSource() == this.frmAdministrarDoctor.btnDirigirmePacientes) {
            int fila = frmAdministrarDoctor.tblDatos.getSelectedRow();
            if (fila != -1) {
                Doctor doctor = ProcesosFormularioDoctor.crearDoctorDesdeFormulario(frmAdministrarDoctor);
                frmAdministrarDoctorPacientes frmAdministrarDoctorPacientes = new frmAdministrarDoctorPacientes(doctor);
                ControladorAdmiPacientesDoctores capd = new ControladorAdmiPacientesDoctores(frmAdministrarDoctorPacientes, this.frmenu, doctor);
                Mensaje.M1("Doctor con nombre: " + doctor.getNombre() + " enviado a otro problema.");
            } else {
                Mensaje.M1("Por favor seleccine una fila para poder dirigirse al formulario de asignacion de pacientes.");
            }
            System.out.println("Click sobre el boton que veo si un numero es impar");
        }

        if (e.getSource() == this.frmAdministrarDoctor.btnVerAsiganciones) {
            int fila = frmAdministrarDoctor.tblDatos.getSelectedRow();
            if (fila != -1) {
                Doctor doctor = ProcesosFormularioDoctor.crearDoctorDesdeFormulario(frmAdministrarDoctor);
                frmVerAsignacionesPacientesDoctor fr = new frmVerAsignacionesPacientesDoctor(doctor);
                ControladorFormularioVerAsigacion cfva = new ControladorFormularioVerAsigacion(fr, this.frmenu, doctor);
            } else {
                Mensaje.M1("Por favor seleccine una fila para poder dirigirse al formulario de asignacion de pacientes.");
            }
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
