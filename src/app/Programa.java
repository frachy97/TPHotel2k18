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
        Controlador controlador = new Controlador(hotel);
        controlador.inicio();
/*        Conserje conserjito = new Conserje("Pepe", new Password("lalala"), "papapapa");
        Cliente unCliente = new Cliente("pepe", "pepon", "10101010", "Springfield", "lalalaa");
        Cliente unCliente2 = new Cliente("pablo", "asereje", "11111111", "Springfield", "lalalaa");

        hotel.agregarCliente(unCliente);
        Reserva r=new Reserva(unCliente,habitacion1, LocalDate.now(), LocalDate.of(2018, 6, 3));
        hotel.agregarReserva(r);
        Reserva r2=new Reserva(unCliente2,habitacion1, LocalDate.of(2018,6,5), LocalDate.of(2018, 6, 6));
        hotel.agregarReserva(r2);
        conserjito.altaReserva(new Scanner(System.in), hotel);*/
        
    }


}
