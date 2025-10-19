// Representa cada esfera del dragón con número y ubicación
public class EsferaDragon {
    private int numero;
    private String ubicacion;

    public EsferaDragon(int numero, String ubicacion) {
        this.numero = numero;
        this.ubicacion = ubicacion;
    }

    public int getNumero() {
        return numero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    @Override
    public String toString() {
        return "Esfera " + numero + " en " + ubicacion;
    }
}
