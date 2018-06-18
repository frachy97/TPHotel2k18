package app.model;

import app.archivos.Archivos;
import app.enums.Producto;
import app.menus.Menu;
import app.utils.FechaHoraUtil;
import app.utils.IOGenericoUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Conserje extends Usuario {

    private boolean habilitado = false;

    public Conserje(String id, Password password, String nombre) {
        super(id, password, nombre);
    }


    public Reserva altaReserva(Scanner scanner, Hotel hotel) {

        Reserva reserva = null;
        LocalDate ingreso = null;
        LocalDate salida = null;

        Cliente cAux = null;
        boolean repetir = true;
        boolean confirmarReserva = false;

        while (!confirmarReserva) {
            boolean fechaValida = false;
            System.out.println("CREACION DE LA RESERVA\n");
            while (repetir == true) {
                System.out.println("1-Crear Cliente");
                System.out.println("2-Buscar Cliente");
                String opcion = scanner.nextLine();

                switch (opcion) {
                    case "1":
                        cAux = altaCliente(scanner, hotel);
                        repetir = false;
                        break;
                    case "2":
                        cAux = buscarCliente(scanner, hotel);
                        if (cAux == null) {
                            cAux = altaCliente(scanner, hotel);
                        }
                        repetir = false;
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            }

            Habitacion hAux = hotel.seleccionarHabitacionParaReserva(scanner);

            while (!fechaValida) {
                try {
                    System.out.println("Ingrese fecha ingreso (dd/MM/yyyy): ");
                    ingreso = LocalDate.parse(scanner.nextLine(),
                            FechaHoraUtil.formatoFecha);
                    if (FechaHoraUtil.yaEsPasada(ingreso)) {
                        throw new Exception("La fecha ya es pasada");
                    }
                    System.out.println("Ingrese fecha salida (dd/MM/yyyy): ");
                    salida = LocalDate.parse(scanner.nextLine(),
                            FechaHoraUtil.formatoFecha);
                    if (FechaHoraUtil.yaEsPasada(salida)
                            || salida.isEqual(ingreso)) {
                        throw new Exception(
                                "La fecha ya es pasada o es la misma que la de ingreso.");
                    }

                    for (Reserva r : hotel.getReservas().values()) {
                        if (r.getHabitacion().getNumero()
                                .equals(hAux.getNumero())) {
                            if (FechaHoraUtil.hayConflictosConFechaDeReserva(
                                    ingreso, salida, r.getFechaIngreso(),
                                    r.getFechaSalida())) {
                                throw new Exception(
                                        "Existen conflictos con una reserva ya existente: "
                                                + r);
                            }
                        }

                    }
                    System.out.println("Las fechas son validas");
                    fechaValida = true;

                } catch (DateTimeParseException e) {
                    System.out.println("Fecha no valida");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("Usted esta a punto de crear la siguiente reserva: ");
            System.out.println("DNI: " + cAux.getDni());
            System.out.println("Habitacion " + hAux.getNumero());
            System.out.println(ingreso);
            System.out.println(salida);
            Menu.confirmarConTeclaS();
            String aux = scanner.nextLine();
            if (aux.equals("s")) {
                confirmarReserva = true;
                reserva = new Reserva(cAux, hAux, ingreso, salida);
                System.out.println("RESERVA EXITOSA");
            }
        }

        if (reserva.getFechaIngreso().isEqual(LocalDate.now())) {
            System.out.println("Desea realizar el checkIn de la reserva? s/n");
            String aux2 = scanner.nextLine();

            if (aux2.equals("s")) {
                checkIn(scanner, hotel);
            }
        }
        return reserva;
    }

    public void cancelarReserva(Scanner scanner, Hotel hotel) {
        boolean validar = false;
        boolean validar2 = false;
        while (!validar) {
            System.out.println("Ingrese el numero de reserva que desea cancelar:");
            String numero = scanner.nextLine();

            for (Map.Entry<String, Reserva> entry : hotel.getReservas().entrySet()) {
                if (numero.equals(entry.getKey())) {

                    validar2 = true;
                    System.out.println("Se ha eliminado la siguiente reserva: " + entry.getValue());
                    hotel.getReservas().remove(entry.getValue());
                    break;
                }
            }

            if (!validar2) {
                System.out.println("No se ha encontrado ninguna reserva con ese numero");
                System.out.println("Desea volver a intentarlo? s/n");
                String opcion = scanner.nextLine();

                if (!opcion.equals("s")) {
                    validar = true;
                }
            }
        }
    }

    public void checkIn(Scanner scanner, Hotel hotel) {

        List<Reserva> reservas = hotel.obtenerReservasDeLaFecha();
        String codigo;
        Reserva paraCheckIn = null;

        if (reservas.isEmpty()) {
            System.out.println("No existen reservas validas para check-in");
            return;
        }

        System.out.println("Lista de reservas para hoy:");

        for (Reserva r : reservas) {
            System.out.println(r);
        }

        System.out.println("Seleccione el codigo de la reserva: ");

        codigo = scanner.nextLine();

        for (Reserva r : reservas) {
            if (codigo.equals(r.getNroReserva())) {
                paraCheckIn = r;
            }
        }

        if (paraCheckIn == null) {
            System.out.println("El codigo es invalido.");
            return;
        }

        if (!paraCheckIn.isConfirmada()) {
            paraCheckIn.confirmarOcupacion();
            /*
             * 01/06/2018 Una vez confirmado se guarda la reserva en la lista
             * historial del Cliente
             */
            paraCheckIn.getCliente().agregarEntradaHistorial(paraCheckIn.toString());
            System.out.println("Check-in exitoso.");
        } else {
            System.out.println("La reserva ya esta confirmada.");
        }
    }

    /*05/06/2018 Retorna el precio total de la reserva, incluyendo consumos, para que sea atrapado
    por el controlador y lo sume a la variable Double ingresosdentro de la clase Hotel*/
    public double checkOut(Scanner scanner, Hotel hotel) throws Exception {

        List<Reserva> reservasConfirmadas = hotel.obtenerReservasConfirmadas();
        String codigo;
        Reserva paraCheckOut = null;

        if (reservasConfirmadas.isEmpty()) {
            throw new Exception("No existen reservas validas para check-out");
        }

        System.out.println("Lista de reservas validas:");

        for (Reserva r : reservasConfirmadas) {
            System.out.println(r);
        }

        System.out.println("Seleccione el codigo de la reserva:");

        codigo = scanner.nextLine();

        for (Reserva r : reservasConfirmadas) {
            if (codigo.equals(r.getNroReserva())) {
                paraCheckOut = r;
            }
        }

        if (paraCheckOut == null) {
            throw new Exception("Codigo invalido");
        }

        /* Calculo la cantidad de dias que duro la reserva */
        int periodo = (int) ChronoUnit.DAYS.between(paraCheckOut.getFechaIngreso(), paraCheckOut.getFechaSalida());
        double precioTotal = paraCheckOut.getHabitacion().getPrecioPorDia() * periodo;
        System.out.println("CHECK OUT");
        System.out.println("El precio de la estadia es de $" + precioTotal);

        double precioProductos = 0;
        for (Producto p : paraCheckOut.getConsumos()) {
            System.out.println(p + " - $" + p.getPrecio());
            precioProductos = precioProductos + p.getPrecio();
        }
        precioTotal = paraCheckOut.getHabitacion().getPrecioPorDia() * periodo + precioProductos;
        System.out.println("TOTAL A PAGAR: $" + precioTotal);

        hotel.removerReserva(paraCheckOut.getNroReserva());

        return precioTotal;

    }

    /*Muestra el listado de Productos y da a seleccionar cual se agrega a la cuenta.*/
    public void agregarConsumo(Scanner scanner, Reserva aux) {
        boolean validar = false;
        boolean validar2 = false;
        String numero = null;
        while (!validar) {
            while (!validar2) {
                Menu.listadoProductos();
                System.out.println("\nSelecciona el numero de producto:");
                numero = scanner.nextLine();
                /*
                 * Verifico que el numero ingresado no sea mayor a la cantidad
                 * de Productos
                 */
                if (Integer.parseInt(numero) > Producto.values().length) {
                    System.out
                            .println("El dato ingresado es incorrecto, reintente.");
                } else {
                    validar2 = true;
                }
            }
            aux.getConsumos().add(Producto.buscarPorID(numero));
            System.out.println("Se ha agregado " + Producto.buscarPorID(numero) + " a la reserva.");
            System.out.println("Desea agregar otro producto? s/n");
            String opcion = scanner.nextLine();
            if (!opcion.equals("s")) {
                validar = true;
            }
        }

    }

    /*Se crea un cliente si se confirma, sino se vuelve a crear esto es por si los datos ingresados son no queridos*/
    public Cliente altaCliente(Scanner scanner, Hotel hotel) {
        System.out.println("CREACION DE CLIENTE\n");
        System.out.println("Ingrese nombre:");
        String nom = scanner.nextLine();
        System.out.println("Ingrese apellido:");
        String ape = scanner.nextLine();
        System.out.println("Ingrese dni:");
        String dni = scanner.nextLine();
        System.out.println("Ingrese nacionalidad:");
        String nac = scanner.nextLine();
        System.out.println("Ingrese direccion:");
        String dir = scanner.nextLine();

        System.out.println("1-Confirmar ");
        System.out.println("2-Volver a ingresar los datos ");
        String confirmar = scanner.nextLine();
        if (confirmar.equals("1")) {
            Cliente aux = new Cliente(nom, ape, dni, nac, dir);
            hotel.agregarCliente(aux);
            IOGenericoUtil.escribirObjeto(hotel.getClientes(), Archivos.CLIENTES);
            System.out.println("Se ha creado el siguiente Cliente " + aux);
            return aux;
        } else {
            Cliente aux = altaCliente(scanner, hotel);// recursividad, se vuelve a crear
            // el cliente
            return aux;
        }
    }

    /*Este metodo busca un cliente x dni si no lo encuentra retorna null*/
    public Cliente buscarCliente(Scanner scanner, Hotel hotel) {
        System.out.println("Ingrese dni del Cliente a buscar: ");
        String dni = scanner.nextLine();

        Cliente aux = null;

        for (Map.Entry<String, Cliente> entry : hotel.getClientes().entrySet()) {

            Cliente value = entry.getValue();
            String key = entry.getKey();
            if (key.equals(dni)) {
                aux = value;
                break;
            }
        }

        if (aux == null) {
            System.out.println("No se encontro ningun cliente con el dni " + dni + " en la base de datos.");
            System.out.println("Volver a intentar? s/n");
            String opcion = scanner.nextLine();
            if (opcion.equals("s")) {
                aux = buscarCliente(scanner, hotel);
            }

        } else {
            System.out.println("Se ha encontrado el siguiente Cliente: " + aux);
        }

        return aux;
    }

    /*Opcion para modificar datos de un cliente. Deberia ser capaz de buscar al cliente en vez de pasarselo x parametro*/
    public void modificarCliente(Scanner scanner, Hotel hotel) {
        Cliente aux = buscarCliente(scanner, hotel);
        if (aux == null) {
            System.out.println("No hay cliente en sistema");
            return;
        }
        Menu.menuModificarCliente();

        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.println("Ingrese el nuevo nombre:\n");
                String nom = scanner.nextLine();
                aux.setNombre(nom);
                break;
            case "2":
                System.out.println("Ingrese el nuevo apellido::\n");
                String ape = scanner.nextLine();
                aux.setApellido(ape);
                break;
            case "3":
                System.out.println("Ingrese el nuevo dni:\n");
                String dni = scanner.nextLine();
                aux.setDni(dni);
                break;
            case "4":
                System.out.println("Ingrese la nuevo nacionalidad:\n");
                String nac = scanner.nextLine();
                aux.setNacionalidad(nac);
                break;
            case "5":
                System.out.println("Ingrese la nuevo direccion:\n");
                String dir = scanner.nextLine();
                aux.setNacionalidad(dir);
                break;
        }

    }

    public void cambiarEstadoHabilitado() {
        habilitado = !habilitado;
    }

    @Override
    public String toString() {
        return super.toString() + "\nHabilitado: " + habilitado + "\n";
    }

}
