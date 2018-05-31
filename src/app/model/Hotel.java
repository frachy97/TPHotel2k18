package app.model;


import java.util.Map;
import java.util.Scanner;

import java.io.Serializable;

import java.util.TreeMap;

import app.enums.EstadoHab;
import app.menus.Menu;

public class Hotel {

    private static Hotel laInstancia = new Hotel();

    private TreeMap<String, Habitacion> habitaciones = new TreeMap<>();
    private TreeMap<String, Conserje> conserjes = new TreeMap<>();
    private TreeMap<String, Cliente> clientes = new TreeMap<>();
    private TreeMap<String, Reserva> reservas = new TreeMap<>();
    private Admin admin;
    private double totalIngresos = 0;

    private Hotel() {
    }

    public static Hotel getInstancia() {
        return laInstancia;
    }

    public Admin getAdmin() {
        return admin;
    }
  
  
	public TreeMap<String, Cliente> getClientes() {
		return clientes;
	}
	/*
	 * Este metodo busca un cliente x dni si no lo encuentra retorna null
	 */
	public Cliente buscarCliente(Scanner scan) {
		System.out.println("Ingrese dni del Cliente a buscar: \n");
		String dni = scan.nextLine();

		Cliente aux = null;
		boolean flag = false;
		for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
			if (!flag) {
				Cliente value = entry.getValue();
				String key = entry.getKey();
				if (key.equals(dni)) {
					aux = value;
					flag = true;
				}
			}
		}

		if (aux == null) {
			System.out.println("No se encontro ningun cliente con el dni " + dni + " en la base de datos.\n");
		} else {
			System.out.println("Se ha encontrado el siguiente Cliente: " + aux);
		}

		return aux;
	}
	
	
	/*
	 * Pide que se le especifique el tipo de habitacion, Lista la q esten libres
	 */	
	public void listadoHabitaciones(Scanner scan)
	{
		System.out.println("Ingrese el tipo de Habitacion que desea: \n");
		Menu.listadoTipoHab();
		String opcion = scan.nextLine();

		for (Map.Entry<String, Habitacion> entry : habitaciones.entrySet()) {
			System.out.println("Listado de Habitaciones Libres del tipo " + opcion + "\n");
			Habitacion value = entry.getValue();
			String key = entry.getKey();
			/* Compruebo q la habitacion este libre y que sea del tipo seleccionado*/
			
			if (value.getEstado().equals(EstadoHab.LIBRE) && value.getTipo().getID().equals(opcion)) {
				System.out.println(key + "\n");
			}
		}

	}

	/*
	 * mediante el anterior metodo se listan las hab libres 
	 * y se pide que se seleccione una. Se retorna
	 * en caso de que los datos sean erroneos va a devolver una hab null
	 * Deberia tratarse mejor
	 * 
	 */
	public Habitacion buscarHabitacion(Scanner scan) {
		listadoHabitaciones(scan);
		System.out.println("Ingrese el numero de habitacion a seleccionar: \n");
		String hab = scan.nextLine();
		Habitacion aux = null;
		for (Map.Entry<String, Habitacion> entry : habitaciones.entrySet()) {
			Habitacion value = entry.getValue();
			String key = entry.getKey();
			if (hab.equals(key)) {
				aux = value;
			}
		}

		if (aux == null) {
			System.out.println("Los datos ingresados son incorrectos \n");
		}
		return aux;

	}
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

    /*30/05/2018 metodos para agregar elementos a los mapas
    * PREGUNTA: Deberiamos usar tipo generico?*/
    public void agregarConserje(Conserje c) {
        conserjes.put(c.getId(), c);
    }

    public void agregarHabitacion(Habitacion h) {
        habitaciones.put(h.getNumero(), h);
    }

    public void agregarCliente(Cliente c) {
        clientes.put(c.getDni(), c);
    }

    public void agregarReserva(Reserva r) {
        reservas.put(r.getNroReserva(), r);
    }
}
