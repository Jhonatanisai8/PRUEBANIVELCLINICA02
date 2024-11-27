package com.jhonatan.procesos;

import com.jhonatan.models.Constantes;
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

public class ProcesosFormularioPersona
        implements Constantes {

    public static void llenarCombo(frmAdministrarPersona frmAdministrarPersona) {
        frmAdministrarPersona.cbxGenero.removeAllItems();
        for (String string : GENEROS) {
            frmAdministrarPersona.cbxGenero.addItem(string);
        }
    }

    public static void presentarFormulario(JDesktopPane escritorio, frmAdministrarPersona frmPersona) {
        llenarCombo(frmPersona);
        escritorio.add(frmPersona);
        frmPersona.toFront();
        frmPersona.setTitle("Registro de Persona.");
        frmPersona.setVisible(true);
        mostrarPersonasEnTabla(frmPersona.tblDatos);
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

    public static void mostrarDatosPersonaCampos(frmAdministrarPersona frmAdministrarPersona) {
        int filaSelecionada = frmAdministrarPersona.tblDatos.getSelectedRow();
        if (filaSelecionada != -1) {
            String nombre = frmAdministrarPersona.tblDatos.getValueAt(filaSelecionada, 0).toString();
            int edad = Integer.parseInt(frmAdministrarPersona.tblDatos.getValueAt(filaSelecionada, 1).toString());
            String genero = frmAdministrarPersona.tblDatos.getValueAt(filaSelecionada, 2).toString();
            String identificacion = frmAdministrarPersona.tblDatos.getValueAt(filaSelecionada, 3).toString();

            //ponermos esa informoacin en los campos de texto 
            frmAdministrarPersona.txtNombre.setText(nombre);
            frmAdministrarPersona.txtIdentificacion.setText(identificacion);
            frmAdministrarPersona.cbxGenero.setSelectedItem(genero);
            frmAdministrarPersona.spnEdad.setValue(edad);
        }
    }

    public static void buscarPersonaPorNombre(frmAdministrarPersona frmAdministrarPersona, String nombre) {
        DefaultTableModel miModelo = (DefaultTableModel) frmAdministrarPersona.tblDatos.getModel();
        for (int i = 0; i < miModelo.getColumnCount(); i++) {
            String nombrebuscado = (String) miModelo.getValueAt(i, 0);
            if (nombrebuscado.toLowerCase().contains(nombre.toLowerCase())) {
                //ponermos esa informoacin en los campos de texto 
                frmAdministrarPersona.txtNombre.setText(String.valueOf(miModelo.getValueAt(i, 0)));
                frmAdministrarPersona.spnEdad.setValue(Integer.valueOf(miModelo.getValueAt(i, 1).toString()));
                frmAdministrarPersona.cbxGenero.setSelectedItem(String.valueOf(miModelo.getValueAt(i, 2)));
                frmAdministrarPersona.txtIdentificacion.setText(String.valueOf(miModelo.getValueAt(i, 3)));
            }
        }
    }

    public static void eliminarPersonaPorIdentificacion(frmAdministrarPersona frmAdministrarPersona, String identificacion) {
        DefaultTableModel miModelo = (DefaultTableModel) frmAdministrarPersona.tblDatos.getModel();
        boolean encontrado = false;
        for (int i = 0; i < miModelo.getRowCount(); i++) {
            String identificacionTabla = (String) miModelo.getValueAt(i, 3);
            if (identificacionTabla.equalsIgnoreCase(identificacion)) {
                miModelo.removeRow(i);
                encontrado = true;
                Mensaje.M1("Persona con Identificacion " + identificacion + " eliminada de la tabla.");
                break;
            }

            if (encontrado) {

            } else {
                Mensaje.M1("No se encontro una persona con Identificacion: " + identificacion);
            }
        }
    }

    public static void actualizarArchivoTabla(frmAdministrarPersona frAdministrarPersona) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("personas.txt", false))) {
            DefaultTableModel miModelo = (DefaultTableModel) frAdministrarPersona.tblDatos.getModel();
            for (int i = 0; i < miModelo.getRowCount(); i++) {
                String nombre = (String) miModelo.getValueAt(i, 0);
                int edad = Integer.parseInt(miModelo.getValueAt(i, 1).toString());
                String genero = miModelo.getValueAt(i, 2).toString();
                String identificacion = miModelo.getValueAt(i, 3).toString();
                br.write(nombre + "," + edad + "," + genero + "," + identificacion);
                br.newLine();
            }
        } catch (Exception e) {
            Mensaje.M1("Error al actualizar el archivo txt de personas: " + e.getMessage());
        }
    }
}
