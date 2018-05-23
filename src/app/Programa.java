package app;

import app.controlador.Controlador;
import app.model.Admin;
import app.model.Hotel;
import app.model.Password;
import app.utils.IOGenericoUtil;

import java.io.File;

public class Programa {

    public static void main(String[] args) {

        Hotel hotel = Hotel.getInstancia();
        Controlador controlador = new Controlador(hotel);
        controlador.inicio();
    }
}
