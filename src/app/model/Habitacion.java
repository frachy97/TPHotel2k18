package app.model;


import app.enums.TipoHab;

import java.io.Serializable;

public class Habitacion implements Serializable {

    private String numero;
    private boolean estado;//true libre, false ocupado

    private TipoHab tipo;
    private double precioPorDia;

    public Habitacion(String numero, TipoHab tipo, double precioPorDia) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioPorDia = precioPorDia;
        estado = true;
    }

    public TipoHab getTipo() {
        return tipo;
    }

    public String getNumero() {
        return numero;
    }


    public boolean elEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public String toStringEstadoHab() {
        String aux;
        if (estado == true) {
            aux = "LIBRE";
        } else {
            aux = "OCUPADO";
        }
        return aux;
    }

    @Override
    public String toString() {
        return "Habitacion " + numero + "\nTipo: " + tipo + "\nPrecio: " + precioPorDia + " x dia" + "\n" +
                "Estado: " + toStringEstadoHab() + "\n";
    }

    public void setTipo(TipoHab individual) {
        this.tipo = individual;
    }
}
