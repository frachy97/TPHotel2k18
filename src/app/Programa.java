package app;

import app.controlador.Controlador;
import app.enums.TipoHab;
import app.model.*;

import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Hotel hotel = Hotel.getInstancia();
        Controlador controlador = new Controlador(hotel);
        //controlador.inicio();
        Conserje conserjito = new Conserje("Pepe", new Password("lalala"), "papapapa");
        Cliente unCliente = new Cliente("pepe", "pepon", "10101010", "Springfield",
                "lalalaa");
        Habitacion habitacion1 = new Habitacion("101", TipoHab.INDIVIDUAL, 1);
        Habitacion habitacion2 = new Habitacion("102", TipoHab.INDIVIDUAL, 1);
        hotel.agregarHabitacion(habitacion1);
        hotel.agregarHabitacion(habitacion2);
        hotel.agregarCliente(unCliente);
        conserjito.altaReserva(new Scanner(System.in), hotel);
    }


}
