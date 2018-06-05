package app.model;

import app.enums.Producto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Serializable {


    private String nroReserva;
    private Cliente cliente;
    private Habitacion habitacion;

    private LocalDateTime horarioCreacion = LocalDateTime.now();
    /*30/05/2018 Nuevo metodo que registra cuando la reserva fue confirmada con la ocupacion del cliente*/
    private LocalDateTime horarioOcupacion;

    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private boolean confirmada = false;
    private List<Producto> consumos = new ArrayList<>();

    public Reserva(Cliente cliente, Habitacion habitacion,
                   LocalDate fechaIngreso, LocalDate fechaSalida) {

        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Producto> getConsumos() {
        return consumos;
    }


    public String getNroReserva() {
        return nroReserva;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    /*30/05/2018 registra la ocupacion del cliente*/
    public void confirmarOcupacion() {
        confirmada = true;
        horarioOcupacion = LocalDateTime.now();
        habitacion.setEstado(false);
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    @Override
    public String toString() {
        return "ID reserva: " + nroReserva + "\nHabitacion: " + habitacion.getNumero() + "\nCliente: " +
                cliente.getNombreCompleto() + ", DNI " + cliente.getDni() + "\nIngreso: " + fechaIngreso +
                "\nSalida: " + fechaSalida + "\n";
    }

    public void setNroReserva(String nroReserva) {
        this.nroReserva = nroReserva;
    }
}