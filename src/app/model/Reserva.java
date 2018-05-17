package app.model;

import app.enums.EstadoHab;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva implements Serializable {

    private static int contadorReservas;

    private String nroReserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;

    public Reserva(Cliente cliente, Habitacion habitacion,
                   LocalDate fechaIngreso, LocalDate fechaSalida) {

        nroReserva = String.valueOf(++contadorReservas);
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.habitacion.setEstado(EstadoHab.RESERVADA);
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }
}
