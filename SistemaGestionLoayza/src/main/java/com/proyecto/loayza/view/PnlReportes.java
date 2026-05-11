package com.proyecto.loayza.view;

import com.proyecto.loayza.controller.ReporteController;
import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PnlReportes extends JInternalFrame {
    private ReporteController controller;
    private JTable tablaResumen;

    public PnlReportes() {
        super("Dashboard de Gestión Hospitalaria", true, true, true, true);
        controller = new ReporteController();
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Especialidad", "N° de Citas"}, 0);
        Map<String, Integer> datos = controller.obtenerDemandaPorEspecialidad();
        
        datos.forEach((esp, cant) -> model.addRow(new Object[]{esp, cant}));
        
        tablaResumen = new JTable(model);
        add(new JLabel("Especialidades con mayor demanda", JLabel.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(tablaResumen), BorderLayout.CENTER);
    }
}