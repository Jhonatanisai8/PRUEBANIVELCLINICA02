package com.jhonatan.app;

import com.jhonatan.controllers.ControladorFormularioMenu;
import com.jhonatan.views.frmMenu;

public class App {

    public static void main(String[] args) {
        System.out.println("Hola Mundo");
        frmMenu menu = new frmMenu();
        ControladorFormularioMenu cfm = new ControladorFormularioMenu(menu);
    }

}
