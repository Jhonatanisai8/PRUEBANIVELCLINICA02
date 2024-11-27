package com.jhonatan.procesos;

import com.jhonatan.models.Cita;
import com.jhonatan.models.Doctor;
import com.jhonatan.models.Paciente;
import com.jhonatan.views.frmAdministrarCita;
import javax.swing.JDesktopPane;

public class ProcesosFormularioAdministrarCita {
    
    public static void presentarFormulario(JDesktopPane escritorio, frmAdministrarCita frmAdministrarCita) {
        escritorio.add(frmAdministrarCita);
        frmAdministrarCita.toFront();
        frmAdministrarCita.setTitle("Administracion de Cita");
        frmAdministrarCita.setVisible(true);
        ProcesosFormularioPersona.mostrarPersonasEnTabla(frmAdministrarCita.tblPacientes);
        ProcesosFormularioDoctor.mostrarDoctoresEnTabla(frmAdministrarCita.tblDoctores);
    }
    
    public static Cita crearCitaDesdeFormulario(frmAdministrarCita frmCita) {
        Cita cita = new Cita();
        int filaPaciente = frmCita.tblPacientes.getSelectedRow();
        int filaDoctores = frmCita.tblDoctores.getSelectedRow();
        Paciente paciente = new Paciente();
        Doctor doctor = new Doctor();
        cita.setMotivo(frmCita.txtMotivo.getText());
        if (filaDoctores != -1 || filaDoctores != -1) {
            paciente.setNombre(String.valueOf(frmCita.tblPacientes.getValueAt(filaPaciente, 0)));
            paciente.setEdad(Integer.parseInt(frmCita.tblPacientes.getValueAt(filaPaciente, 1).toString()));
            paciente.setGenero(String.valueOf(frmCita.tblPacientes.getValueAt(filaPaciente, 2)));
            paciente.setIdentificacion(String.valueOf(frmCita.tblPacientes.getValueAt(filaPaciente, 3)));
            cita.setPaciente(paciente);
            
            doctor.setNombre(String.valueOf(frmCita.tblDoctores.getValueAt(filaDoctores, 0)));
            doctor.setEdad(Integer.parseInt(frmCita.tblDoctores.getValueAt(filaDoctores, 1).toString()));
            doctor.setGenero(String.valueOf(frmCita.tblDoctores.getValueAt(filaDoctores, 2)));
            doctor.setIdentificacion(String.valueOf(frmCita.tblDoctores.getValueAt(filaDoctores, 3)));
            doctor.setEspecialidad(String.valueOf(frmCita.tblDoctores.getValueAt(filaDoctores, 4)));
            doctor.setNumeroLicencia(String.valueOf(frmCita.tblDoctores.getValueAt(filaDoctores, 5)));
            cita.setDoctor(doctor);
            cita.setDate(frmCita.jDateChooser1.getDate());
        }
        return cita;
    }
    
}
