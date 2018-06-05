package app;

import app.archivos.Archivos;
import app.controlador.Controlador;
import app.enums.TipoHab;
import app.model.*;

import java.io.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Archivos.DIR.mkdir();
        Hotel hotel = Hotel.getInstancia();
        //Controlador controlador = new Controlador(hotel);
        //controlador.inicio();

        Habitacion hab = new Habitacion("101", TipoHab.INDIVIDUAL, 100);
        hotel.agregarHabitacion(hab);

        Admin admin = new Admin("asdas", new Password("alsdk"), "lasdk");

        admin.modificarPrecioHabitacion(new Scanner(System.in), hotel);
        System.out.println(hab);
    }
}
