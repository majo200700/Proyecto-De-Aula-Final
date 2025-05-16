package codigo_completo.controlador;

import codigo_completo.modelo.*;
import codigo_completo.Registro;
import codigo_completo.vista.ConsolaVista;
import java.util.List;
import javax.swing.JOptionPane;
import codigo_completo.vista.VentanaEspecialista;
import codigo_completo.vista.VentanaCliente;

public class ControladorPrincipal {
    private Registro registro;
    private ConsolaVista vista;
    private ChatService chatService;
    private Usuario usuarioActual;

    public ControladorPrincipal(Registro registro, ConsolaVista vista) {
        this.registro = registro;
        this.vista = vista;
        this.chatService = new ChatService();
        this.vista.setControlador(this);
        this.vista.iniciar();
    }

    public void iniciarSesion(String nombre, String pass) {
    RegistroUsuarios registro = new RegistroUsuarios();
    List<Usuario> usuarios = registro.cargarUsuarios();

    for (Usuario u : usuarios) {
        if (u.getNombreUsuario().equals(nombre) && u.validarContraseña(pass)) {
            this.usuarioActual = u; 
            if (u instanceof Especialista) {
                new VentanaEspecialista((Especialista) u, this);
            } else if (u instanceof Cliente) {
                new VentanaCliente((Cliente) u, this);  
            }
            return;
        }
    }

    vista.mostrarError("Credenciales incorrectas.");
    }
    
    public void enviarMensajePrivado(String emisor, String receptor, String mensaje) {
    chatService.enviarMensaje(emisor, receptor, mensaje);  
    }
  
    public void registrarUsuario(String usuario, String contrasena) {
        registro.registrarUsuario(usuario, contrasena, false);
        vista.mostrarConfirmacion("¡Registro exitoso!");
        vista.mostrarVentanaLogin();
    }

    public void calcularIMC(double peso, double altura) {
        IMC calculadora = new IMC(peso, altura);
        ResultadoIMC resultado = calculadora.obtenerResultadoIMC();
        vista.mostrarResultadoIMC(resultado.getImc(), resultado.getCategoria());
    }

    public void enviarMensaje(String mensaje) {
        chatService.enviarMensaje(usuarioActual.getNombreUsuario(), "Nutricionista", mensaje);
    }

    public void agendarCita(String fecha, String hora, String nutricionista) {
        vista.mostrarConfirmacion("Cita agendada con " + nutricionista + " para el " + fecha + " a las " + hora);
    }

    public Registro getRegistro() {
        return this.registro;
    }
    
    public void volverAlLogin() {
    vista.mostrarVentanaLogin();
    }

    public void registrarUsuarioEnArchivo(String nombre, String pass, boolean esCliente) {
    RegistroUsuarios registro = new RegistroUsuarios();
    Usuario nuevo = esCliente ? new Cliente(nombre, pass) : new Especialista(nombre, pass);
    registro.guardarUsuario(nuevo);
}
    
    public void verIMCClientes() {
    RegistroUsuarios registro = new RegistroUsuarios();
    List<Cliente> clientes = registro.obtenerClientes();

    StringBuilder sb = new StringBuilder();
    for (Cliente c : clientes) {
        ResultadoIMC imc = c.getUltimoIMC();
        sb.append("Cliente: ").append(c.getNombreUsuario()).append("\n");
        if (imc != null) {
            sb.append("  IMC: ").append(String.format("%.2f", imc.getImc())).append("\n");
            sb.append("  Categoría: ").append(imc.getCategoria()).append("\n");
        } else {
            sb.append("  IMC no registrado.\n");
        }
        sb.append("\n");
    }

    JOptionPane.showMessageDialog(null, sb.toString(), "IMC de Clientes", JOptionPane.INFORMATION_MESSAGE);
}

public void verCitasEspecialista(){
    String mensaje = """
        - Cita con Jorge - 10/05/2025 a las 10:00am
        - Cita con Juan - 12/05/2025 a las 3:00pm
        """;
    JOptionPane.showMessageDialog(null, mensaje, "Citas Agendadas", JOptionPane.INFORMATION_MESSAGE);
    }
}