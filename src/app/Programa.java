package app;

import app.archivos.Archivos;
import app.controlador.Controlador;
import app.model.Hotel;
import app.model.Reserva;

public class Programa {

    public static void main(String[] args) {

        //Archivos.DIR.mkdir();
        new Controlador (Hotel.getInstancia()).inicio();
        Reserva r = new Reserva(null, null, null, null);
        Hotel h = Hotel.getInstancia();
        h.agregarReserva(r);




    }
}
