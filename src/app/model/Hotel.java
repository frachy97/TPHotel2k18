package app.model;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import app.archivos.Archivos;
import app.enums.TipoHab;
import app.menus.Menu;
import app.utils.IOGenericoUtil;

public class Hotel {

	private static Hotel laInstancia = new Hotel();

	private TreeMap<String, Habitacion> habitaciones;
	private TreeMap<String, Conserje> conserjes;
	private TreeMap<String, Cliente> clientes;
	private TreeMap<String, Reserva> reservas;
	private Admin admin;
	private Double totalIngresos;

	private Hotel() {

		if ((admin = IOGenericoUtil.leerObjeto(Archivos.ADMIN)) == null) {
			admin = Admin.proveerDefaultAdmin();
		}
		if ((conserjes = IOGenericoUtil.leerObjeto(Archivos.CONSERJES)) == null) {
			conserjes = new TreeMap<>();
		}
		if ((clientes = IOGenericoUtil.leerObjeto(Archivos.CLIENTES)) == null) {
			clientes = new TreeMap<>();
		}

		if ((reservas = IOGenericoUtil.leerObjeto(Archivos.RESERVAS)) == null) {
			reservas = new TreeMap<>();
		}
		if ((habitaciones = IOGenericoUtil.leerObjeto(Archivos.HABITACIONES)) == null) {
			habitaciones = new TreeMap<>();
		}
		if ((totalIngresos = IOGenericoUtil.leerObjeto(Archivos.INGRESOS)) == null) {
			totalIngresos = (double) 0;
		}
	}

	public static Hotel getInstancia() {
		return laInstancia;
	}

	/*
	 * Este metodo busca un cliente x dni si no lo encuentra retorna null
	 */
	public Cliente buscarCliente(Scanner scan) {
		System.out.println("Ingrese dni del Cliente a buscar: ");
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
			System.out.println("No se encontro ningun cliente con el dni "
					+ dni + " en la base de datos.");
			System.out.println("Volver a intentar? s/n");
			String opcion = scan.nextLine();
			if (opcion.equals("s")) {
				aux = buscarCliente(scan);
			}
		} else {
			System.out.println("Se ha encontrado el siguiente Cliente: " + aux);
		}

		return aux;
	}

	/*
	 * Pide que se le especifique el tipo de habitacion, Lista la q esten libres
	 */
	public void listadoHabitacionesPorTipo(Scanner scan) {
		System.out.println("Ingrese el tipo de Habitacion que desea: ");
		Menu.listadoTipoHab();
		String opcion = scan.nextLine();

		boolean haydato = false;
		/* Verifico que los datos ingresados sean los correctos */
		if (TipoHab.buscarPorID(opcion) != null) {
			for (Map.Entry<String, Habitacion> entry : habitaciones.entrySet()) {
				Habitacion value = entry.getValue();
				String key = entry.getKey();
				/*
				 * Compruebo q la habitacion este libre y que sea del tipo
				 * seleccionado
				 */

				if (value.getTipo().getID().equals(opcion)) {
					/*
					 * Una vez que entra significa que hay al menos una
					 * habitacion
					 */
					if (haydato == false) {
						System.out.println("Listado de Habitaciones libres:");
						haydato = true;
					}
					System.out.println(key + " " + value.toStringEstadoHab());

				}
			}

			if (haydato == false) {
				System.out
						.println("No se ha encontrado ninguna habitacion del tipo "
								+ TipoHab.buscarPorID(opcion));
				listadoHabitacionesPorTipo(scan);
			}
		} else {
			System.out.println("Los datos ingresados son incorrectos");
			listadoHabitacionesPorTipo(scan);
		}
	}

	/* 03/06/2018 Agregados los siguientes tres metodos */
	public void listarTodasLashabitaciones() {
		for (Habitacion h : habitaciones.values()) {
			System.out.println(h);
		}
	}

	public void listarHabitacionesLibres() {
		boolean existen = false;
		for (Habitacion h : habitaciones.values()) {
			if (h.elEstado()) {
				System.out.println(h);
				existen = true;
			}
		}
		if (!existen) {
			System.out.println("No hay habitaciones libres\n");
		}
	}

	public void listarHabitacionesOcupadas() {
		boolean existen = false;
		for (Habitacion h : habitaciones.values()) {
			if (!h.elEstado()) {
				System.out.println(h);
				existen = true;
			}
		}
		if (!existen) {
			System.out.println("No hay habitaciones ocupadas\n");
		}
	}

    /**
     * Verifica si en el mapa existe la habitacion.
     * @param idHabitacion
     * @return true/false
     */
    public boolean existeHabitacion(String idHabitacion) {
        for (Map.Entry<String, Habitacion> entry : habitaciones.entrySet()) {
            if (idHabitacion.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

	/*
	 * mediante el anterior metodo se listan las hab libres y se pide que se
	 * seleccione una. Se retorna en caso de que los datos sean erroneos va a
	 * devolver una hab null Deberia tratarse mejor
	 */
	public Habitacion buscarHabitacion(Scanner scan) {
		listadoHabitacionesPorTipo(scan);
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
		verReservasHabitacion(hab);

		if (aux == null) {
			System.out.println("Los datos ingresados son incorrectos \n");
			aux = buscarHabitacion(scan);
		}
		return aux;

	}

	public void verReservasHabitacion(String numeroHab) {
		boolean valido = false;
		for (Reserva r : reservas.values()) {
			if (r.getHabitacion().getNumero().equals(numeroHab)) {
				while (valido == false) {
					System.out
							.println("Existen reservas pertenecientes a dicha habitacion:");
					valido = true;
				}
				System.out.println(r);
			}
		}
	}








	/*
	 *  05/06/2018 se crearon los siguientes 3 metodos
	 */
	public void cancelarReserva(Scanner scan) {
		boolean validar = false;
		boolean validar2= false;
		while (!validar)
		{
			System.out.println("Ingrese el numero de reserva que desea cancelar:");
			String numero = scan.nextLine();

			for (Map.Entry<String, Reserva> entry : reservas.entrySet()) {
				if (numero.equals(entry.getKey())) {
					
					validar2=true;
					System.out.println("Se ha eliminado la siguiente reserva: "+ entry.getValue());
					reservas.remove(entry.getValue());
				}
			}
			
			if(!validar2)
			{
				System.out.println("No se ha encontrado ninguna reserva con ese numero");
				System.out.println("Desea volver a intentarlo? s/n");
				String opcion=scan.nextLine();
				
				if(!opcion.equals("s"))
				{
					validar=true;
				}
			}
		}

	}
	
	public void listarTodasLosClientes() {
		for (Cliente c : clientes.values()) {
			System.out.println(c);
		}
	}
	
	public void listarTodasLasReservas() {
		for (Reserva r : reservas.values()) {
			System.out.println(r);
		}
	}

	/* getters and setters */
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

	/*
	 * 30/05/2018 metodos para agregar elementos a los mapas PREGUNTA:
	 * Deberiamos usar tipo generico?
	 */
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
	
	public void removerReserva(String key) {
        reservas.remove(key);
    }

    public void removerCliente(String key) {
        reservas.remove(key);
    }

    public void removerHabitacion(String key) {
        reservas.remove(key);
    }

    public void removerConserje(String key) {
        reservas.remove(key);
    }
}
