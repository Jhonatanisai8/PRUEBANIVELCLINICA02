package com.jhonatan.procesos;

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
    
}
