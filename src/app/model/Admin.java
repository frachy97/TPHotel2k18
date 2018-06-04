package app.model;

import app.enums.TipoHab;

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
        int opcion = 2; //inicializo en 2 para que entre al una vez al while. Luego lo setteo a 0 en el mismo.
        boolean reingresarDatos;
        //variables para conserje
        String id = null;
        String psw = null;
        String nombre = null;
        Password password;

        while (opcion == 2) {

            System.out.println("Ingresar id: ");
            id = scanner.nextLine();

            //si el usuario ingresa la opcion "2" , setteo a "false" para que reingrese otra contrasenia
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

            //si el usuario útiliza la opcion "2" del siguiente While
            //setteo los valores para el uso del mismo.
            opcion = 0;
            reingresarDatos = true;
            while (opcion != 2 && opcion != 1 && reingresarDatos == true) {
                System.out.println("Usted ha ingresado los siguientes datos: " +
                        "\nid: " + id +
                        "\npsw: " + psw +
                        "\nnombre: " + nombre +
                        "\n1: confirmar" +
                        "\n2: volver a ingresar los datos");
                opcion = scanner.nextInt();
                scanner.nextLine();
                reingresarDatos = false;
            }
        }
        return new Conserje(id, new Password(psw), nombre);
    }


    public void habilitarODeshabilitarConserje(Conserje conserje) {
        conserje.habilitar();
    }

    public Habitacion agregarHabitacion(Scanner scanner) {
        boolean flag = false;
        int opcion = 0;
        TipoHab tipoHabitacion = null;

        System.out.println("Ingresar numero de habitacion: ");
        String numeroHabitacion = scanner.nextLine();

        /*Mientras el usuario NO ingrese una opcion valida, va a seguir en el bucle.*/
        while (!flag) {
            System.out.println("Que clase de habitacion desea agregar?: \n1: Individual \n2: Matrimonial \n3: Matrimonial + Individual ");
            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 3) {
                flag = true;
            }
        }


        switch (opcion) {
            case 1:
                tipoHabitacion = TipoHab.INDIVIDUAL;
            case 2:
                tipoHabitacion = TipoHab.MATRIMONIAL;
            case 3:
                tipoHabitacion = TipoHab.FAMILIAR;
        }


        System.out.println("Ingresar precio por día: ");
        double precioxdia = scanner.nextDouble();

        //limpio el \n que quedó del nextdouble
        scanner.nextLine();

        return (new Habitacion(numeroHabitacion, tipoHabitacion, precioxdia));
    }

    public String eliminarHabitacion(String idHabitacion) {
        //retornar el idHabitacion, y eliminar la Habitacion en Hotel?
        return idHabitacion;
    }

    public double modificarPrecioHabitacion(double precioHabitacion) {

        //o pasamos una habitacion por parametro, y la setteamos¿?

        return precioHabitacion;
    }

    /* o es mejor mostrarle un menú de opciones?
     */
}


