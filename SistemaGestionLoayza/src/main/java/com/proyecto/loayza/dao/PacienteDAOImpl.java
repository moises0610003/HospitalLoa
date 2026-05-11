package com.proyecto.loayza.dao;

import com.proyecto.loayza.db.DatabaseContext;
import com.proyecto.loayza.model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOImpl implements IPacienteDAO {

    @Override
    public boolean insertar(Paciente paciente) {
        String sql = "INSERT INTO pacientes (dni, nombres, apellidos, fecha_nacimiento, telefono, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getApellidos());
            ps.setDate(4, paciente.getFechaNacimiento() != null ? new java.sql.Date(paciente.getFechaNacimiento().getTime()) : null);
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizar(Paciente paciente) {
        String sql = "UPDATE pacientes SET nombres = ?, apellidos = ?, fecha_nacimiento = ?, telefono = ?, email = ? WHERE dni = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, paciente.getNombres());
            ps.setString(2, paciente.getApellidos());
            ps.setDate(3, paciente.getFechaNacimiento() != null ? new java.sql.Date(paciente.getFechaNacimiento().getTime()) : null);
            ps.setString(4, paciente.getTelefono());
            ps.setString(5, paciente.getEmail());
            ps.setString(6, paciente.getDni());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(String dni) {
        String sql = "DELETE FROM pacientes WHERE dni = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Paciente buscarPorDni(String dni) {
        String sql = "SELECT * FROM pacientes WHERE dni = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Paciente p = new Paciente();
                p.setDni(rs.getString("dni"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";
        try (Connection conn = DatabaseContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Paciente p = new Paciente();
                p.setDni(rs.getString("dni"));
                p.setNombres(rs.getString("nombres"));
                p.setApellidos(rs.getString("apellidos"));
                p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                p.setTelefono(rs.getString("telefono"));
                p.setEmail(rs.getString("email"));
                pacientes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }
}