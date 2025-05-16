package codigo_completo;

import codigo_completo.vista.ConsolaVista;
import codigo_completo.controlador.ControladorPrincipal;
import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Registro registro = new Registro();
            ConsolaVista vista = new ConsolaVista();
            new ControladorPrincipal(registro, vista);
        });
    }
}
