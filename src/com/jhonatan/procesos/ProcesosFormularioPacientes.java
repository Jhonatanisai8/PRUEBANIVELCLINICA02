package com.jhonatan.procesos;

import com.jhonatan.models.Persona;
import com.jhonatan.views.frmAdministrarPaciente;
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
        frmAdministrarPaciente.setVisible(true);
    }

}
