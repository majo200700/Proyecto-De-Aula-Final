package codigo_completo.modelo;

public class Cliente extends Usuario {
    private double peso;
    private double altura;
    private ResultadoIMC ultimoIMC;

    public Cliente(String nombreUsuario, String contraseña) {
        super(nombreUsuario, contraseña);
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public ResultadoIMC getUltimoIMC() {
        return ultimoIMC;
    }

    public void actualizarUltimoIMC(ResultadoIMC resultadoIMC) {
        this.ultimoIMC = resultadoIMC;
    }
}
