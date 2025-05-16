package codigo_completo.vista;

import codigo_completo.VentanaChatCliente;
import codigo_completo.modelo.*;
import codigo_completo.controlador.ControladorPrincipal;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import codigo_completo.modelo.RegistroUsuarios;
import codigo_completo.vista.VentanaCliente;
import codigo_completo.vista.VentanaEspecialista;

public class ConsolaVista {
    private ControladorPrincipal controlador;
    private JFrame ventanaActual;
    private Usuario usuarioActual;
    private JTextArea areaChatCliente;
    private JTextArea areaChatEspecialista;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    private final Color COLOR_PRIMARIO = new Color(34, 139, 34);
    private final Color COLOR_SECUNDARIO = new Color(144, 238, 144);
    private final Color COLOR_OSCURO = new Color(0, 100, 0);

    public void setControlador(ControladorPrincipal controlador) {
        this.controlador = controlador;
    }

    public void iniciar() {
        crearVentanaLogin();
    }

    public void mostrarVentanaLogin() {
        if (ventanaActual != null) {
            ventanaActual.dispose();
        }
        crearVentanaLogin();
    }

    private void crearVentanaLogin() {
        JFrame ventana = new JFrame("Nutrición Saludable - Login");
        ventana.setSize(400, 350);
        ventana.setLayout(new BorderLayout(10, 10));

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        JLabel lblTitulo = new JLabel("NUTRICIÓN SALUDABLE", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtUsuario = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);

        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.addActionListener(e -> {
            controlador.iniciarSesion(txtUsuario.getText(), new String(txtPassword.getPassword()));
        });

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(e -> {
            ventana.dispose();
            crearVentanaRegistro();
        });

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panelCentral.add(new JLabel(new ImageIcon("nutricion_icono.png")), gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy = 1; panelCentral.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1; panelCentral.add(txtUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; panelCentral.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; panelCentral.add(txtPassword, gbc);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.add(btnLogin);
        panelBotones.add(btnRegistro);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panelCentral.add(panelBotones, gbc);

        ventana.add(panelHeader, BorderLayout.NORTH);
        ventana.add(panelCentral, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        this.ventanaActual = ventana;
    }

    private void crearVentanaRegistro() {
        JFrame ventana = new JFrame("Registro de Usuario");
        ventana.setSize(400, 300);
        ventana.setLayout(new BorderLayout(10, 10));

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(COLOR_PRIMARIO);
        JLabel lblTitulo = new JLabel("REGISTRO DE USUARIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelHeader.add(lblTitulo);

        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtUsuario = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);

        JButton btnRegistro = new JButton("Registrarse");
        btnRegistro.addActionListener(e -> {
        String nombre = txtUsuario.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        String[] opciones = {"Especialista", "Cliente"};
        int tipo = JOptionPane.showOptionDialog(
            ventana, 
            "¿Qué tipo de usuario desea registrar?",
            "Tipo de Usuario",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        if (tipo == 0 || tipo == 1) {
            controlador.registrarUsuarioEnArchivo(nombre, pass, tipo == 1);  
            JOptionPane.showMessageDialog(ventana, "Usuario registrado exitosamente.");
            ventana.dispose();
            crearVentanaLogin();  // Regresa al login
             }
            });


        gbc.gridx = 0; gbc.gridy = 0; panelCentral.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1; panelCentral.add(txtUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; panelCentral.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; panelCentral.add(txtPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelCentral.add(btnRegistro, gbc);

        ventana.add(panelHeader, BorderLayout.NORTH);
        ventana.add(panelCentral, BorderLayout.CENTER);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        this.ventanaActual = ventana;
    }
    
    public void mostrarVentanaEspecialista(Especialista especialista) {
    if (ventanaActual != null) {
        ventanaActual.dispose();
    }

    JFrame ventana = new JFrame("Panel del Especialista - " + especialista.getNombreUsuario());
    ventana.setSize(800, 600);

    JPanel panel = new JPanel(new BorderLayout());
    JLabel titulo = new JLabel("BIENVENIDO ESPECIALISTA", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 26));
    panel.add(titulo, BorderLayout.NORTH);

    JPanel opciones = new JPanel(new GridLayout(3, 1, 10, 10));

    JButton btnVerIMCs = new JButton("Ver IMC de Clientes");
    btnVerIMCs.addActionListener(e -> controlador.verIMCClientes());

    JButton btnChat = new JButton("Abrir Chat con Cliente");
    btnChat.addActionListener(e -> {
        String cliente = JOptionPane.showInputDialog("Nombre del cliente:");
        if (cliente != null && !cliente.trim().isEmpty()) {
            new VentanaChatCliente(especialista.getNombreUsuario(), cliente);
        }
    });

    JButton btnVerCitas = new JButton("Ver Citas Agendadas");
    btnVerCitas.addActionListener(e -> controlador.verCitasEspecialista());

    JButton btnCerrarSesion = new JButton("Cerrar Sesión");
    btnCerrarSesion.addActionListener(e -> mostrarVentanaLogin());

    opciones.add(btnVerIMCs);
    opciones.add(btnChat);
    opciones.add(btnVerCitas);
    opciones.add(btnCerrarSesion);

    panel.add(opciones, BorderLayout.CENTER);
    ventana.add(panel);
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    this.ventanaActual = ventana;
}

    

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(ventanaActual, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void mostrarConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(ventanaActual, mensaje, "Confirmación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void mostrarResultadoIMC(double imc, String categoria) {
        JOptionPane.showMessageDialog(ventanaActual, 
            "IMC: " + df.format(imc) + " - " + categoria, 
            "Resultado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void agregarMensajeChat(String mensaje, boolean esEspecialista) {
        if (esEspecialista) {
            areaChatEspecialista.append("Nutricionista: " + mensaje + "\n");
        } else {
            areaChatCliente.append("Tú: " + mensaje + "\n");
        }
    }
}