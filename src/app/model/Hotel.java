package app.model;

import app.archivos.Archivos;
import app.enums.TipoHab;
import app.menus.Menu;
import app.utils.IOGenericoUtil;

import java.time.LocalDate;
import java.util.*;

public class Hotel {

    /*Diseño 'simpleton'. Asegura que solo sea posible una unica instancia de Hotel.*/
    private static Hotel laInstancia = new Hotel();

    private TreeMap<String, Habitacion> habitaciones;
    private TreeMap<String, Conserje> conserjes;
    private TreeMap<String, Cliente> clientes;
    private TreeMap<String, Reserva> reservas;
    private Admin admin;
    private Double totalIngresos;
    private Integer contadorReservas;

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
        if ((contadorReservas = IOGenericoUtil.leerObjeto(Archivos.CONTADOR_RESERVAS)) == null) {
            contadorReservas = 0;
        }
    }

    /*Metodo de instanciacion del unico objeto de tipo Hotel.*/
    public static Hotel getInstancia() {
        return laInstancia;
    }

    /*
     * mediante el anterior metodo se listan las hab libres y se pide que se
     * seleccione una. Se retorna en caso de que los datos sean erroneos va a
     * devolver una hab null Deberia tratarse mejor
     */
    /*Siguientes 3 metodos se encargan de proveer una habitacion valida (o null :^\) para reserva.*/
    public Habitacion seleccionarHabitacionParaReserva(Scanner scan) {

        listadoHabitacionesPorTipo(scan);
        System.out.println("Ingrese el numero de habitacion a seleccionar: \n");
        String numero = scan.nextLine();
        Habitacion aux = null;

        for (Habitacion h : habitaciones.values()) {
            if (numero.equals(h.getNumero())) {
                aux = h;
            }
        }

        verReservasHabitacion(numero);

        if (aux == null) {
            System.out.println("Los datos ingresados son incorrectos \n");
            aux = seleccionarHabitacionParaReserva(scan);
        }
        return aux;
    }

    public void verReservasHabitacion(String numeroHab) {
        boolean valido = false;
        for (Reserva r : reservas.values()) {
            if (r.getHabitacion().getNumero().equals(numeroHab)) {
                while (!valido) {
                    System.out.println("Existen reservas pertenecientes a dicha habitacion:");
                    valido = true;
                }
                System.out.println(r);
            }
        }
    }

    private void listadoHabitacionesPorTipo(Scanner scan) {
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
                    if (!haydato) {
                        System.out.println("Listado de Habitaciones libres:");
                        haydato = true;
                    }
                    System.out.println(key + " " + value.toStringEstadoHab());

                }
            }

            if (!haydato) {
                System.out.println("No se ha encontrado ninguna habitacion del tipo " + TipoHab.buscarPorID(opcion));
                listadoHabitacionesPorTipo(scan);
            }
        } else {
            System.out.println("Los datos ingresados son incorrectos");
            listadoHabitacionesPorTipo(scan);
        }
    }


    /*Metodos de listado de elementos en mapas*/
    public void listarTodosLosConserjes() {
        for (Conserje c : conserjes.values()) {
            System.out.println(c);
        }
    }

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

    public void listarTodosLosClientes() {
        for (Cliente c : clientes.values()) {
            System.out.println(c);
        }
    }

    public void listarTodasLasReservas() {
        for (Reserva r : reservas.values()) {
            System.out.println(r);
        }
    }


    /*metodos de agregado y quita de elementos en los mapas*/
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
        r.setNroReserva(String.valueOf(++contadorReservas));
        reservas.put(r.getNroReserva(), r);
    }

    public void removerReserva(String key) {
        reservas.remove(key);
    }

    public void removerCliente(String key) {
        clientes.remove(key);
    }

    public void removerHabitacion(String key) {
        habitaciones.remove(key);
    }

    public void removerConserje(String key) {
        conserjes.remove(key);
    }

    public void agregarIngreso(double ingreso) {
        totalIngresos += ingreso;
    }


    /*Metodos que devuelven un elemento en los mapas segun clave. Arrojan excepciones en caso de que la clave
    * no sea valida*/
    public Conserje encontrarConserjePorClave(String key) {
        if (!conserjes.containsKey(key)) {
            throw new NullPointerException("No existe conserje con ese DNI.");
        }
        return conserjes.get(key);
    }

    public Cliente encontrarClientePorClave(String key) {
        if (!conserjes.containsKey(key)) {
            throw new NullPointerException("No existe conserje con ese DNI.");
        }
        return clientes.get(key);
    }


    /*Metodos que devuelven listas de elementos dentro de los mapas que cumplan con un cierto requisito*/
    public List<Reserva> obtenerReservasDeLaFecha() {

        List<Reserva> validas = new ArrayList<>();
        for (Reserva r : reservas.values()) {
            if (r.getFechaIngreso().equals(LocalDate.now())) {
                validas.add(r);
            }
        }
        return validas;
    }

    public List<Reserva> obtenerReservasConfirmadas() {
        List<Reserva> validas = new ArrayList<>();
        for (Reserva r : reservas.values()) {
            if (r.isConfirmada()) {
                validas.add(r);
            }
        }
        return validas;
    }

    public List<Habitacion> obtenerHabitacionesModificables() {

        List<Habitacion> lista = new ArrayList<>();

        for (Habitacion h : habitaciones.values()) {
            boolean modificable = true;
            for (Reserva r : reservas.values()) {
                if (h.getNumero().equals(r.getHabitacion().getNumero())) {
                    modificable = false;
                    break;
                }
            }
            if (modificable) {
                lista.add(h);
            }
        }
        return lista;
    }

    /**
     * Verifica si en el mapa existe la habitacion.
     *
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


    /*getters y setters*/
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

    public Double getTotalIngresos() {
        return totalIngresos;
    }
}
