package codigo_completo.vista;

import codigo_completo.controlador.ControladorPrincipal;
import codigo_completo.modelo.Cliente;
import codigo_completo.modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class VentanaCliente extends JFrame {
    private final Usuario usuarioActual;
    private final ControladorPrincipal controlador;
    private final DecimalFormat df = new DecimalFormat("#.##");

    private final Color COLOR_PRIMARIO = new Color(34, 139, 34);
    private final Color COLOR_SECUNDARIO = new Color(144, 238, 144);
    private final Color COLOR_OSCURO = new Color(0, 100, 0);

    public VentanaCliente(Usuario usuario, ControladorPrincipal controlador) {
        this.usuarioActual = usuario;
        this.controlador = controlador;

        setTitle("Bienvenido " + usuario.getNombreUsuario());
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarInterfaz();
        setVisible(true);
    }

    private void configurarInterfaz() {
        JPanel cardPanel = new JPanel(new CardLayout());

        cardPanel.add(crearPanelInicio(), "INICIO");
        cardPanel.add(crearPanelIMC(), "IMC");
        cardPanel.add(crearPanelCitas(), "CITAS");  // CHAT eliminado como panel

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        navPanel.setBackground(COLOR_SECUNDARIO);

        String[] pestañas = {"INICIO", "IMC", "CHAT", "CITAS"};

        for (String pestaña : pestañas) {
            JButton btn = new JButton(pestaña);

            if (pestaña.equals("CHAT")) {
                // Abre ventana externa con nombre del especialista
                btn.addActionListener(e -> {
                    String especialista = JOptionPane.showInputDialog(this, "Ingrese el nombre del especialista con quien desea chatear:");
                    if (especialista != null && !especialista.trim().isEmpty()) {
                        new codigo_completo.VentanaChatCliente(usuarioActual.getNombreUsuario(), especialista.trim());
                    } else {
                        JOptionPane.showMessageDialog(this, "Debe ingresar un nombre válido.");
                    }
                });
            } else {
                btn.addActionListener(e -> {
                    ((CardLayout) cardPanel.getLayout()).show(cardPanel, pestaña);
                });
            }

            navPanel.add(btn);
        }

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> {
            dispose();
            controlador.volverAlLogin();
        });
        navPanel.add(btnCerrarSesion);

        add(navPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
    }

    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        JLabel lblBienvenida = new JLabel("BIENVENIDO/A", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 48));
        lblBienvenida.setForeground(COLOR_OSCURO);
        panel.add(lblBienvenida, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearPanelIMC() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("CALCULADORA DE IMC", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(COLOR_OSCURO);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        panel.add(new JLabel("Peso (kg):"), gbc);
        JTextField txtPeso = new JTextField(10);
        gbc.gridx = 1;
        panel.add(txtPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Altura (m):"), gbc);
        JTextField txtAltura = new JTextField(10);
        gbc.gridx = 1;
        panel.add(txtAltura, gbc);

        JButton btnCalcular = new JButton("Calcular IMC");
        btnCalcular.addActionListener(e -> {
            try {
                double peso = Double.parseDouble(txtPeso.getText());
                double altura = Double.parseDouble(txtAltura.getText());
                controlador.calcularIMC(peso, altura);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnCalcular, gbc);

        return panel;
    }

    private JPanel crearPanelCitas() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel("AGENDAR CITA MÉDICA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(COLOR_OSCURO);
        panel.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelForm.add(new JLabel("Fecha:"), gbc);
        JTextField txtFecha = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtFecha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelForm.add(new JLabel("Hora:"), gbc);
        JTextField txtHora = new JTextField(15);
        gbc.gridx = 1;
        panelForm.add(txtHora, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panelForm.add(new JLabel("Nutricionista:"), gbc);
        String[] nutricionistas = {"Dr. García", "Dra. Martínez", "Dr. López", "Dra. Rodríguez"};
        JComboBox<String> comboNutricionistas = new JComboBox<>(nutricionistas);
        gbc.gridx = 1;
        panelForm.add(comboNutricionistas, gbc);

        JButton btnAgendar = new JButton("Agendar Cita");
        btnAgendar.addActionListener(e -> {
            controlador.agendarCita(
                    txtFecha.getText(),
                    txtHora.getText(),
                    (String) comboNutricionistas.getSelectedItem()
            );
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panelForm.add(btnAgendar, gbc);

        panel.add(panelForm, BorderLayout.CENTER);
        return panel;
    }
}
