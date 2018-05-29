package app.model;

import java.io.Serializable;
import java.util.TreeMap;

public class Hotel {

    /*Diseño "singleton". Se pretende que unicamente haya una instancia de Hotel. Al crearla dentro de la clase
     * y solo pudiendo ser accedida mediante un metodo estatico, se cumple con lo pretendido.*/
    private static Hotel laInstancia = new Hotel();

    private TreeMap<String, Habitacion> habitaciones = new TreeMap<>();
    private TreeMap<String, Conserje> conserjes = new TreeMap<>();
    private TreeMap<String, Cliente> clientes = new TreeMap<>();
    private TreeMap<String, Reserva> reservas = new TreeMap<>();
    private Admin admin;
    private double totalIngresos = 0;

    /*Constructor privado. Esto impide instanciar la clase libremente*/
    private Hotel() {
    }

    /*El objeto de tipo Hotel puede ser accedido mediante este metodo.*/
    public static Hotel getInstancia() {
        return laInstancia;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public TreeMap<String, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public TreeMap<String, Conserje> getConserjes() {
        return conserjes;
    }

    public TreeMap<String, Cliente> getClientes() {
        return clientes;
    }

    public TreeMap<String, Reserva> getReservas() {
        return reservas;
    }
}
