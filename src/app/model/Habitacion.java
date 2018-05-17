package app.model;

import app.enums.EstadoHab;
import app.enums.TipoHab;

import java.io.Serializable;

public class Habitacion implements Serializable {

    private String numero;
    private EstadoHab estado;
    private TipoHab tipo;
    private double precioPorDia;

    public Habitacion(String numero, TipoHab tipo, double precioPorDia) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorDia = precioPorDia;
        estado = EstadoHab.LIBRE;
    }

    public String getNumero() {
        return numero;
    }

    public EstadoHab getEstado() {
        return estado;
    }

    public void setEstado(EstadoHab estado) {
        this.estado = estado;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    @Override
    public String toString() {
        return "Habitacion " + numero + "\nTipo: " + tipo + "\nPrecio: " + precioPorDia + " x dia" + "\n" +
                "Estado: " + estado + "\n";
    }
}
