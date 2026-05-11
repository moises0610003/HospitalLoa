package com.proyecto.loayza.model;

import java.util.Date;

public class Cita {
    private int id; // El ID que genera el error
    private Paciente paciente;
    private Medico medico;
    private Date fecha;
    private String hora;
    private String estado;

    public Cita() {}

    public Cita(int id, Paciente paciente, Medico medico, Date fecha, String hora, String estado) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Otros Getters necesarios para el DAO y los Reportes
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}