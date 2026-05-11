package com.proyecto.loayza.db;

import com.proyecto.loayza.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseContext {
    private static final String URL = "jdbc:h2:~/hospital_loayza;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            createTables();
            loadInitialData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTables() throws SQLException {
        String createEspecialidades = "CREATE TABLE IF NOT EXISTS especialidades (id INT PRIMARY KEY, nombre VARCHAR(255))";
        String createUsuarios = "CREATE TABLE IF NOT EXISTS usuarios (id INT PRIMARY KEY, usuario VARCHAR(255), password VARCHAR(255), nombres VARCHAR(255), rol VARCHAR(255))";
        String createMedicos = "CREATE TABLE IF NOT EXISTS medicos (colegiatura VARCHAR(255) PRIMARY KEY, nombres VARCHAR(255), apellidos VARCHAR(255), especialidad_id INT, FOREIGN KEY (especialidad_id) REFERENCES especialidades(id))";
        String createPacientes = "CREATE TABLE IF NOT EXISTS pacientes (dni VARCHAR(8) PRIMARY KEY, nombres VARCHAR(255), apellidos VARCHAR(255), fecha_nacimiento DATE, telefono VARCHAR(15), email VARCHAR(255))";
        String createCitas = "CREATE TABLE IF NOT EXISTS citas (id INT AUTO_INCREMENT PRIMARY KEY, paciente_dni VARCHAR(8), medico_colegiatura VARCHAR(255), fecha DATE, hora VARCHAR(10), estado VARCHAR(50), FOREIGN KEY (paciente_dni) REFERENCES pacientes(dni), FOREIGN KEY (medico_colegiatura) REFERENCES medicos(colegiatura))";
        String createRecursos = "CREATE TABLE IF NOT EXISTS recursos (id INT PRIMARY KEY, nombre VARCHAR(255), tipo VARCHAR(255), cantidad INT, disponible INT, ubicacion VARCHAR(255))";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createEspecialidades);
            stmt.execute(createUsuarios);
            stmt.execute(createMedicos);
            stmt.execute(createPacientes);
            stmt.execute(createCitas);
            stmt.execute(createRecursos);
        }
    }

    private static void loadInitialData() throws SQLException {
        // Especialidades
        insertEspecialidad(1, "Medicina General");
        insertEspecialidad(2, "Pediatría");
        insertEspecialidad(3, "Ginecología");

        // Usuarios
        insertUsuario(1, "Administrador", "3.1416", "Pítagoras", "ADMINISTRADOR");

        // Médicos
        insertMedico("CMP-45678", "Rodrigo Manuel", "Taboada", 1);
        insertMedico("CMP-12345", "Arturo Héctor", "Espinoza", 2);
        insertMedico("CMP-24680", "Chris Angela", "Villafuerte", 3);

        // Recursos
        insertRecurso(1, "Cama UCI-01", "Camas", 1, 1, "Pabellón A");
    }

    private static void insertEspecialidad(int id, String nombre) throws SQLException {
        String sql = "MERGE INTO especialidades (id, nombre) KEY(id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.executeUpdate();
        }
    }

    private static void insertUsuario(int id, String usuario, String password, String nombres, String rol) throws SQLException {
        String sql = "MERGE INTO usuarios (id, usuario, password, nombres, rol) KEY(id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, usuario);
            ps.setString(3, password);
            ps.setString(4, nombres);
            ps.setString(5, rol);
            ps.executeUpdate();
        }
    }

    private static void insertMedico(String colegiatura, String nombres, String apellidos, int especialidadId) throws SQLException {
        String sql = "MERGE INTO medicos (colegiatura, nombres, apellidos, especialidad_id) KEY(colegiatura) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, colegiatura);
            ps.setString(2, nombres);
            ps.setString(3, apellidos);
            ps.setInt(4, especialidadId);
            ps.executeUpdate();
        }
    }

    private static void insertRecurso(int id, String nombre, String tipo, int cantidad, int disponible, String ubicacion) throws SQLException {
        String sql = "MERGE INTO recursos (id, nombre, tipo, cantidad, disponible, ubicacion) KEY(id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, tipo);
            ps.setInt(4, cantidad);
            ps.setInt(5, disponible);
            ps.setString(6, ubicacion);
            ps.executeUpdate();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    // Métodos legacy para compatibilidad (aunque ahora usamos BD)
    public static List<Usuario> tablaUsuarios = new ArrayList<>();
    public static List<Paciente> tablaPacientes = new ArrayList<>();
    public static List<Medico> tablaMedicos = new ArrayList<>();
    public static List<Especialidad> tablaEspecialidades = new ArrayList<>();
    public static List<Cita> tablaCitas = new ArrayList<>();
    public static List<RecursoHospitalario> tablaRecursos = new ArrayList<>();
}