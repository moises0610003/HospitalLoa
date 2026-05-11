package com.proyecto.loayza.model;
//FALTA IMPLEMENTAR 
public class RecursoHospitalario {
    private int idRecurso;
    private String nombre;
    private String tipo; // Camas, Salas, Quirófanos, Equipos
    private int cantidadTotal;
    private int cantidadDisponible;
    private String ubicacion; // Pabellón o área específica del Loayza

    public RecursoHospitalario() {}

    public RecursoHospitalario(int idRecurso, String nombre, String tipo, int cantidadTotal, int cantidadDisponible, String ubicacion) {
        this.idRecurso = idRecurso;
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidadTotal = cantidadTotal;
        this.cantidadDisponible = cantidadDisponible;
        this.ubicacion = ubicacion;
    }

    public int getIdRecurso() { return idRecurso; }
    public void setIdRecurso(int idRecurso) { this.idRecurso = idRecurso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(int cantidadTotal) { this.cantidadTotal = cantidadTotal; }

    public int getCantidadDisponible() { return cantidadDisponible; }
    public void setCantidadDisponible(int cantidadDisponible) { this.cantidadDisponible = cantidadDisponible; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    @Override
    public String toString() {
        return nombre + " [" + tipo + "] - Disponibles: " + cantidadDisponible;
    }
}