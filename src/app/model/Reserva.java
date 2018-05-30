package app.model;

import app.enums.EstadoHab;
import app.enums.Producto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva implements Serializable {

    private static int contadorReservas;

    private String nroReserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private boolean confirmada = false;
    

	private List<Producto> consumos = new ArrayList<>();

    public Reserva(Cliente cliente, Habitacion habitacion,
                   LocalDate fechaIngreso, LocalDate fechaSalida) {

        nroReserva = String.valueOf(++contadorReservas);
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.habitacion.setEstado(EstadoHab.RESERVADA);
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
    }

    public void setConfirmada(boolean confirmada) {
    	this.confirmada = confirmada;
    }
	public List<Producto> getConsumos() {
		return consumos;
	}
    
    
}
