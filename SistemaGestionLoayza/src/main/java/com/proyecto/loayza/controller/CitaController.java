package com.proyecto.loayza.controller;

import com.proyecto.loayza.dao.CitaDAOImpl;
import com.proyecto.loayza.dao.ICitaDAO;
import com.proyecto.loayza.model.Cita;
import com.proyecto.loayza.utils.Mensajes;
import java.util.Date;
import java.util.List;

public class CitaController {
    private ICitaDAO dao;

    public CitaController() {

        // Después SQL
        this.dao = new CitaDAOImpl();
    }

    public void registrarNuevaCita(Cita cita) {

        boolean disponible = dao.validarDisponibilidad(
                cita.getMedico().getColegiatura(), 
                cita.getFecha(), 
                cita.getHora()
        );

        if (disponible) {
            if (dao.registrarCita(cita)) {
                Mensajes.informar("Cita registrada exitosamente para el paciente: " + cita.getPaciente().getNombres());
            } else {
                Mensajes.error("No se pudo registrar la cita en el sistema.");
            }
        } else {
            Mensajes.error("El médico ya tiene una cita programada para esa fecha y hora.");
        }
    }

    public List<Cita> obtenerTodasLasCitas() {
        return dao.listarTodas();
    }
    
    public void cancelarCita(int idCita) {
        if (dao.cancelarCita(idCita)) {
            Mensajes.informar("La cita ha sido cancelada.");
        }
    }
}