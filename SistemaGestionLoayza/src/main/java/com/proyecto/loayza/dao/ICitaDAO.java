package com.proyecto.loayza.dao;

import com.proyecto.loayza.model.Cita;
import java.util.Date;
import java.util.List;

public interface ICitaDAO {
    boolean registrarCita(Cita cita);
    List<Cita> listarTodas();
    boolean cancelarCita(int idCita);

    boolean validarDisponibilidad(String colegiatura, Date fecha, String hora);
}