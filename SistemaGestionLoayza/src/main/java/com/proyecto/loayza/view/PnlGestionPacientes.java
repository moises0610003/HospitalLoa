package com.proyecto.loayza.view;

import com.proyecto.loayza.controller.PacienteController;
import com.proyecto.loayza.model.Paciente;
import com.proyecto.loayza.utils.Mensajes;
import com.proyecto.loayza.utils.ValidadorLoayza;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PnlGestionPacientes extends JInternalFrame {

    private JTextField txtDni, txtNombres, txtApellidos, txtEdad, txtSeguro;
    private JButton btnGuardar, btnLimpiar, btnBuscar;
    private JTable tablaPacientes;
    private DefaultTableModel modeloTabla;
    private PacienteController controller;

    public PnlGestionPacientes() {
        super("Gestión de Admisión - Registro de Pacientes", true, true, true, true);
        controller = new PacienteController();
        inicioComponentes();
        setSize(750, 500);
    }

    private void inicioComponentes() {
        setLayout(new BorderLayout());

        // --- PANEL DE FORMULARIO  ---
        JPanel pnlForm = new JPanel(new GridBagLayout());
        pnlForm.setBorder(BorderFactory.createTitledBorder("Datos del Paciente"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: DNI y Botón Buscar
        gbc.gridx = 0; gbc.gridy = 0;
        pnlForm.add(new JLabel("DNI:"), gbc);
        txtDni = new JTextField(10);
        gbc.gridx = 1;
        pnlForm.add(txtDni, gbc);
        
        btnBuscar = new JButton("Buscar");
        gbc.gridx = 2;
        pnlForm.add(btnBuscar, gbc);

        // Fila 2: Nombres y Apellidos
        gbc.gridx = 0; gbc.gridy = 1;
        pnlForm.add(new JLabel("Nombres:"), gbc);
        txtNombres = new JTextField(15);
        gbc.gridx = 1;
        pnlForm.add(txtNombres, gbc);
        
        gbc.gridx = 2;
        pnlForm.add(new JLabel("Apellidos:"), gbc);
        txtApellidos = new JTextField(15);
        gbc.gridx = 3;
        pnlForm.add(txtApellidos, gbc);

        // Fila 3: Edad y Seguro
        gbc.gridx = 0; gbc.gridy = 2;
        pnlForm.add(new JLabel("Edad:"), gbc);
        txtEdad = new JTextField(5);
        gbc.gridx = 1;
        pnlForm.add(txtEdad, gbc);
        
        gbc.gridx = 2;
        pnlForm.add(new JLabel("Seguro (SIS/Otros):"), gbc);
        txtSeguro = new JTextField(10);
        gbc.gridx = 3;
        pnlForm.add(txtSeguro, gbc);

        // Botones de Acción
        JPanel pnlAcciones = new JPanel();
        btnGuardar = new JButton("Registrar Paciente");
        btnLimpiar = new JButton("Limpiar Campos");
        pnlAcciones.add(btnGuardar);
        pnlAcciones.add(btnLimpiar);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        pnlForm.add(pnlAcciones, gbc);

        add(pnlForm, BorderLayout.NORTH);

        // --- TABLA DE REGISTROS ---
        modeloTabla = new DefaultTableModel(new Object[]{"DNI", "Nombres", "Apellidos", "Seguro"}, 0);
        tablaPacientes = new JTable(modeloTabla);
        add(new JScrollPane(tablaPacientes), BorderLayout.CENTER);

        // EVENTOS
        btnGuardar.addActionListener(e -> registrar());
        btnBuscar.addActionListener(e -> buscar());
        btnLimpiar.addActionListener(e -> limpiar());
        
        actualizarTabla();
    }

    private void registrar() {
        if (ValidadorLoayza.campoVacio(txtDni.getText()) || ValidadorLoayza.campoVacio(txtNombres.getText())) {
            Mensajes.error("Por favor, complete los campos obligatorios.");
            return;
        }

        Paciente p = new Paciente(
            txtDni.getText(),
            txtNombres.getText(),
            txtApellidos.getText(),
            Integer.parseInt(txtEdad.getText().isEmpty() ? "0" : txtEdad.getText()),
            txtSeguro.getText()
        );

        controller.registrarPaciente(p);
        actualizarTabla();
        limpiar();
    }

    private void buscar() {
        Paciente p = controller.buscarPaciente(txtDni.getText());
        if (p != null) {
            txtNombres.setText(p.getNombres());
            txtApellidos.setText(p.getApellidos());
            txtEdad.setText(String.valueOf(p.getEdad()));
            txtSeguro.setText(p.getTipoSeguro());
        } else {
            Mensajes.informar("Paciente no encontrado.");
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (Paciente p : controller.listarPacientes()) {
            modeloTabla.addRow(new Object[]{p.getDni(), p.getNombres(), p.getApellidos(), p.getTipoSeguro()});
        }
    }

    private void limpiar() {
        txtDni.setText("");
        txtNombres.setText("");
        txtApellidos.setText("");
        txtEdad.setText("");
        txtSeguro.setText("");
    }
}