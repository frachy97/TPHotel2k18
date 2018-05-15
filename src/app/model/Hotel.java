package app.model;

import app.enums.Nacionalidad;
import app.enums.TipoHab;
import app.utils.FechaHoraUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Hotel {

    private Map<String, Habitacion> habitaciones = new TreeMap<>();
    private Map<String, Conserje> conserjes = new TreeMap<>();
    private Map<String, Cliente> clientes = new TreeMap<>();
    private Map<String, Reserva> reservas = new TreeMap<>();
    private Map<String, Ocupacion> ocupaciones = new TreeMap<>();
    private Admin admin;
    private double totalIngresos = 0;

    public Map<String, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public Map<String, Conserje> getConserjes() {
        return conserjes;
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public Map<String, Reserva> getReservas() {
        return reservas;
    }

    public static void main(String[] args) {

        Hotel h = new Hotel();
        Cliente cliente = new Cliente("Perdo", "Moraes", "34222345", Nacionalidad.BRASIL, "234 Piedrabuena, Rio");
        Habitacion habitacion = new Habitacion("101", TipoHab.INDIVIDUAL, 199.99);
        Ocupacion ocupacion = new Ocupacion(cliente, habitacion,
                                LocalDateTime.of(2018, Month.MAY, 13, FechaHoraUtil.horaCheckOut, FechaHoraUtil.minutoCero));
        System.out.println(ocupacion);
        System.out.println(habitacion);
        System.out.println(cliente);

        System.out.println(LocalDateTime.now().getYear());
    }
}
