package com.jhonatan.procesos;

import com.jhonatan.models.Cita;
import com.jhonatan.models.Doctor;
import com.jhonatan.models.Paciente;
import com.jhonatan.views.frmAdministrarCita;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.swing.JDesktopPane;
import javax.swing.table.DefaultTableModel;

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
            cita.setHora(LocalTime.now());
        }
        return cita;
    }

    public static void guardarCitaEnArchivo(Cita cita) {
        String nombreArchivo = "citas.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            writer.write("Paciente: " + cita.getPaciente().getNombre() + ", ");
            writer.write("Edad Paciente: " + cita.getPaciente().getEdad() + ", ");
            writer.write("Género Paciente: " + cita.getPaciente().getGenero() + ", ");
            writer.write("ID Paciente: " + cita.getPaciente().getIdentificacion() + ", ");
            writer.write("Doctor: " + cita.getDoctor().getNombre() + ", ");
            writer.write("Edad Doctor: " + cita.getDoctor().getEdad() + ", ");
            writer.write("Género Doctor: " + cita.getDoctor().getGenero() + ", ");
            writer.write("Especialidad Doctor: " + cita.getDoctor().getEspecialidad() + ", ");
            writer.write("Licencia Doctor: " + cita.getDoctor().getNumeroLicencia() + ", ");
            writer.write("Motivo: " + cita.getMotivo() + ", ");
            writer.write("Fecha: " + cita.getDate() + ", ");
            writer.write("Hora: " + cita.getHora());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la cita en el archivo: " + e.getMessage());
        }
    }

    public static void mostrarCitasEnTabla(javax.swing.JTable tblCitas) {
        String nombreArchivo = "citas.txt";
        DefaultTableModel modelo = (DefaultTableModel) tblCitas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
        String[] encabezado = {"Paciente", "Edad Paciente", "Género Paciente", "ID Paciente",
            "Doctor", "Edad Doctor", "Género Doctor", "Especialidad Doctor",
            "Licencia Doctor", "Motivo", "Fecha", "Hora"};
        modelo = new DefaultTableModel(encabezado, 0);
        tblCitas.setModel(modelo);
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Suponemos que los datos están separados por comas (,) en el archivo
                String[] datos = linea.split(", ");

                // Extraemos los valores después del prefijo (Paciente:, Edad Paciente:, etc.)
                String paciente = datos[0].split(": ")[1];
                String edadPaciente = datos[1].split(": ")[1];
                String generoPaciente = datos[2].split(": ")[1];
                String idPaciente = datos[3].split(": ")[1];
                String doctor = datos[4].split(": ")[1];
                String edadDoctor = datos[5].split(": ")[1];
                String generoDoctor = datos[6].split(": ")[1];
                String especialidadDoctor = datos[7].split(": ")[1];
                String licenciaDoctor = datos[8].split(": ")[1];
                String motivo = datos[9].split(": ")[1];
                String fecha = datos[10].split(": ")[1];
                String hora = datos[11].split(": ")[1];

                // Agregar la fila a la tabla
                modelo.addRow(new Object[]{
                    paciente, edadPaciente, generoPaciente, idPaciente,
                    doctor, edadDoctor, generoDoctor, especialidadDoctor,
                    licenciaDoctor, motivo, fecha, hora
                });
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de citas: " + e.getMessage());
        }
    }

}
