package com.proyecto.loayza.controller;

import com.proyecto.loayza.dao.CitaDAOImpl;
import com.proyecto.loayza.dao.ICitaDAO;
import com.proyecto.loayza.db.DatabaseContext;
import com.proyecto.loayza.model.Cita;
import com.proyecto.loayza.model.RecursoHospitalario;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteController {
    private ICitaDAO citaDao;

    public ReporteController() {
        this.citaDao = new CitaDAOImpl();
    }

    /**
     * Calcula cuántas citas hay por cada especialidad.
     */
    public Map<String, Integer> obtenerDemandaPorEspecialidad() {
        List<Cita> todas = citaDao.listarTodas();
        Map<String, Integer> conteo = new HashMap<>();

        for (Cita c : todas) {
            String especialidad = c.getMedico().getEspecialidad().getNombre();
            conteo.put(especialidad, conteo.getOrDefault(especialidad, 0) + 1);
        }
        return conteo;
    }

    /**
Atendidas y Canceladas
     */
    public Map<String, Integer> obtenerEstadoAsistencia() {
        List<Cita> todas = citaDao.listarTodas();
        int atendidas = 0;
        int canceladas = 0;
        int pendientes = 0;

        for (Cita c : todas) {
            switch (c.getEstado().toUpperCase()) {
                case "ATENDIDA": atendidas++; break;
                case "CANCELADA": canceladas++; break;
                default: pendientes++; break;
            }
        }

        Map<String, Integer> estados = new HashMap<>();
        estados.put("Atendidas", atendidas);
        estados.put("Canceladas", canceladas);
        estados.put("Pendientes", pendientes);
        return estados;
    }

    /**
     * Reporte de ocupación de recursos (Camas, Quirófanos) - FALTA.
     */
    public List<RecursoHospitalario> obtenerEstadoRecursos() {

        return DatabaseContext.tablaRecursos;
    }
}