package com.proyecto.loayza.view;

import com.proyecto.loayza.controller.UsuarioController;
import com.proyecto.loayza.model.Usuario;
import com.proyecto.loayza.utils.Mensajes;
import java.awt.*;
import javax.swing.*;

public class FrmLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar, btnSalir;
    private UsuarioController controller;

    public FrmLogin() {
        controller = new UsuarioController();
        inicioComponentes();
        configurarVentana();
    }

    private void configurarVentana() {
        setTitle("Acceso al Sistema - Hospital Loayza");
        setSize(400, 250);
        setLocationRelativeTo(null); // Centrar en pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void inicioComponentes() {
        // Panel principal con diseño de cuadrícula (Grid)
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margen entre elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta Título
        JLabel lblTitulo = new JLabel("INICIO DE SESIÓN", JLabel.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        // Usuario
        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        
        txtUsuario = new JTextField(15);
        gbc.gridx = 1;
        panel.add(txtUsuario, gbc);

        // Contraseña
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Contraseña:"), gbc);
        
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(txtPassword, gbc);

        // Botones
        JPanel panelBotones = new JPanel();
        btnIngresar = new JButton("Ingresar");
        btnSalir = new JButton("Salir");
        panelBotones.add(btnIngresar);
        panelBotones.add(btnSalir);

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(panelBotones, gbc);

        add(panel);

        // Eventos de los botones
        btnIngresar.addActionListener(e -> ejecutarLogin());
        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void ejecutarLogin() {
        String user = txtUsuario.getText();
        String pass = new String(txtPassword.getPassword());

        // Validamos con el controlador
        Usuario usuarioLogueado = controller.iniciarSesion(user, pass);

        if (usuarioLogueado != null) {
            // Si es correcto, abrimos el Menú Principal
            this.dispose(); // Cierra el login
            new FrmMenuPrincipal().setVisible(true); 
        }
    }
}