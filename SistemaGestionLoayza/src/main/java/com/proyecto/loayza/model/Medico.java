package com.proyecto.loayza.model;

public class Medico {
    private String colegiatura;
    private String nombres;
    private String apellidos;
    private Especialidad especialidad;

    public Medico(String colegiatura, String nombres, String apellidos, Especialidad especialidad) {
        this.colegiatura = colegiatura;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
    }

    public String getNombreCompleto() {
        return this.nombres + " " + this.apellidos;
    }

    // Getters 
    public String getColegiatura() { return colegiatura; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public Especialidad getEspecialidad() { return especialidad; }

    @Override
    public String toString() {
        return getNombreCompleto() + " (" + especialidad.getNombre() + ")";
    }
}