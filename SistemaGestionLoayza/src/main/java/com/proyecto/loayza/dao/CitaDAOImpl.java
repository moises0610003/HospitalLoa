package com.proyecto.loayza.dao;

import com.proyecto.loayza.db.DatabaseContext;
import com.proyecto.loayza.model.Cita;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitaDAOImpl implements ICitaDAO {

    @Override
    public boolean registrarCita(Cita cita) {
        String sql = "INSERT INTO citas (paciente_dni, medico_colegiatura, fecha, hora, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cita.getPaciente().getDni());
            ps.setString(2, cita.getMedico().getColegiatura());
            ps.setDate(3, new java.sql.Date(cita.getFecha().getTime()));
            ps.setString(4, cita.getHora());
            ps.setString(5, cita.getEstado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Cita> listarTodas() {
        List<Cita> citas = new ArrayList<>();
        String sql = "SELECT * FROM citas";
        try (Connection conn = DatabaseContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Nota: Necesitarías cargar Paciente y Medico desde sus DAOs
                // Por simplicidad, asumimos que se cargan por separado
                Cita cita = new Cita();
                cita.setId(rs.getInt("id"));
                cita.setFecha(rs.getDate("fecha"));
                cita.setHora(rs.getString("hora"));
                cita.setEstado(rs.getString("estado"));
                // cita.setPaciente(...) - implementar carga
                // cita.setMedico(...) - implementar carga
                citas.add(cita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }

    @Override
    public boolean cancelarCita(int idCita) {
        String sql = "UPDATE citas SET estado = 'CANCELADA' WHERE id = ?";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validarDisponibilidad(String colegiatura, Date fecha, String hora) {
        String sql = "SELECT COUNT(*) FROM citas WHERE medico_colegiatura = ? AND fecha = ? AND hora = ? AND estado != 'CANCELADA'";
        try (Connection conn = DatabaseContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, colegiatura);
            ps.setDate(2, new java.sql.Date(fecha.getTime()));
            ps.setString(3, hora);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}