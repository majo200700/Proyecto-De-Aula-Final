/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_completo.modelo;

import java.io.*;
import java.util.*;

public class RegistroUsuarios {
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";

    public void guardarUsuario(Usuario usuario) {
        try (FileWriter fw = new FileWriter(ARCHIVO_USUARIOS, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            String tipo = (usuario instanceof Especialista) ? "E" : "C";
            out.println(tipo + "," + usuario.getNombreUsuario() + "," + usuario.getContraseña());

        } catch (IOException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    public List<Usuario> cargarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        File archivo = new File(ARCHIVO_USUARIOS);
        if (!archivo.exists()) return usuarios;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String tipo = partes[0];
                    String nombre = partes[1];
                    String pass = partes[2];

                    if (tipo.equals("C")) {
                        usuarios.add(new Cliente(nombre, pass));
                    } else if (tipo.equals("E")) {
                        usuarios.add(new Especialista(nombre, pass));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }

        return usuarios;
    }

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        for (Usuario u : cargarUsuarios()) {
            if (u instanceof Cliente) clientes.add((Cliente) u);
        }
        return clientes;
    }

    public List<Especialista> obtenerEspecialistas() {
        List<Especialista> especialistas = new ArrayList<>();
        for (Usuario u : cargarUsuarios()) {
            if (u instanceof Especialista) especialistas.add((Especialista) u);
        }
        return especialistas;
    }
}
