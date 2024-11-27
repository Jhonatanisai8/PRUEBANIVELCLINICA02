package com.jhonatan.procesos;

import com.jhonatan.models.Constantes;
import com.jhonatan.models.Doctor;
import com.jhonatan.models.Mensaje;
import com.jhonatan.views.frmAdministrarDoctor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ProcesosFormularioDoctor
        implements Constantes {

    public static void llenarComboBox(frmAdministrarDoctor frmAdministrarDoctor) {
        frmAdministrarDoctor.cbxEspecialidad.removeAllItems();
        frmAdministrarDoctor.cbxGenero.removeAllItems();

        for (String genero : GENEROS) {
            frmAdministrarDoctor.cbxGenero.addItem(genero);
        }
        for (String especialidad : especialidadesMedicas) {
            frmAdministrarDoctor.cbxEspecialidad.addItem(especialidad);
        }
    }

    public static void presentarFormulario(JDesktopPane escritorio, frmAdministrarDoctor frmAdministrarDocto) {
        llenarComboBox(frmAdministrarDocto);
        escritorio.add(frmAdministrarDocto);
        frmAdministrarDocto.toFront();
        frmAdministrarDocto.setTitle("Registro de Doctores.");
        frmAdministrarDocto.setVisible(true);
        mostrarDoctoresEnTabla(frmAdministrarDocto.tblDatos);
    }

    public static Doctor crearDoctorDesdeFormulario(frmAdministrarDoctor frmAdministrarDoctor1) {
        Doctor miDoctor = new Doctor();
        miDoctor.setNombre(frmAdministrarDoctor1.txtNombre.getText());
        miDoctor.setEdad(Integer.parseInt(frmAdministrarDoctor1.spnEdad.getValue().toString()));
        miDoctor.setGenero(frmAdministrarDoctor1.cbxGenero.getSelectedItem().toString());
        miDoctor.setIdentificacion(frmAdministrarDoctor1.txtIdentificacion.getText());
        miDoctor.setEspecialidad(frmAdministrarDoctor1.cbxEspecialidad.getSelectedItem().toString());
        miDoctor.setNumeroLicencia(frmAdministrarDoctor1.txtNumeroLicencia.getText());
        return miDoctor;
    }

    public static void guardarDoctorArchivo(Doctor doctor) {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("doctores.txt", true))) {
            String datosDcocto = doctor.getNombre() + "," + doctor.getEdad() + "," + doctor.getGenero() + "," + doctor.getIdentificacion() + "," + doctor.getEspecialidad() + "," + doctor.getNumeroLicencia();
            //escribimos los datos
            bf.write(datosDcocto);
            bf.newLine();
            Mensaje.M1("Doctor Guardada Correcctamente en el archivo.");
        } catch (Exception e) {
            Mensaje.M1("Error al guardar una doctor en el archivo txt: \n" + e.getLocalizedMessage());
        }
    }

    private static List<String[]> leerDoctoresDesdeArchivo(String nombreArchivo) {
        List<String[]> listaDoctores = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = rd.readLine()) != null) {
                //dividir la linea por comas
                String datos[] = linea.split(",");
                if (datos.length == 6) {
                    listaDoctores.add(datos);
                }
            }
        } catch (Exception e) {
            Mensaje.M1("Error al obtener doctores desde archivo: " + e.getMessage());
        }
        return listaDoctores;
    }

    public static void mostrarDoctoresEnTabla(JTable tblDatos) {
        String[] columnas = {"Nombre", "Edad", "Género", "Identificación", "Especialidad", "Nº de Licencia"};
        DefaultTableModel miModelo = new DefaultTableModel(columnas, 0);
        List<String[]> listaDoctores = leerDoctoresDesdeArchivo("doctores.txt");
        //recorremos y agregamos
        for (String[] doctor : listaDoctores) {
            miModelo.addRow(doctor);
        }
        tblDatos.setModel(miModelo);
    }

    public static void buscarDoctorPorNombre(frmAdministrarDoctor frmAdministrarDoctor, String nombre) {
        DefaultTableModel miModelo = (DefaultTableModel) frmAdministrarDoctor.tblDatos.getModel();
        for (int i = 0; i < miModelo.getColumnCount(); i++) {
            String nombrebuscado = (String) miModelo.getValueAt(i, 0);
            if (nombrebuscado.toLowerCase().contains(nombre.toLowerCase())) {
                //ponermos esa informoacin en los campos de texto 
                frmAdministrarDoctor.txtNombre.setText(String.valueOf(miModelo.getValueAt(i, 0)));
                frmAdministrarDoctor.spnEdad.setValue(Integer.valueOf(miModelo.getValueAt(i, 1).toString()));
                frmAdministrarDoctor.cbxGenero.setSelectedItem(String.valueOf(miModelo.getValueAt(i, 2)));
                frmAdministrarDoctor.txtIdentificacion.setText(String.valueOf(miModelo.getValueAt(i, 3)));
                frmAdministrarDoctor.cbxEspecialidad.setSelectedItem(String.valueOf(miModelo.getValueAt(i, 4)));
                frmAdministrarDoctor.txtNumeroLicencia.setText(String.valueOf(miModelo.getValueAt(i, 5)));
            }
        }
    }

    public static void eliminarPersonaPorIdentificacion(frmAdministrarDoctor frmAdministrarDoctor, String identificacion) {
        DefaultTableModel miModelo = (DefaultTableModel) frmAdministrarDoctor.tblDatos.getModel();
        boolean encontrado = false;
        for (int i = 0; i < miModelo.getRowCount(); i++) {
            String identificacionTabla = (String) miModelo.getValueAt(i, 3);
            if (identificacionTabla.equalsIgnoreCase(identificacion)) {
                miModelo.removeRow(i);
                encontrado = true;
                Mensaje.M1("Doctor con Identificacion " + identificacion + " eliminado de la tabla.");
                break;
            }

            if (encontrado) {
                System.out.println("El archivo TXT ha sido actualizado.");
            } else {
                Mensaje.M1("No se encontro un doctoe  con Identificacion: " + identificacion);
            }
        }
    }

    public static void actualizarArchivoTabla(frmAdministrarDoctor frmAdministrarDoctor) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter("doctores.txt", false))) {
            DefaultTableModel miModelo = (DefaultTableModel) frmAdministrarDoctor.tblDatos.getModel();
            for (int i = 0; i < miModelo.getRowCount(); i++) {
                String nombre = (String) miModelo.getValueAt(i, 0);
                int edad = Integer.parseInt(miModelo.getValueAt(i, 1).toString());
                String genero = miModelo.getValueAt(i, 2).toString();
                String identificacion = miModelo.getValueAt(i, 3).toString();
                String especialidad = miModelo.getValueAt(i, 4).toString();
                String numeroLicencia = miModelo.getValueAt(i, 5).toString();

                br.write(nombre + "," + edad + "," + genero + "," + identificacion + "," + especialidad + "," + numeroLicencia);
                br.newLine();
            }
        } catch (Exception e) {
            Mensaje.M1("Error al actualizar el archivo txt de doctores: " + e.getMessage());
        }
    }

    public static void limpiarCampos(frmAdministrarDoctor frmAdministrarDoctor) {
        frmAdministrarDoctor.txtIdentificacion.setText("");
        frmAdministrarDoctor.txtNombre.setText("");
        frmAdministrarDoctor.txtNumeroLicencia.setText("");
        frmAdministrarDoctor.cbxEspecialidad.setSelectedIndex(0);
        frmAdministrarDoctor.cbxEspecialidad.setSelectedIndex(1);
        frmAdministrarDoctor.spnEdad.setValue(0);

        frmAdministrarDoctor.txtNombre.requestFocus();
    }

    public static void mostrarDatosDoctorCampos(frmAdministrarDoctor frmAdministrarDoctor) {
        int filaSelecionada = frmAdministrarDoctor.tblDatos.getSelectedRow();
        if (filaSelecionada != -1) {
            String nombre = frmAdministrarDoctor.tblDatos.getValueAt(filaSelecionada, 0).toString();
            int edad = Integer.parseInt(frmAdministrarDoctor.tblDatos.getValueAt(filaSelecionada, 1).toString());
            String genero = frmAdministrarDoctor.tblDatos.getValueAt(filaSelecionada, 2).toString();
            String identificacion = frmAdministrarDoctor.tblDatos.getValueAt(filaSelecionada, 3).toString();
            String especialidad = frmAdministrarDoctor.tblDatos.getValueAt(filaSelecionada, 4).toString();
            String numerolicencia = frmAdministrarDoctor.tblDatos.getValueAt(filaSelecionada, 5).toString();

            //ponermos esa informoacin en los campos de texto 
            frmAdministrarDoctor.txtNombre.setText(nombre);
            frmAdministrarDoctor.txtIdentificacion.setText(identificacion);
            frmAdministrarDoctor.cbxGenero.setSelectedItem(genero);
            frmAdministrarDoctor.spnEdad.setValue(edad);
            frmAdministrarDoctor.cbxEspecialidad.setSelectedItem(especialidad);
            frmAdministrarDoctor.txtNumeroLicencia.setText(numerolicencia);

        }
    }
}
