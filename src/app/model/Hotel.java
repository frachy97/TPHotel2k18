package app.model;

import java.util.TreeMap;

public class Hotel {

    private static Hotel laInstancia = new Hotel();

    private TreeMap<String, Habitacion> habitaciones = new TreeMap<>();
    private TreeMap<String, Conserje> conserjes = new TreeMap<>();
    private TreeMap<String, Cliente> clientes = new TreeMap<>();
    private TreeMap<String, Reserva> reservas = new TreeMap<>();
    private Admin admin = new Admin("admin", new Password("password"), "nombre");
    private double totalIngresos = 0;

    private Hotel() {
    }

    public static Hotel getInstancia() {
        return laInstancia;
    }

    public Admin getAdmin() {
        return admin;
    }
}
