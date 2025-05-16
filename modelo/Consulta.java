/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_completo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Consulta {
    private final Usuario usuario;
    private final IMC imc;
    private final List<String> recomendaciones;

    public Consulta(Usuario usuario, IMC imc) {
        this.usuario = usuario;
        this.imc = imc;
        this.recomendaciones = new ArrayList<>();
    }

    public void agregarRecomendacion(String recomendacion) {
        recomendaciones.add(recomendacion);
    }

    public void mostrarRecomendaciones() {
        for (String recomendacion : recomendaciones) {
            System.out.println(recomendacion);
        }
    }

    public void seguimiento() {
        System.out.println("Usuario: " + usuario.getNombreUsuario());
        System.out.println("IMC: " + imc.calcularIMC());
        System.out.println("Recomendaciones:");
        mostrarRecomendaciones();
    }
}
