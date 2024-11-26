package com.jhonatan.procesos;

import com.jhonatan.models.Mensaje;
import com.jhonatan.models.Persona;
import com.jhonatan.views.frmAdministrarPersona;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProcesosFormularioPersona {

    public static void presentarFormulario(JDesktopPane escritorio, frmAdministrarPersona frmPersona) {
        escritorio.add(frmPersona);
        frmPersona.toFront();
        frmPersona.setTitle("Registro de Persona.");
        frmPersona.setVisible(true);
    }

    public static Persona crearPersonaDesdeFormulario(frmAdministrarPersona frmPersona) {
        Persona miPersona = new Persona();
        miPersona.setNombre(frmPersona.txtNombre.getText());
        miPersona.setEdad(Integer.parseInt(frmPersona.spnEdad.getValue().toString()));
        miPersona.setGenero(frmPersona.cbxGenero.getSelectedItem().toString());
        miPersona.setIdentificacion(frmPersona.txtIdentificacion.getText());
        return miPersona;
    }

    public static void guardarPersonaEnArchivo(Persona persona) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("personas.txt", true))) {
            String datosPersona = persona.getNombre() + "," + persona.getEdad() + "," + persona.getGenero() + "," + persona.getIdentificacion();
            //escribimos los datos
            bf.write(datosPersona);
            bf.newLine();
            Mensaje.M1("Persona Guardada Correcctamente en el archivo.");
        } catch (Exception e) {
            Mensaje.M1("Error al guardar una persona en el archivo txt: \n" + e.getLocalizedMessage());
        }

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
            Mensaje.M1("Error al obtener personas desde archivo: " + e.getMessage());
        }
        return listaPersonas;
    }

    public static void mostrarPersonasEnTabla(JTable tblDatos) {
        String[] columnas = {"Nombre", "Edad", "Género", "Identificación"};
        DefaultTableModel miModelo = new DefaultTableModel(columnas, 0);
        List<String[]> listaPersonas = leerPersonaDesdeArchivo("personas.txt");
        //recorremos y agregamos
        for (String[] persona : listaPersonas) {
            miModelo.addRow(persona);
        }
        tblDatos.setModel(miModelo);
    }

}
