package app.model;

import app.enums.TipoHab;

import java.util.Scanner;

public class Admin extends Usuario {



    public Admin(String id, Password password, String nombre)
    {
        super(id, password, nombre);
    }

    /**
     * El administrador da de alta un nuevo Conserje.
     * @param  scanner
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

    public void habilitarODeshabilitarConserje(Conserje conserje){
		conserje.cambiarEstadoHabilitado();
    }

     public Habitacion agregarHabitacion(Scanner scanner) throws Exception {
        boolean flag = false;
        String opcion = "";
        TipoHab tipoHabitacion = null;

        System.out.println("Ingresar numero de habitacion: ");
        String numeroHabitacion = scanner.nextLine();

        /*Mientras el usuario NO ingrese una opcion valida, va a seguir en el bucle.*/
        try {
            while (!flag) {
                System.out.println("Que clase de habitacion desea agregar?: \n1: Individual \n2: Matrimonial \n3: Matrimonial + Individual ");
                opcion = scanner.nextLine();

                if (Integer.parseInt(opcion) >= 1 && Integer.parseInt(opcion) <= 3) {
                    flag = true;
                } else {
                    throw new Exception("No se ha ingresado una opcion valida.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }


        switch (opcion) {
            case 1:
                tipoHabitacion = TipoHab.INDIVIDUAL;
            case 2:
                tipoHabitacion = TipoHab.MATRIMONIAL;
            case 3:
                tipoHabitacion = TipoHab.MATRIMONIALeINDIVIDUAL;
        }


        System.out.println("Ingresar precio por día: ");
        double precioxdia = scanner.nextDouble();

        //limpio el \n que quedó del nextdouble
        scanner.nextLine();

        return (new Habitacion(numeroHabitacion, tipoHabitacion, precioxdia));
    }

    public String eliminarHabitacion(String idHabitacion){
        //retornar el idHabitacion, y eliminar la Habitacion en Hotel?
        return idHabitacion;
    }

    public  double modificarPrecioHabitacion(double precioHabitacion){

        //o pasamos una habitacion por parametro, y la setteamos¿?

        return precioHabitacion;
    }

    public void modificarTipoHabitacion(Habitacion habitacion , TipoHab esteTipo){
        habitacion.setTipoHab( esteTipo );

        /* o es mejor mostrarle un menú de opciones?
         */
    }

}
