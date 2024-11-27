package com.jhonatan.procesos;

import com.jhonatan.models.Doctor;
import com.jhonatan.views.frmAdministrarDoctorPacientes;
import javax.swing.JDesktopPane;
import javax.swing.JTable;

public class ProcesosFormularioPacientesDoctores {

    public static void presentarFormulario(JDesktopPane escritorio, frmAdministrarDoctorPacientes frmAdministrarDoctorPacientes, Doctor doctor) {
        escritorio.add(frmAdministrarDoctorPacientes);
        frmAdministrarDoctorPacientes.setTitle("Asigancion de pacientes a doctores.");
        frmAdministrarDoctorPacientes.lblNombre.setText("Nombre: " + doctor.getNombre());
        frmAdministrarDoctorPacientes.lblEdad.setText("Edad: " + doctor.getEdad() + "");
        frmAdministrarDoctorPacientes.lblGenero.setText("Genero: " + doctor.getGenero());
        frmAdministrarDoctorPacientes.lblIdentificacion.setText("Indentificacion: " + doctor.getIdentificacion());
        frmAdministrarDoctorPacientes.lblEspecialidad.setText("ESpecialidad:: " + doctor.getEspecialidad());
        frmAdministrarDoctorPacientes.lblNumeroLicencia.setText("Nº de Licencia.: " + doctor.getNumeroLicencia());
        frmAdministrarDoctorPacientes.toFront();
        frmAdministrarDoctorPacientes.setVisible(true);
    }

    public static void listarPacientes(JTable tblDatos) {
        ProcesosFormularioPersona.mostrarPersonasEnTabla(tblDatos);
    }

}
