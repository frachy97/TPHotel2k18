package app.controlador;

import app.model.Admin;
import app.model.Hotel;
import app.utils.IOGenericoUtil;

import java.io.File;
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
        Admin admin = IOGenericoUtil.leerObjeto(new File("admin.dat"));
        if (admin == null) {
            admin = hotel.getAdmin();
        }

        while (flag == false) {

            System.out.print("Ingrese ID: ");
            String idLogin = input.nextLine();
            System.out.print("Ingrese clave: ");
            String idPassword = input.nextLine();

            if (idLogin.equals(admin.getId())) {
                if (idPassword.equals(admin.getPassword().getClave())) {
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
