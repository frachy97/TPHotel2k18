package app.model;

import app.enums.EstadoHab;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva extends Entrada {

    private static int contadorReservas = 0;

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
