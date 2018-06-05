package app.model;

import app.enums.TipoHab;

import java.util.Scanner;

public class Admin extends Usuario {


    public Admin(String id, Password password, String nombre) {
        super(id, password, nombre);
    }

    public static Admin proveerDefaultAdmin() {
        return new Admin("admin", new Password("password"), "nombre");
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

        while (!userConfirm.equals("si")) {

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
                    "\nConfirmar: si" +
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
        switch (opcion) {
            case "1":
                tipoHabitacion = TipoHab.INDIVIDUAL;
                break;
            case "2":
                tipoHabitacion = TipoHab.MATRIMONIAL;
                break;
            case "3":
                tipoHabitacion = TipoHab.FAMILIAR;
                break;
        }

        flag = false;
        double precioxdia = 50;

        while (!flag) {
            try {
                System.out.println("Ingresar precio por día: ");
                precioxdia = Double.parseDouble(scanner.nextLine());
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un valor valido");
            }
        }

        return (new Habitacion(numeroHabitacion, tipoHabitacion, precioxdia));
    }

    public String eliminarHabitacion(String idHabitacion) {
        //retornar el idHabitacion, y eliminar la Habitacion en Hotel?
        return idHabitacion;
    }

    //modif
    public void modificarPrecioHabitacion(Scanner scanner, Habitacion habitacion) {
        boolean flag = false;
        String valorIngresado = "";
        while (!flag) {

            try {
                System.out.println("Ingrese el nuevo valor de la habitacion: ");
                valorIngresado = scanner.nextLine();

                if (Double.parseDouble(valorIngresado) >= 1) {
                    flag = true;
                } else if (Double.parseDouble(valorIngresado) == 0) {
                    throw new Exception("El coste de la habitacion no puede ser 0.");
                } else {
                    throw new Exception("El sistema no acepta valores negativos.");
                }
            } catch (NumberFormatException e) {
                System.out.println("El dato ingresado no es un numero.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        habitacion.setPrecioPorDia(Double.parseDouble(valorIngresado));
    }

    //modif
    public void modificarTipoHabitacion(Scanner scanner, Habitacion habitacion) {
        //variable para condiciones
        boolean flag = false;
        String opcion = "";

        /*Mientras el usuario NO ingrese una opcion valida, va a seguir en el bucle.*/
        while (!flag) {
            try {

                System.out.println("A qué tipo de habitacion desea modificarla?: \n1: Individual \n2: Matrimonial \n3: Familiar");
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
                e.getMessage();
            }
        }
        //Cuando confirmo que no ingresó una opcion valida. asigno el valor escogido por el usuario.

        switch (opcion) {
            case "1":
                habitacion.setTipo(TipoHab.INDIVIDUAL);
                break;
            case "2":
                habitacion.setTipo(TipoHab.MATRIMONIAL);
                break;
            case "3":
                habitacion.setTipo(TipoHab.FAMILIAR);
                break;
        }

        /* o es mejor mostrarle un menú de opciones?
         */
    }

}


