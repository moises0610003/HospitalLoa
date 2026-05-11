package com.proyecto.loayza.controller;

import com.proyecto.loayza.dao.IUsuarioDAO;
import com.proyecto.loayza.dao.UsuarioDAOImpl;
import com.proyecto.loayza.model.Usuario;
import com.proyecto.loayza.utils.Mensajes;

public class UsuarioController {
    private IUsuarioDAO dao;

    public UsuarioController() {
        this.dao = new UsuarioDAOImpl();
    }

    public Usuario iniciarSesion(String user, String pass) {
        Usuario u = dao.login(user, pass);
        if (u == null) {
            Mensajes.error("Usuario o contraseña incorrectos.");
        } else {
            Mensajes.informar("Bienvenido(a) al Sistema Loayza, " + u.getNombreReal());
        }
        return u;
    }
}