package com.proyecto.loayza.model;

public class Usuario {
    private int idUsuario;
    private String username;
    private String password;
    private String nombreReal;
    private String rol; // Ejemplo: "ADMINISTRADOR", "ADMISION", "MEDICO"

    public Usuario() {}

    public Usuario(int idUsuario, String username, String password, String nombreReal, String rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombreReal = nombreReal;
        this.rol = rol;
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNombreReal() { return nombreReal; }
    public void setNombreReal(String nombreReal) { this.nombreReal = nombreReal; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    @Override
    public String toString() {
        return nombreReal + " (" + rol + ")";
    }
}