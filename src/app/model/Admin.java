package app.model;

import app.enums.TipoHab;
import app.menus.Menu;

import java.util.List;
import java.util.Scanner;

/**
 * Clase que administra las cuentas de conserjes, las habitaciones y los clientes dentro del
 * sistema. Se pretende que solo haya una instancia de admin, aunque se permite que existan mas.
 * @see Cliente
 * @see Conserje
 * @see Habitacion
 */
public class Admin extends Usuario {


    /**
     * Constrcutor que asigna el id de usuario, un Password y un nombre personal
     * @see Password
     * @param id 
     * @param password
     * @param nombre
     */
    public Admin(String id, Password password, String nombre) {
        super(id, password, nombre);
    }

    /*Credenciales estandar para admin. Este metodo se llama cuando el sistema no detecta usuario admin al iniciar*/
    public static Admin proveerDefaultAdmin() {
        return new Admin("admin", new Password("password"), "nombre");
    }

    /**
     * El administrador da de alta un nuevo Conserje.
     *
     * @param scanner
     * @return retorna un nuevo Conserje
     */

    /*Metodos de gestion de conserjes*/
    public Conserje altaConserje(Scanner scanner) {

        //variables para condicionar
        boolean requisitosContrasenia;
        String userConfirm = "";
        //variables para conserje
        String id = null;
        String psw = null;
        String nombre = null;

        while (!userConfirm.equals("s")) {

            System.out.println("Ingresar id: ");
            id = scanner.nextLine();

            /*Asigno false para que entre al "while" más de una vez en caso de que el usuario haya ingresado mal
            su posible contraseña*/
            requisitosContrasenia = false;
            while (!requisitosContrasenia) {
                System.out.println("Ingrese contraseña alfanumerica(8-20 digitos): ");
                psw = scanner.nextLine();

                if (Password.hasLongitudCorrecta(psw) && Password.isAlfanumerico(psw)) {
                    requisitosContrasenia = true;
                } else {
                    System.out.println("La contraseña ingresada no cumple todos los requisitos: ");
                }
            }

            System.out.println("Ingresar nombre: ");
            nombre = scanner.nextLine();

            System.out.println("Usted ha ingresado los siguientes datos: " +
                    "\nid: " + id +
                    "\npsw: " + psw +
                    "\nnombre: " + nombre +
                    "\nConfirmar: s" +
                    "\nVolver a ingresar los datos: Presionar cualquier tecla.");
            userConfirm = scanner.nextLine();
        }
        return new Conserje(id, new Password(psw), nombre);
    }

    public void cambiarEstadoConserje(Hotel hotel, Scanner scanner) {
        try {
            Conserje conserje = obtenerConserjePorClave(scanner, hotel);
            conserje.cambiarEstadoHabilitado();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarConserje(Hotel hotel, Scanner scanner) {
        try {
            hotel.removerConserje(obtenerConserjePorClave(scanner, hotel).getId());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Conserje obtenerConserjePorClave(Scanner scanner, Hotel hotel) {
        String dni = null;
        Conserje conserje = null;
        System.out.println("Lista de conserjes:\n");
        hotel.listarTodosLosConserjes();

        System.out.println("Seleccione el DNI del conserje: ");

        conserje = hotel.encontrarConserjePorClave(scanner.nextLine());

        return conserje;
    }

    /*Metodos de gestion de habitaciones*/
    public Habitacion agregarHabitacion(Scanner scanner) {
        boolean flag = false;
        String opcion = "";
        TipoHab tipoHabitacion = null;

        System.out.println("Ingresar numero de habitacion: ");
        String numeroHabitacion = scanner.nextLine();

        /*Mientras el usuario NO ingrese una opcion valida, va a seguir en el bucle.*/
        try {
            while (!flag) {
                System.out.println("Que clase de habitacion desea agregar?: \n1: Individual \n2: Matrimonial \n3: Familiar");
                opcion = scanner.nextLine();

                //Válido si el usuario ingresó una opción correcta.
                if (Integer.parseInt(opcion) >= 1 && Integer.parseInt(opcion) <= 3) {
                    //si ingresó la opción correcta, finalizo el bucle
                    flag = true;
                } else {
                    //si el usuario no ingresó una opción correcta vuelve a repetirse el proceso.
                    throw new Exception("No se ha ingresado una opcion valida.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("No se ha ingresado un numero.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //Cuando confirmo que no ingresó una opcion valida. asigno el valor escogido por el usuario.
        tipoHabitacion = TipoHab.buscarPorID(opcion);

        double precioxdia = generarNuevoPrecioHabitacion(scanner);

        return (new Habitacion(numeroHabitacion, tipoHabitacion, precioxdia));
    }

    public void eliminarHabitacion(Scanner scanner, Hotel hotel) {
        String opcion = "";
        while (!opcion.equals("s")) {
            System.out.println("Ingrese el ID de habitacion a buscar.");
            String idHabitacion = scanner.nextLine();
            if (hotel.existeHabitacion(idHabitacion)) {
                hotel.removerHabitacion(idHabitacion);
            } else {
                System.out.println("La habitacion no existe.");
            }
            System.out.println("¿Desea volver a intenterlo?s/n");
            opcion = scanner.nextLine();
        }
    }

    public void modificarPrecioHabitacion(Scanner scanner, Hotel hotel) {

        List<Habitacion> modificables = hotel.obtenerHabitacionesModificables();

        if (!modificables.isEmpty()) {
            System.out.println("Habitaciones modificables: \n");
            for (Habitacion h : modificables) {
                System.out.println(h);
            }

            System.out.println("Ingrese el numero de la habitacion a modificar: ");
            String numero = scanner.nextLine();

            for (Habitacion h : modificables) {
                if (numero.equals(h.getNumero())) {
                    h.setPrecioPorDia(generarNuevoPrecioHabitacion(scanner));
                } else {
                    System.out.println("El numero no es valido");
                }
            }
        } else {
            System.out.println("No hay habitaciones modificables en este momento.");
        }
    }

    private double generarNuevoPrecioHabitacion(Scanner scanner) {

        boolean valido = false;
        String valorIngresado = null;

        while (!valido) {
            try {
                System.out.println("Ingrese el valor de la habitacion (minimo 200): ");
                valorIngresado = scanner.nextLine();

                if (Double.parseDouble(valorIngresado) >= 200) {
                    valido = true;
                } else {
                    throw new Exception("El valor debe ser 200 o superior.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un numero.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return Double.parseDouble(valorIngresado);
    }

    public void modificarTipoHabitacion(Scanner scanner, Hotel hotel) {

        List<Habitacion> modificables = hotel.obtenerHabitacionesModificables();

        if (!modificables.isEmpty()) {
            System.out.println("Habitaciones modificables: \n");
            for (Habitacion h : modificables) {
                System.out.println(h);
            }

            System.out.println("Ingrese el numero de la habitacion a modificar: ");
            String numero = scanner.nextLine();

            for (Habitacion h : modificables) {
                if (numero.equals(h.getNumero())) {
                    h.setTipo(generarTipoHabitacion(scanner));
                } else {
                    System.out.println("El numero no es valido");
                }
            }
        } else {
            System.out.println("No hay habitaciones modificables en este momento.");
        }
    }

    private TipoHab generarTipoHabitacion(Scanner scanner) {
        //variable para condiciones
        boolean flag = false;
        String opcion = "";

        /*Mientras el usuario NO ingrese una opcion valida, va a seguir en el bucle.*/
        while (!flag) {
            try {

                System.out.println("Seleccione un tipo de habitacion: ");
                Menu.listadoTipoHab();
                opcion = scanner.nextLine();

                //Válido si el usuario ingresó una opción correcta.
                if (Integer.parseInt(opcion) >= 1 && Integer.parseInt(opcion) <= 3) {
                    //si ingresó la opción correcta, finalizo el bucle While
                    flag = true;
                } else {
                    //si el usuario no ingresó una opción correcta vuelve a repetirse el proceso.
                    throw new Exception("No se ha ingresado una opcion valida.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return TipoHab.buscarPorID(opcion);
    }

    /*Metodos de gestion de clientes*/
    public void eliminarCliente(Scanner scanner, Hotel hotel) {
        try {
            hotel.removerCliente(obtenerClientePorClave(hotel, scanner).getDni());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Cliente obtenerClientePorClave(Hotel hotel, Scanner scanner) {
        String dni = null;
        Cliente cliente = null;
        System.out.println("Lista de clientes:\n");
        hotel.listarTodosLosClientes();

        System.out.println("Seleccione el DNI del cliente a eliminar: ");

        cliente = hotel.encontrarClientePorClave(scanner.nextLine());

        return cliente;
    }
}


