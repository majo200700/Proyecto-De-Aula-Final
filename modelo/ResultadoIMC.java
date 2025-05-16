/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo_completo.modelo;

public class ResultadoIMC {
    private double imc;
    private String categoria;

    public ResultadoIMC(double imc, String categoria) {
        this.imc = imc;
        this.categoria = categoria;
    }

    public double getImc() {
        return imc;
    }

    public String getCategoria() {
        return categoria;
    }
}
