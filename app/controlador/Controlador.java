package app.controlador;


import app.archivos.Archivos;
import app.enums.TipoHab;
import app.menus.Menu;
import app.model.*;
import app.utils.IOGenericoUtil;


import java.io.File;
import java.util.Scanner;

public class Controlador {

    private Scanner input = new Scanner(System.in);
    private Hotel hotel;

    public Controlador(Hotel hotel) {
        this.hotel = hotel;
    }

    /*Prueba preliminar del login. Ver clase Hotel para usuario y contraseña.*/
    /*25/05/18 Cambio en almacenamiento de usuario admin estandar.*/
    public void inicio() {



        Conserje c1 = new Conserje("conserjito", new Password("conserjito"), "Miguel");
        Conserje c2 = new Conserje("conserjete", new Password("conserjete"), "Pedrin");

        Habitacion habitacion1 = new Habitacion("101", TipoHab.INDIVIDUAL, 1);
        Habitacion habitacion2 = new Habitacion("102", TipoHab.INDIVIDUAL, 1);
        hotel.agregarHabitacion(habitacion1);
        hotel.agregarHabitacion(habitacion2);

        hotel.getConserjes().put(c1.getId(), c1);
        hotel.getConserjes().put(c2.getId(), c2);

        File archivoAdmin = new File("admin.dat");
        /*Admin pruebaAdmin = new Admin("myadmin", new Password("mypassword"), "pepe");
        IOGenericoUtil.escribirObjeto(pruebaAdmin, archivoAdmin);*/

        boolean loginExitoso = false;
        boolean hayAdmin = true;
        hotel.setAdmin(IOGenericoUtil.leerObjeto(archivoAdmin));
        if (hotel.getAdmin() == null) {
            hayAdmin = false;
            hotel.setAdmin(Admin.proveerDefaultAdmin());
        }

        String opcion;

        while (!loginExitoso) {

            System.out.print("Ingrese ID: ");
            String idLogin = input.nextLine();
            System.out.print("Ingrese clave: ");
            String idPassword = input.nextLine();

            /*Autenticacion de credenciales para Admin*/
            if (idLogin.equals(hotel.getAdmin().getId()) &&
                    idPassword.equals(hotel.getAdmin().getPassword().getClave())) {

                Admin admin = hotel.getAdmin();
                System.out.println("Inicio sesion exitoso.");
                System.out.println("Ha iniciado sesion como Admin");
                if (!hayAdmin) {
                    System.out.println("Usted ha iniciado sesion con credenciales por defecto. " +
                            "Se recomienda que estas sean modificadas.");
                }
                loginExitoso = true;

                /*Menu de Admin*/
                do {
                    Menu.adminMain();
                    opcion = input.nextLine();
                    switch (opcion) {
                        /*Submenu conserjes*/
                        case "1":
                            String subOpcion1 = null;
                            do {
                                Menu.adminSubMenuConserjes();
                                subOpcion1 = input.nextLine();
                                switch (subOpcion1) {
                                    case "1":
                                        System.out.println("Opcion 1 seleccionada");
                                        break;
                                    case "2":
                                        System.out.println("Opcion 2 seleccionada");
                                        break;
                                    case "3":
                                        System.out.println("Opcion 3 seleccionada");
                                        break;
                                    case "4":
                                        System.out.println("opcion 4 seleccionada");
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion1.equals("0"));
                            break;
                        /*Submenu habitaciones*/
                        case "2":
                            String subOpcion2 = null;
                            do {
                                Menu.adminSubMenuHabitaciones();
                                subOpcion2 = input.nextLine();
                                switch (subOpcion2) {
                                    case "1":
                                        System.out.println("Opcion 1 seleccionada");
                                        break;
                                    case "2":
                                        System.out.println("Opcion 2 seleccionada");
                                        break;
                                    case "3":
                                        System.out.println("Opcion 3 seleccionada");
                                        break;
                                    case "4":
                                        System.out.println("opcion 4 seleccionada");
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion2.equals("0"));
                            break;
                        /*Submenu clientes*/
                        case "3":
                            String subOpcion3 = null;
                            do {
                                Menu.adminSubMenuClientes();
                                subOpcion3 = input.nextLine();
                                switch (subOpcion3) {
                                    case "1":
                                        System.out.println("Opcion 1 seleccionada");
                                        break;
                                    case "2":
                                        System.out.println("Opcion 2 seleccionada");
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion3.equals("0"));
                            break;
                        /*Submenu info propia*/
                        case "4":
                            String subOpcion4 = null;
                            do {
                                Menu.subMenuInfoPropia();
                                subOpcion4 = input.nextLine();
                                switch (subOpcion4) {
                                    case "1":
                                        System.out.println("Opcion 1 seleccionada");
                                        break;
                                    case "2":
                                        System.out.println("Opcion 2 seleccionada");
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion4.equals("0"));
                            break;
                        case "0":
                            System.out.println("Sesion finalizada.");
                            break;
                        default:
                            Menu.opcionIncorrecta();
                    }
                } while (!opcion.equals("0"));

            } else if (hotel.getConserjes().containsKey(idLogin) &&
                    idPassword.equals(hotel.getConserjes().get(idLogin).getPassword().getClave())) {
                Conserje conserje = hotel.getConserjes().get(idLogin);
                System.out.println("Ha iniciado sesion como Conserje. Bienvenido " + conserje.getNombre());
                loginExitoso = true;

                /*Menu de Conserje*/
                do {
                    Menu.conserjeMain();
                    opcion = input.nextLine();
                    switch (opcion) {
                        /*Submenu reservas*/
                        case "1":
                            String subOpcion1 = null;
                            do {
                                Menu.conserjeSubMenuReservas();
                                subOpcion1 = input.nextLine();
                                switch (subOpcion1) {
                                    case "1":
                                        Reserva reserva = conserje.altaReserva(input, hotel);
                                        hotel.agregarReserva(reserva);
                                        IOGenericoUtil.escribirObjeto(hotel.getReservas(), Archivos.RESERVAS);
                                        break;
                                    case "2":
                                        System.out.println("Opcion 2 seleccionada");
                                        break;
                                    case "3":
                                        System.out.println("Opcion 3 seleccionada");
                                        break;
                                    case "4":
                                        System.out.println("opcion 4 seleccionada");
                                        break;
                                    case "5":
                                        System.out.println("opcion 5");
                                        break;
                                    case "6":
                                        System.out.println("opcion 6");
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion1.equals("0"));
                            break;
                        /*Submenu clientes*/
                        case "2":
                            String subOpcion2 = null;
                            do {
                                Menu.conserjeSubMenuClientes();
                                subOpcion2 = input.nextLine();
                                switch (subOpcion2) {
                                    case "1":
                                        System.out.println("Opcion 1 seleccionada");
                                        break;
                                    case "2":
                                        conserje.modificarCliente(input, hotel);
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion2.equals("0"));
                            break;
                        /*Submenu habitaciones*/
                        case "3":
                            String subOpcion3 = null;
                            do {
                                Menu.conserjeSubMenuHabitaciones();
                                subOpcion3 = input.nextLine();
                                switch (subOpcion3) {
                                    case "1":
                                        hotel.listarHabitacionesLibres();
                                        break;
                                    case "2":
                                        hotel.listarHabitacionesOcupadas();
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion3.equals("0"));
                            break;
                        /*Submenu info propia*/
                        case "4":
                            String subOpcion4 = null;
                            do {
                                Menu.subMenuInfoPropia();
                                subOpcion4 = input.nextLine();
                                switch (subOpcion4) {
                                    case "1":
                                        System.out.println("Opcion 1 seleccionada");
                                        break;
                                    case "2":
                                        System.out.println("Opcion 2 seleccionada");
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion4.equals("0"));
                            break;
                        case "0":
                            System.out.println("Sesion finalizada.");
                            break;
                        default:
                            Menu.opcionIncorrecta();
                    }
                } while (!opcion.equals("0"));


            } else {
                System.out.println("El ID de usuario no existe o la contraseña es incorrecta.");
            }

        } // while (login == false)
    } // inicio()
}
