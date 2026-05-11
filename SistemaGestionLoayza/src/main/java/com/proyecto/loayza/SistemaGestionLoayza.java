package com.proyecto.loayza;

import com.proyecto.loayza.view.FrmLogin;
import javax.swing.UIManager;

public class SistemaGestionLoayza {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("No se pudo cargar el Look and Feel.");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }
}