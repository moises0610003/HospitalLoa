package com.proyecto.loayza.dao;

import com.proyecto.loayza.model.Paciente;
import java.util.List;

public interface IPacienteDAO {
    boolean insertar(Paciente paciente);
    boolean actualizar(Paciente paciente);
    boolean eliminar(String dni);
    Paciente buscarPorDni(String dni);
    List<Paciente> listarTodos();
}