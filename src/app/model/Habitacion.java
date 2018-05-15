package app.model;

import app.enums.EstadoHab;
import app.enums.TipoHab;

public class Habitacion {

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
}
