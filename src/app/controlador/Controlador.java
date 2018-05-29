package app.controlador;

import app.model.Hotel;

import java.util.Scanner;

public class Controlador {

    Scanner input = new Scanner(System.in);
    private Hotel hotel;

    public Controlador(Hotel hotel) {
        this.hotel = hotel;
    }

    /*Prueba preliminar del login. Ver clase Hotel para usuario y contraseña.*/
    public void inicio() {

        boolean flag = false;
        System.out.println(hotel.getAdmin().getId());
        System.out.println(hotel.getAdmin().getPassword().getClave());



        while (flag == false) {

            System.out.print("Ingrese ID: ");
            String idLogin = input.nextLine();
            System.out.print("Ingrese clave: ");
            String idPassword = input.nextLine();

            if (idLogin.equals(hotel.getAdmin().getId())) {
                if (idPassword.equals(hotel.getAdmin().getPassword().getClave())) {
                    System.out.println("Inicio sesion exitoso.");
                    flag = true;
                } else {
                    System.out.println("La contraseña es incorrecta.");
                }
            } else {
                System.out.println("El ID de usuario no existe.");
            }

        }
    }
}
