package com.proyecto.loayza.utils;

public class ValidadorLoayza {
    
    public static boolean esDNIValido(String dni) {
        // Un DNI peruano debe tener exactamente 8 dígitos numéricos
        return dni != null && dni.matches("\\d{8}");
    }
    
    public static boolean campoVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
    
    // Validaciones de CMP
    public static boolean esCMPValido(String cmp) {
        return cmp != null && cmp.startsWith("CMP-") && cmp.length() > 5;
    }
}
