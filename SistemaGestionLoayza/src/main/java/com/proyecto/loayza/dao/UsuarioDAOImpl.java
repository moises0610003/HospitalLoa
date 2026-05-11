package com.proyecto.loayza.dao;

import com.proyecto.loayza.db.DatabaseContext;
import com.proyecto.loayza.model.Usuario;
import java.sql.*;

public class UsuarioDAOImpl implements IUsuarioDAO {

    @Override
    public Usuario login(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id"));
                u.setUsername(rs.getString("usuario"));
                u.setPassword(rs.getString("password"));
                u.setNombreReal(rs.getString("nombres"));
                u.setRol(rs.getString("rol"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean cambiarPassword(int idUsuario, String nuevaPassword) {
        String sql = "UPDATE usuarios SET password = ? WHERE id = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevaPassword);
            ps.setInt(2, idUsuario);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}