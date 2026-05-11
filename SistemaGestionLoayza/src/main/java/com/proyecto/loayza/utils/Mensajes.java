package com.proyecto.loayza.utils;

import javax.swing.JOptionPane;

public class Mensajes {
    
    public static void informar(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Sistema Loayza - Información", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void error(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Sistema Loayza - Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static boolean confirmar(String mensaje) {
        int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Sistema Loayza - Confirmar", JOptionPane.YES_NO_OPTION);
        return respuesta == JOptionPane.YES_OPTION;
    }
}