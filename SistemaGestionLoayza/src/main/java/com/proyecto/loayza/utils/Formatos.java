package com.proyecto.loayza.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatos {
    
    // Formatos estándar para el hospital
    private static final SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    // Convierte de Date a String (Para mostrar en tablas/etiquetas)
    public static String fechaAString(Date fecha) {
        return (fecha != null) ? sdfFecha.format(fecha) : "";
    }

    public static String horaAString(Date hora) {
        return (hora != null) ? sdfHora.format(hora) : "";
    }

    // Convierte de String a Date (Para guardar lo que el usuario escribe o selecciona)
    public static Date stringAFecha(String fechaStr) {
        try {
            return sdfFecha.parse(fechaStr);
        } catch (Exception e) {
            return null; // Retorna null si el formato es inválido
        }
    }

    public static String obtenerNombreMes(Date fecha) {
        SimpleDateFormat mesFormat = new SimpleDateFormat("MMMM");
        return mesFormat.format(fecha);
    }
}