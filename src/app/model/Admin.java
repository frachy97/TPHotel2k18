package app.model;

import app.enums.TipoHab;
import app.menus.Menu;

import java.util.Scanner;

public class Admin extends Usuario {


    public Admin(String id, Password password, String nombre) {
        super(id, password, nombre);
    }

    /**
     * El administrador da de alta un nuevo Conserje.
     *
     * @param scanner
     * @return retorna un nuevo Conserje
     */
    public Conserje darDeAltaConserje(Scanner scanner) {

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

    public void habilitarODeshabilitarConserje(Conserje conserje) {
        conserje.cambiarEstadoHabilitado();
    }

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
        tipoHabitacion=TipoHab.buscarPorID(opcion);

        double precioxdia = generarNuevoPrecio(scanner);

        return (new Habitacion(numeroHabitacion, tipoHabitacion, precioxdia));
    }

    public void eliminarHabitacion(Hotel hotel, Scanner scanner) {
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

    //modif
    public void modificarPrecioHabitacion(Scanner scanner, Hotel hotel) {

        boolean flag = false;
        hotel.listarHabitacionesLibres();
        String valorIngresado = "";
        Habitacion aux;
        String num = null;

        System.out.println("Ingrese el numero de habitacion a modificar: ");
        num = scanner.nextLine();
        if (!hotel.existeHabitacion(num)) {
            System.out.println("El numero no existe.");
            return;
        }

        aux = hotel.getHabitaciones().get(num);
        aux.setPrecioPorDia(generarNuevoPrecio(scanner));
    }

    /*05/06/18 nuevo metodo, se busca no repetir codigo modularizando*/
    public double generarNuevoPrecio(Scanner scanner) {

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

    //modif
    public void setearTipoHabitacion(Scanner scanner, Habitacion habitacion) {
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

       habitacion.setTipo(TipoHab.buscarPorID(opcion));
    }

    public static Admin proveerDefaultAdmin() {
        return new Admin("admin", new Password("password"), "nombre");
    }
}


