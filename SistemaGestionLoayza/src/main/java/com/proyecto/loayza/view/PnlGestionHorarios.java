package com.proyecto.loayza.view;

import com.proyecto.loayza.db.DatabaseContext;
import com.proyecto.loayza.model.Medico;
import com.proyecto.loayza.utils.Mensajes;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PnlGestionHorarios extends JInternalFrame {

    private JComboBox<Medico> cbMedicos;
    private JComboBox<String> cbTurno;
    private JCheckBox chkLunes, chkMartes, chkMiercoles, chkJueves, chkViernes;
    private JTable tablaHorarios;
    private DefaultTableModel modelo;

    public PnlGestionHorarios() {
        super("Configuración de Disponibilidad Médica", true, true, true, true);
        inicioComponentes();
        setSize(700, 450);
    }

    private void inicioComponentes() {
        setLayout(new BorderLayout());

        // --- Panel Superior: Configuración ---
        JPanel pnlConfig = new JPanel(new GridBagLayout());
        pnlConfig.setBorder(BorderFactory.createTitledBorder("Asignación de Turnos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Selección de Médico
        gbc.gridx = 0; gbc.gridy = 0;
        pnlConfig.add(new JLabel("Seleccionar Médico:"), gbc);
        cbMedicos = new JComboBox<>(DatabaseContext.tablaMedicos.toArray(new Medico[0]));
        gbc.gridx = 1; gbc.gridwidth = 2;
        pnlConfig.add(cbMedicos, gbc);

        // Selección de Turno
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        pnlConfig.add(new JLabel("Turno:"), gbc);
        cbTurno = new JComboBox<>(new String[]{"Mañana (08:00 - 13:00)", "Tarde (14:00 - 19:00)", "Guardia (20:00 - 07:00)"});
        gbc.gridx = 1; gbc.gridwidth = 2;
        pnlConfig.add(cbTurno, gbc);

        // Días de la semana
        JPanel pnlDias = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkLunes = new JCheckBox("Lun"); chkMartes = new JCheckBox("Mar");
        chkMiercoles = new JCheckBox("Mie"); chkJueves = new JCheckBox("Jue");
        chkViernes = new JCheckBox("Vie");
        pnlDias.add(chkLunes); pnlDias.add(chkMartes); pnlDias.add(chkMiercoles);
        pnlDias.add(chkJueves); pnlDias.add(chkViernes);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        pnlConfig.add(pnlDias, gbc);

        JButton btnAsignar = new JButton("Actualizar Horario");
        gbc.gridy = 3; gbc.gridx = 1; gbc.gridwidth = 1;
        pnlConfig.add(btnAsignar, gbc);

        add(pnlConfig, BorderLayout.NORTH);

        // --- Panel Central: Visualización ---
        modelo = new DefaultTableModel(new Object[]{"Médico", "Especialidad", "Turno", "Días"}, 0);
        tablaHorarios = new JTable(modelo);
        add(new JScrollPane(tablaHorarios), BorderLayout.CENTER);

        // Eventos
        btnAsignar.addActionListener(e -> asignarHorario());
        
        cargarDatosPrueba();
    }

    private void asignarHorario() {
        Medico m = (Medico) cbMedicos.getSelectedItem();
        String turno = cbTurno.getSelectedItem().toString();
        String dias = "";
        if(chkLunes.isSelected()) dias += "Lun ";
        if(chkMartes.isSelected()) dias += "Mar ";
        if(chkMiercoles.isSelected()) dias += "Mie ";
        if(chkJueves.isSelected()) dias += "Jue ";
        if(chkViernes.isSelected()) dias += "Vie ";

        if (dias.isEmpty()) {
            Mensajes.error("Debe seleccionar al menos un día.");
            return;
        }

        modelo.addRow(new Object[]{m.getNombreCompleto(), m.getEspecialidad().getNombre(), turno, dias});
        Mensajes.informar("Horario actualizado para el Dr. " + m.getApellidos());
    }

    private void cargarDatosPrueba() {
        // Para que la tabla no inicie vacía en la sustentación
        if (!DatabaseContext.tablaMedicos.isEmpty()) {
            Medico m = DatabaseContext.tablaMedicos.get(0);
            modelo.addRow(new Object[]{m.getNombreCompleto(), m.getEspecialidad().getNombre(), "Mañana", "Lun Mar Mie"});
        }
    }
}