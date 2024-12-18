package com.jhonatan.procesos;

import com.jhonatan.models.Mensaje;
import com.jhonatan.models.Paciente;
import com.jhonatan.models.Persona;
import com.jhonatan.views.frmAdministrarPaciente;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import javax.swing.JDesktopPane;

public class ProcesosFormularioPacientes {

    public static void presentarFormulario(JDesktopPane escritorio, frmAdministrarPaciente frmAdministrarPaciente, Persona miPersona) {
        escritorio.add(frmAdministrarPaciente);
        frmAdministrarPaciente.lblNombre.setText("Nombre Paciente: " + miPersona.getNombre());
        frmAdministrarPaciente.lblEdad.setText("Edad:: " + miPersona.getEdad());
        frmAdministrarPaciente.lblGenero.setText("Género: " + miPersona.getGenero());
        frmAdministrarPaciente.lblIdentificacion.setText("Identificación: " + miPersona.getIdentificacion());
        frmAdministrarPaciente.toFront();
        frmAdministrarPaciente.setTitle("Registro de Historial.");
        frmAdministrarPaciente.txtHistorial.setForeground(Color.BLACK);
        frmAdministrarPaciente.setVisible(true);
        frmAdministrarPaciente.txtMotivo.requestFocus();
        frmAdministrarPaciente.txtHistorial.setEditable(false);
    }

    public static void guardarHistorialEnArchivo(Paciente miPaciente, HashMap<String, String> historialMedico) {
        String nombreArchivo = miPaciente.getNombre() + ".txt";
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            //recorremos el historial 
            for (String motivo : historialMedico.keySet()) {
                wr.write(motivo + ":" + historialMedico.get(motivo));
                wr.newLine();
            }
            System.out.println("Archivo de historial guardado correctamente.");
        } catch (Exception e) {
            Mensaje.M1("Error al guardar historial en el archivo txt ");
        }
    }

    public static void leerDatosHistorial(frmAdministrarPaciente frmAdministrarPaciente, Paciente paciente) {
        if (frmAdministrarPaciente.txtMotivo.getText().isBlank()
                || frmAdministrarPaciente.txtDescripcion.getText().isBlank()) {
            Mensaje.M1("Por favor, completa los campos de motivo y detalle.");
            return;
        }
        String motivo, detalle;
        motivo = frmAdministrarPaciente.txtMotivo.getText();
        detalle = frmAdministrarPaciente.txtDescripcion.getText();

        paciente.agregarHistorial(motivo, detalle);
        //limpiamos los campos 
        frmAdministrarPaciente.txtDescripcion.setText("");
        frmAdministrarPaciente.txtMotivo.setText("");

        System.out.println("Datos del historial guardados correctamente.");

    }

    public static void mostrarHistorialPaciente(frmAdministrarPaciente frmAdministrarPaciente, String miPaciente) {
        String nombreArchivo = miPaciente + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            frmAdministrarPaciente.txtHistorial.setText("");
            frmAdministrarPaciente.txtHistorial.append("=====HISTORIAL MEDICO=====\n");
            String linea;
            while ((linea = br.readLine()) != null) {
                frmAdministrarPaciente.txtHistorial.append(linea + "\n");
            }
            System.out.println("Mostrando historial.....");
        } catch (Exception e) {
            System.out.println("Error al listar el historial del paciente. " + e.getMessage());
        }
    }

}
