package app.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

import app.enums.Producto;
import app.menus.Menu;
import app.utils.FechaHoraUtil;

public class Conserje extends Usuario {

    private boolean habilitado = false;

    public Conserje(String id, Password password, String nombre) {
        super(id, password, nombre);
    }

    @Override
    public String toString() {
        return super.toString() + "\nHabilitado: " + habilitado + "\n";
    }

    /*
     * Se crea un cliente si se confirma, sino se vuelve a crear esto es por si
     * los datos ingresados son no queridos
     */

    public Cliente altaCliente(Scanner scan) {
        System.out.println("CREACION DE CLIENTE\n");
        System.out.println("Ingrese nombre:");
        String nom = scan.nextLine();
        System.out.println("Ingrese apellido:");
        String ape = scan.nextLine();
        System.out.println("Ingrese dni:");
        String dni = scan.nextLine();
        System.out.println("Ingrese nacionalidad:");
        String nac = scan.nextLine();
        System.out.println("Ingrese direccion:");
        String dir = scan.nextLine();

        System.out.println("1-Confirmar ");
        System.out.println("2-Volver a ingresar los datos ");
        String confirmar = scan.nextLine();
        if (confirmar.equals("1")) {
            Cliente aux = new Cliente(nom, ape, dni, nac, dir);
            System.out.println("Se ha creado el siguiente Cliente " + aux);
            return aux;
        } else {
            Cliente aux = altaCliente(scan);// recursividad, se vuelve a crear
            // el cliente
            return aux;
        }
    }


    public Reserva altaReserva(Scanner input, Hotel hotel) {

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
                String opcion = input.nextLine();

                switch (opcion) {
                    case "1":
                        cAux = altaCliente(input);
                        repetir = false;
                        break;
                    case "2":
                        cAux = hotel.buscarCliente(input);
                        if (cAux == null) {
                            cAux = altaCliente(input);
                        }
                        repetir = false;
                        break;
                    default:
                        System.out.println("Opcion incorrecta");
                }
            }

            Habitacion hAux = hotel.buscarHabitacion(input);

            while (!fechaValida) {
                try {
                    System.out.println("Ingrese fecha ingreso (dd/MM/yyyy): ");
                    ingreso = LocalDate.parse(input.nextLine(),
                            FechaHoraUtil.formatoFecha);
                    if (FechaHoraUtil.yaEsPasada(ingreso)) {
                        throw new Exception("La fecha ya es pasada");
                    }
                    System.out.println("Ingrese fecha salida (dd/MM/yyyy): ");
                    salida = LocalDate.parse(input.nextLine(),
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

            System.out
                    .println("Usted esta a punto de crear la siguiente reserva: ");
            System.out.println("DNI: " + cAux.getDni());
            System.out.println("Habitacion" + hAux.getNumero());
            System.out.println(ingreso);
            System.out.println(salida);
            Menu.confirmarConTeclaS();
            String aux = input.nextLine();
            if (aux.equals("s")) {
                confirmarReserva = true;
                reserva = new Reserva(cAux, hAux, ingreso, salida);
                System.out.println("RESERVA EXITOSA!");
            }
        }

        if (reserva.getFechaIngreso().isEqual(LocalDate.now())) {
            System.out.println("Desea realizar el checkIn de la reserva? s/n");
            String aux2 = input.nextLine();

            if (aux2.equals("s")) {
                checkIn(reserva);
            }
        }
        return reserva;
    }
    /*
     *
     */


    public void checkIn(Reserva reserva) {
        if (reserva.isConfirmada() == false) {
            reserva.confirmarOcupacion();
            /*01/06/2018
             * Una vez confirmado se guarda la reserva en la lista historial del Cliente
             */
            reserva.getCliente().agregarEntradaHistorial(reserva.toString());
        } else {
            System.out.println("La reserva ya esta confirmada.");
        }
    }

    public void checkOut(Reserva reserva) {
        /*Calculo la cantidad de dias que duro la reserva*/
        int periodo = (int) ChronoUnit.DAYS.between(reserva.getFechaIngreso(), reserva.getFechaSalida());
        double precioTotal = reserva.getHabitacion().getPrecioPorDia() * periodo;
        System.out.println("CHECK OUT");
        System.out.println("El precio de la estadia es de $" + precioTotal);

        if (reserva.getConsumos() != null) {
            double precioProductos = 0;
            for (Producto p : reserva.getConsumos()) {
                System.out.println(p + " - $" + p.getPrecio());
                precioProductos = precioProductos + p.getPrecio();
            }
            precioTotal = reserva.getHabitacion().getPrecioPorDia() * periodo + precioProductos;
        }
        System.out.println("TOTAL A PAGAR: $" + precioTotal);


    }

    /*
     * Muestra el listado de Productos y da a seleccionar cual se agrega a la
     * cuenta.
     */
    public void agregarConsumo(Scanner scan, Reserva aux) {
        boolean validar = false;
        boolean validar2 = false;
        String numero = null;
        while (!validar) {
            while (!validar2) {
                Menu.listadoProductos();
                System.out.println("\nSelecciona el numero de producto:");
                numero = scan.nextLine();
                /*Verifico que el numero ingresado
                 * no sea mayor a la cantidad de Productos */
                if (Integer.parseInt(numero) > Producto.values().length) {
                    System.out.println("El dato ingresado es incorrecto, reintente.");
                } else {
                    validar2 = true;
                }
            }
            aux.getConsumos().add(Producto.buscarPorID(numero));
            System.out.println("Se ha agregado " + Producto.buscarPorID(numero) + " a la reserva.");
            System.out.println("Desea agregar otro producto? s/n");
            String opcion = scan.nextLine();
            if (!opcion.equals("s")) {
                validar = true;
            }
        }

    }

    /*
     * Opcion para modificar datos de un cliente
     *
     * Deberia ser capaz de buscar al cliente en vez de pasarselo x parametro
     */
    public void modificarCliente(Scanner scan, Hotel hotel) {
        Cliente aux = hotel.buscarCliente(scan);
        if (aux == null) {
            System.out.println("No hay cliente en sistema");
            return;
        }
        Menu.menuModificarCliente();

        String opcion = scan.nextLine();

        switch (opcion) {
            case "1":
                System.out.println("Ingrese el nuevo nombre:\n");
                String nom = scan.nextLine();
                aux.setNombre(nom);
                break;
            case "2":
                System.out.println("Ingrese el nuevo apellido::\n");
                String ape = scan.nextLine();
                aux.setApellido(ape);
                break;
            case "3":
                System.out.println("Ingrese el nuevo dni:\n");
                String dni = scan.nextLine();
                aux.setDni(dni);
                break;
            case "4":
                System.out.println("Ingrese la nuevo nacionalidad:\n");
                String nac = scan.nextLine();
                aux.setNacionalidad(nac);
                break;
            case "5":
                System.out.println("Ingrese la nuevo direccion:\n");
                String dir = scan.nextLine();
                aux.setNacionalidad(dir);
                break;
        }

    }

    public void cambiarEstadoHabilitado() {
        habilitado = !habilitado;
    }
}
