package com.proyecto.loayza.view;

import javax.swing.*;
import java.awt.*;

public class FrmMenuPrincipal extends JFrame {

    private JDesktopPane desktop;

    public FrmMenuPrincipal() {
        inicioComponentes();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Sistema de Gestión Hospitalaria - Hospital Nacional Arzobispo Loayza");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicioComponentes() {
        desktop = new JDesktopPane();
        desktop.setBackground(new Color(45, 62, 80)); 
        add(desktop, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        // --- MENÚ ARCHIVO ---
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemSalir = new JMenuItem("Cerrar Sesión");
        itemSalir.addActionListener(e -> {
            this.dispose();
            new FrmLogin().setVisible(true);
        });
        menuArchivo.add(itemSalir);

        // --- MENÚ GESTIÓN ---
        JMenu menuGestion = new JMenu("Gestión");
        
        JMenuItem itemPacientes = new JMenuItem("Registro de Pacientes");
        itemPacientes.addActionListener(e -> abrirFormulario(new PnlGestionPacientes()));
        
        JMenuItem itemHorarios = new JMenuItem("Gestión de Horarios Médicos");
        itemHorarios.addActionListener(e -> abrirFormulario(new PnlGestionHorarios()));
        
        menuGestion.add(itemPacientes);
        menuGestion.add(itemHorarios);

        // --- MENÚ CITAS ---
        JMenu menuCitas = new JMenu("Citas Médicas");
        JMenuItem itemAgendar = new JMenuItem("Agendar Cita Médica");
        itemAgendar.addActionListener(e -> abrirFormulario(new PnlAgendarCita()));
        menuCitas.add(itemAgendar);

        // --- MENÚ REPORTES ---
        JMenu menuReportes = new JMenu("Reportes");
        JMenuItem itemDashboard = new JMenuItem("Dashboard de Demanda");
        itemDashboard.addActionListener(e -> abrirFormulario(new PnlReportes()));
        menuReportes.add(itemDashboard);

        // Agregar menús a la barra
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        menuBar.add(menuCitas);
        menuBar.add(menuReportes);

        setJMenuBar(menuBar);
    }

    private void abrirFormulario(JInternalFrame f) {
        // Lógica para no duplicar ventanas
        boolean mostrar = true;
        for (JInternalFrame frame : desktop.getAllFrames()) {
            if (frame.getClass().equals(f.getClass())) {
                try {
                    frame.setSelected(true);
                    mostrar = false;
                } catch (Exception ex) {}
                break;
            }
        }
        if (mostrar) {
            desktop.add(f);
            f.setVisible(true);
            // Centrar automáticamente
            Dimension desktopSize = desktop.getSize();
            Dimension jInternalFrameSize = f.getSize();
            f.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                          (desktopSize.height - jInternalFrameSize.height) / 2);
        }
    }
}