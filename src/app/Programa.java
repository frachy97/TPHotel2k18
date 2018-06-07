package app;

import app.archivos.Archivos;
import app.controlador.Controlador;
import app.enums.TipoHab;
import app.model.*;

import java.io.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Programa {

    public static void main(String[] args) {

        Archivos.DIR.mkdir();
        Hotel hotel = Hotel.getInstancia();
        //Controlador controlador = new Controlador(hotel);
        //controlador.inicio();

        Admin admin = Admin.proveerDefaultAdmin();
        Habitacion hab1 = new Habitacion("101", TipoHab.INDIVIDUAL, 100);
        Habitacion hab2 = new Habitacion("102", TipoHab.INDIVIDUAL, 100);
        hotel.agregarHabitacion(hab1);
        hotel.agregarHabitacion(hab2);
        System.out.println(hab2.getTipo());


       /* Reserva res1 = new Reserva(null, hab1, null, null);
        Reserva res2 = new Reserva(null, hab2, null, null);
        hotel.agregarReserva(res1);
        hotel.agregarReserva(res2);*/

        Conserje conserje1 = new Conserje("10101010", new Password("laskd"), "laskd");
        Conserje conserje2 = new Conserje("20202020", new Password("laskd"), "laskd");
        hotel.agregarConserje(conserje1);
        hotel.agregarConserje(conserje2);
        hotel.listarTodosLosConserjes();


        admin.eliminarConserje(hotel, new Scanner(System.in));
        hotel.listarTodosLosConserjes();
    }
}
