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
    public  Conserje darDeAlta(Scanner scanner) {

        boolean flag = false;
        String psw = null;
        Conserje nConserje;
        Password password;

        System.out.println("Ingresar id: ");
        String id = scanner.nextLine();

        while(!flag)
        {
            System.out.println("Ingrese contraseña alfanumerica(8-20 digitos): ");
            psw = scanner.nextLine();

            if( Password.hasLongitudCorrecta(psw) && Password.isAlfanumerico(psw) )
            {
                flag = true;
            }else
            {
                System.out.println("La contraseña ingresada no cumple todos los requisitos: ");
            }

        }


        password = new Password( psw );

        System.out.println("Ingresar nombre: ");
        String nombre = scanner.nextLine();

        nConserje = new Conserje(id,password,nombre);


        return nConserje;
    }

    public void habilitarODeshabilitarConserje(Conserje conserje){
        conserje.cambiarEstadoHabilitado();
    }

    public Habitacion agregarHabitacion(Scanner scanner){
        boolean flag = false;
        int opcion=0;
        TipoHab tipoHabitacion = null;

        System.out.println("Ingresar numero de habitacion: ");
        String numeroHabitacion = scanner.nextLine();

        /*Mientras el usuario NO ingrese una opcion valida, va a seguir en el bucle.*/
        while(!flag)
        {
            System.out.println("Que clase de habitacion desea agregar?: \n1: Individual \n2: Matrimonial \n3: Matrimonial + Individual ");
            opcion = scanner.nextInt();

            if(opcion >= 1 && opcion <= 3)
            {
                flag = true;
            }
        }



        switch (opcion)
        {
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

        return (new Habitacion(numeroHabitacion,tipoHabitacion,precioxdia));
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
