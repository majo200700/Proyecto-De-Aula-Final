
package codigo_completo;

import codigo_completo.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class Registro {
    private List<Usuario> usuarios;

    public Registro() {
        usuarios = new ArrayList<>();
    }

    public void registrarUsuario(String nombreUsuario, String contraseña, boolean esCliente) {
        Usuario usuario;
        if (esCliente) {
            usuario = new Cliente(nombreUsuario, contraseña);
        } else {
            usuario = new Especialista(nombreUsuario, contraseña);
        }
        usuarios.add(usuario);
    }

    public Usuario iniciarSesion(String nombreUsuario, String contraseña) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) && usuario.validarContraseña(contraseña)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean esEspecialista(Usuario usuario) {
        return usuario instanceof Especialista;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
