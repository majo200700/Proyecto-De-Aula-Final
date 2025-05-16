package codigo_completo.vista;

import codigo_completo.controlador.ControladorPrincipal;
import codigo_completo.modelo.Especialista;

import javax.swing.*;
import java.awt.*;

public class VentanaEspecialista extends JFrame {
    private final Especialista especialista;
    private final ControladorPrincipal controlador;

    private final Color COLOR_PRIMARIO = new Color(34, 139, 34);
    private final Color COLOR_SECUNDARIO = new Color(144, 238, 144);

    public VentanaEspecialista(Especialista especialista, ControladorPrincipal controlador) {
        this.especialista = especialista;
        this.controlador = controlador;

        setTitle("Panel del Especialista - " + especialista.getNombreUsuario());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarUI();
        setVisible(true);
    }

    private void configurarUI() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel("BIENVENIDO ESPECIALISTA", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setBackground(COLOR_PRIMARIO);
        titulo.setOpaque(true);
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel opciones = new JPanel(new GridLayout(4, 1, 15, 15));
        opciones.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));
        opciones.setBackground(Color.WHITE);

        JButton btnVerIMCs = new JButton("Ver IMC de Clientes");
        btnVerIMCs.addActionListener(e -> controlador.verIMCClientes());

        JButton btnChat = new JButton("Abrir Chat con Cliente");
        btnChat.addActionListener(e -> {
            String cliente = JOptionPane.showInputDialog(this, "Nombre del cliente:");
            if (cliente != null && !cliente.trim().isEmpty()) {
                // Asegúrate de que exista VentanaChatCliente con ese constructor
                new codigo_completo.VentanaChatCliente(especialista.getNombreUsuario(), cliente);
            }
        });

        JButton btnVerCitas = new JButton("Ver Citas Agendadas");
        btnVerCitas.addActionListener(e -> controlador.verCitasEspecialista());

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            controlador.volverAlLogin();
        });

        opciones.add(btnVerIMCs);
        opciones.add(btnChat);
        opciones.add(btnVerCitas);
        opciones.add(btnCerrarSesion);

        panelPrincipal.add(opciones, BorderLayout.CENTER);
        add(panelPrincipal);
    }
}
