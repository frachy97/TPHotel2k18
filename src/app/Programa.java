package app;

import app.archivos.Archivos;
import app.controlador.Controlador;
import app.model.Hotel;

public class Programa {

    public static void main(String[] args) {

        Archivos.DIR.mkdir();
        new Controlador(Hotel.getInstancia()).inicio();
    }
}
