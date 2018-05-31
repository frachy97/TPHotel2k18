package app;

import app.controlador.Controlador;
import app.model.Hotel;

import java.io.Console;
import java.util.Arrays;

public class Programa {

    public static void main(String[] args) {

        Hotel hotel = Hotel.getInstancia();
        Controlador controlador = new Controlador(hotel);
        controlador.inicio();
    }


}
