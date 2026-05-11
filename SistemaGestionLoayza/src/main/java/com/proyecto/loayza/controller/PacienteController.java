package com.proyecto.loayza.controller;

import com.proyecto.loayza.dao.IPacienteDAO;
import com.proyecto.loayza.dao.PacienteDAOImpl;
import com.proyecto.loayza.model.Paciente;
import com.proyecto.loayza.utils.Mensajes;
import com.proyecto.loayza.utils.ValidadorLoayza;
import java.util.List;

public class PacienteController {
    private IPacienteDAO dao;

    public PacienteController() {

        this.dao = new PacienteDAOImpl();
    }

    public void registrarPaciente(Paciente p) {

        if (!ValidadorLoayza.esDNIValido(p.getDni())) {
            Mensajes.error("El DNI debe tener exactamente 8 dígitos.");
            return;
        }

        if (dao.buscarPorDni(p.getDni()) != null) {
            Mensajes.error("Error: Ya existe un paciente registrado con el DNI " + p.getDni());
            return;
        }

        if (dao.insertar(p)) {
            Mensajes.informar("Paciente registrado con éxito.");
        } else {
            Mensajes.error("No se pudo completar el registro.");
        }
    }

    public Paciente buscarPaciente(String dni) {
        return dao.buscarPorDni(dni);
    }

    public List<Paciente> listarPacientes() {
        return dao.listarTodos();
    }
}