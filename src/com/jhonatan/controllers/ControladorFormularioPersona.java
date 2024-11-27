package com.jhonatan.controllers;

import com.jhonatan.models.Mensaje;
import com.jhonatan.models.Persona;
import com.jhonatan.procesos.ProcesosFormularioPersona;
import com.jhonatan.views.frmAdministrarPersona;
import com.jhonatan.views.frmMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControladorFormularioPersona implements ActionListener, MouseListener {

    private final frmAdministrarPersona frmAdministrarPersona;
    private final frmMenu frmMenu;
    private Persona miPersona;

    public ControladorFormularioPersona(frmAdministrarPersona frmAdministrarPersona, frmMenu frmMenu) {
        this.frmAdministrarPersona = frmAdministrarPersona;
        this.frmMenu = frmMenu;
        this.frmAdministrarPersona.btnGuardar.addActionListener(this);
        this.frmAdministrarPersona.btnBuscar.addActionListener(this);
        this.frmAdministrarPersona.btnEliminar.addActionListener(this);
        this.frmAdministrarPersona.tblDatos.addMouseListener(this);
        ProcesosFormularioPersona.presentarFormulario(this.frmMenu.dskEscritorio, this.frmAdministrarPersona);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.frmAdministrarPersona.btnGuardar) {
            miPersona = ProcesosFormularioPersona.crearPersonaDesdeFormulario(this.frmAdministrarPersona);
            ProcesosFormularioPersona.guardarPersonaEnArchivo(miPersona);
            ProcesosFormularioPersona.mostrarPersonasEnTabla(this.frmAdministrarPersona.tblDatos);
            System.out.println("Click sobre el boton guardar.");
        }
        if (e.getSource() == this.frmAdministrarPersona.btnBuscar) {
            if (frmAdministrarPersona.txtBuscarNombre.getText().isBlank()) {
                Mensaje.M1("Por favor ingrese un nombre a buscar.");
            } else {
                ProcesosFormularioPersona.buscarPersonaPorNombre(frmAdministrarPersona, frmAdministrarPersona.txtBuscarNombre.getText());
            }
            System.out.println("Click sobre boton buscar ");
        }
        if (e.getSource() == this.frmAdministrarPersona.btnEliminar) {
            int fila = frmAdministrarPersona.tblDatos.getSelectedRow();
            if (fila != -1) {
                String identificaicion = frmAdministrarPersona.tblDatos.getValueAt(fila, 3).toString();
                ProcesosFormularioPersona.eliminarPersonaPorIdentificacion(frmAdministrarPersona, identificaicion);
                ProcesosFormularioPersona.actualizarArchivoTabla(frmAdministrarPersona);
            } else {
                Mensaje.M1("Por favor seleccione una fila para poder eliminar. ");
            }
            System.out.println("Click sobre boton eliminar");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.frmAdministrarPersona.tblDatos) {
            ProcesosFormularioPersona.mostrarDatosPersonaCampos(frmAdministrarPersona);
            System.out.println("Click sobre la tabla..");
        }
    }

    // Métodos vacíos o eliminados si no se necesitan
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
