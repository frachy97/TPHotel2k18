package app;

import app.controlador.Controlador;
import app.enums.TipoHab;
import app.model.*;

import java.io.Console;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) {

        Hotel hotel = Hotel.getInstancia();
        Controlador controlador = new Controlador(hotel);
        //controlador.inicio();
        Conserje conserjito = new Conserje("Pepe", new Password("lalala"), "papapapa");
        Cliente unCliente = new Cliente("pepe", "pepon", "10101010", "Springfield", "lalalaa");
        Cliente unCliente2 = new Cliente("pablo", "asereje", "11111111", "Springfield", "lalalaa");
        Habitacion habitacion1 = new Habitacion("101", TipoHab.INDIVIDUAL, 1);
        Habitacion habitacion2 = new Habitacion("102", TipoHab.INDIVIDUAL, 1);
        hotel.agregarHabitacion(habitacion1);
        hotel.agregarHabitacion(habitacion2);
        hotel.agregarCliente(unCliente);
        Reserva r=new Reserva(unCliente,habitacion1, LocalDate.now(), LocalDate.of(2018, 6, 3));
        hotel.agregarReserva(r);
        Reserva r2=new Reserva(unCliente2,habitacion1, LocalDate.of(2018,6,5), LocalDate.of(2018, 6, 10));
        hotel.agregarReserva(r2);
        //conserjito.altaReserva(controlador.getInput(), hotel);
        conserjito.agregarConsumo(controlador.getInput(), r2);
        conserjito.checkOut(r2);
    }


}
