package com.jhonatan.procesos;

import com.jhonatan.models.Doctor;
import com.jhonatan.models.Mensaje;
import com.jhonatan.models.Paciente;
import com.jhonatan.views.frmAdministrarDoctorPacientes;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
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

    public static void asigarPacientes(Doctor doctor, frmAdministrarDoctorPacientes frm) {
        String nombreArchivo = doctor.getNombre() + "_pacientes.txt";
        try (BufferedWriter br = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            int fila = frm.tblDatos.getSelectedRow();
            //stremos los datos de fila 
            if (fila != -1) {
                String nombre = (String) frm.tblDatos.getValueAt(fila, 0).toString();
                int edad = Integer.parseInt(frm.tblDatos.getValueAt(fila, 1).toString());
                String genero = (String) frm.tblDatos.getValueAt(fila, 2).toString();
                String identificacion = (String) frm.tblDatos.getValueAt(fila, 3).toString();
                Paciente p = new Paciente();
                p.setEdad(edad);
                p.setIdentificacion(identificacion);
                p.setNombre(nombre);
                p.setGenero(genero);
                doctor.agregarPaciente(p);
                br.write(nombre + "," + edad + "," + genero + "," + identificacion);
                br.newLine();

            } else {
                Mensaje.M1("Derias selecionar un paciente.");
            }

        } catch (Exception e) {
            Mensaje.M1("Error al guardar en el archivo txt: " + e.getMessage());
        }
    }

}
