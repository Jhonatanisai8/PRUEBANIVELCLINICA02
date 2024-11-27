package com.jhonatan.procesos;

import com.jhonatan.views.frmVerCitas;
import java.awt.Font;
import javax.swing.JDesktopPane;

public class ProcesosFormularioVerCitas {

    public static void presentarFormulario(JDesktopPane escritorio, frmVerCitas frmVerCitas) {
        frmVerCitas.tblDatos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        escritorio.add(frmVerCitas);
        frmVerCitas.toFront();
        frmVerCitas.setTitle("Registro de Citas");
        frmVerCitas.setVisible(true);
        ProcesosFormularioAdministrarCita.mostrarCitasEnTabla(frmVerCitas.tblDatos);
    }
}
