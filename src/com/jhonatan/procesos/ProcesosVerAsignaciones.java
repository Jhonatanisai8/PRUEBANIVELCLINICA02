package com.jhonatan.procesos;

import com.jhonatan.models.Doctor;
import com.jhonatan.models.Mensaje;
import com.jhonatan.views.frmVerAsignacionesPacientesDoctor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProcesosVerAsignaciones {

    public static void presentarFormulario(JDesktopPane escritorio, frmVerAsignacionesPacientesDoctor frmAsignacionesPacientesDocto, Doctor doctor) {
        escritorio.add(frmAsignacionesPacientesDocto);
        frmAsignacionesPacientesDocto.toFront();
        frmAsignacionesPacientesDocto.setTitle("PACIENTES ASIGANADOS.");
        frmAsignacionesPacientesDocto.setTitle("Asigancion de pacientes a doctores.");
        frmAsignacionesPacientesDocto.lblNombre.setText("Nombre: " + doctor.getNombre());
        frmAsignacionesPacientesDocto.lblEdad.setText("Edad: " + doctor.getEdad() + "");
        frmAsignacionesPacientesDocto.lblGenero.setText("Genero: " + doctor.getGenero());
        frmAsignacionesPacientesDocto.lblIdentificacion.setText("Indentificacion: " + doctor.getIdentificacion());
        frmAsignacionesPacientesDocto.lblEspecialidad.setText("ESpecialidad:: " + doctor.getEspecialidad());
        frmAsignacionesPacientesDocto.lblNumeroLicencia.setText("Nº de Licencia.: " + doctor.getNumeroLicencia());
        frmAsignacionesPacientesDocto.toFront();
        frmAsignacionesPacientesDocto.setVisible(true);
        mostrarPersonasEnTabla(frmAsignacionesPacientesDocto.tblData, doctor.getNombre() + "_pacientes.txt");
    }

    private static List<String[]> leerPersonaDesdeArchivo(String nombreArchivo) {
        List<String[]> listaPersonas = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = rd.readLine()) != null) {
                //dividir la linea por comas
                String datos[] = linea.split(",");
                if (datos.length == 4) {
                    listaPersonas.add(datos);
                }
            }
        } catch (Exception e) {
            Mensaje.M1("Error al obtener pacientes desde archivo: " + e.getMessage());
        }
        return listaPersonas;
    }

    public static void mostrarPersonasEnTabla(JTable tblDatos, String nombre) {
        String[] columnas = {"Nombre", "Edad", "Género", "Identificación"};
        DefaultTableModel miModelo = new DefaultTableModel(columnas, 0);
        List<String[]> listaPersonas = leerPersonaDesdeArchivo(nombre);
        //recorremos y agregamos
        for (String[] persona : listaPersonas) {
            miModelo.addRow(persona);
        }
        tblDatos.setModel(miModelo);
    }

}
