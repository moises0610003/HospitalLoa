package com.proyecto.loayza.dao;

import com.proyecto.loayza.model.Usuario;

public interface IUsuarioDAO {
    // Para el FrmLogin
    Usuario login(String username, String password);
    boolean cambiarPassword(int idUsuario, String nuevaPassword);
}   