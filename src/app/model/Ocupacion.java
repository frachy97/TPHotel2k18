package app.model;

import app.enums.EstadoHab;
import app.enums.Producto;
import app.utils.FechaHoraUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ocupacion {

    private static int contador = 0;

    private String nroId;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDateTime ingreso = LocalDateTime.now();
    private LocalDateTime salida;
    private int cantDias;
    private List<Producto> consumos = new ArrayList<>();

    public Ocupacion(Cliente cliente, Habitacion habitacion, LocalDateTime salida) {
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.habitacion.setEstado(EstadoHab.OCUPADA);
        this.ingreso = ingreso;
        this.salida = salida;
        nroId = String.valueOf(++contador);
    }

    public int getCantDias() {
        return cantDias;
    }

    public void calcularCantDias() {
        // TODO: 12/05/18 sacar la cantidad de dias basado en las fecha de ingreso y los dias de validez de la ocupacion
    }

    public LocalDateTime getIngreso() {
        return ingreso;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    @Override
    public String toString() {
        return "\nID Ocupacion: " + nroId + "\nDNI: " + cliente.getDni() + "\nCliente: " +
                cliente.getNombreCompleto() + "\nN°Habitacion: " + habitacion.getNumero() +
                "\nFecha ingreso: " + FechaHoraUtil.formatearFechaYHora(ingreso) + "\nFecha salida: " +
                FechaHoraUtil.formatearFechaYHora(salida) + "\n";
    }
}
