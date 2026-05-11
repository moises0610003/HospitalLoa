package com.proyecto.loayza.view;

import com.proyecto.loayza.controller.CitaController;
import com.proyecto.loayza.controller.PacienteController;
import com.proyecto.loayza.db.DatabaseContext;
import com.proyecto.loayza.model.*;
import com.proyecto.loayza.utils.*;
import java.awt.*;
import java.util.Date;
import javax.swing.*;

public class PnlAgendarCita extends JInternalFrame {
    private JTextField txtDniPaciente;
    private JComboBox<Especialidad> cbEspecialidad;
    private JComboBox<Medico> cbMedico;
    private JTextField txtFecha, txtHora;
    private CitaController controller;

    public PnlAgendarCita() {
        super("Agendar Cita Médica", true, true, true, true);
        controller = new CitaController();
        inicioComponentes();
        setSize(500, 400);
    }

    private void inicioComponentes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("DNI Paciente:"), gbc);
        txtDniPaciente = new JTextField(10);
        gbc.gridx = 1;
        add(txtDniPaciente, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Especialidad:"), gbc);
        cbEspecialidad = new JComboBox<>(DatabaseContext.tablaEspecialidades.toArray(new Especialidad[0]));
        gbc.gridx = 1;
        add(cbEspecialidad, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Médico:"), gbc);
        cbMedico = new JComboBox<>(DatabaseContext.tablaMedicos.toArray(new Medico[0]));
        gbc.gridx = 1;
        add(cbMedico, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Fecha (dd/mm/yyyy):"), gbc);
        txtFecha = new JTextField(10);
        gbc.gridx = 1;
        add(txtFecha, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        add(new JLabel("Hora (HH:mm):"), gbc);
        txtHora = new JTextField(10);
        gbc.gridx = 1;
        add(txtHora, gbc);

        JButton btnAgendar = new JButton("Reservar Cupo");
        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        add(btnAgendar, gbc);

        btnAgendar.addActionListener(e -> guardarCita());
    }

    private void guardarCita() {
        try {
            String dni = txtDniPaciente.getText().trim();
            if (dni.isEmpty()) {
                Mensajes.error("Debe ingresar el DNI del paciente.");
                return;
            }

            Medico med = (Medico) cbMedico.getSelectedItem();
            Date fecha = Formatos.stringAFecha(txtFecha.getText());
            String hora = txtHora.getText().trim();

            if (fecha == null || hora.isEmpty()) {
                Mensajes.error("Formato de fecha u hora incorrecto.");
                return;
            }

            // Buscamos al paciente mediante su controlador
            PacienteController pacCtrl = new PacienteController();
            Paciente pac = pacCtrl.buscarPaciente(dni);
            
            if (pac == null) {
                Mensajes.error("Paciente no encontrado. Regístrelo primero.");
                return;
            }

            int nuevoId = DatabaseContext.tablaCitas.size() + 1;
            Cita nuevaCita = new Cita(nuevoId, pac, med, fecha, hora, "PENDIENTE");

            // El controlador llamará al DAO y validará disponibilidad
            controller.registrarNuevaCita(nuevaCita);
            
            limpiarCampos();

        } catch (Exception e) {
            Mensajes.error("Error: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtDniPaciente.setText("");
        txtFecha.setText("");
        txtHora.setText("");
    }
}