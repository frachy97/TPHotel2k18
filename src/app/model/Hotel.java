package app.model;

import app.enums.TipoHab;
import app.utils.IOGenericoUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Hotel {

    private TreeMap<String, Habitacion> habitaciones = new TreeMap<>();
    private TreeMap<String, Conserje> conserjes = new TreeMap<>();
    private TreeMap<String, Cliente> clientes = new TreeMap<>();
    private TreeMap<String, Reserva> reservas = new TreeMap<>();
    private Admin admin;
    private double totalIngresos = 0;

    public static void main(String[] args) {
/*
        Hotel h = new Hotel();
        Cliente cliente = new Cliente("Perdo", "Moraes", "34222345", Nacionalidad.BRASIL, "234 Piedrabuena, Rio");

        Ocupacion ocupacion = new Ocupacion(cliente, habitacion,
                                LocalDateTime.of(2018, Month.MAY, 13, FechaHoraUtil.horaCheckOut, FechaHoraUtil.minutoCero));
        System.out.println(ocupacion);
        System.out.println(habitacion);
        System.out.println(cliente);

        System.out.println(LocalDateTime.now().getYear());

        String str = "31/09/2017";
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(str, dt);

        System.out.println(date);*/

        final File DIR = new File("dir/");
        DIR.mkdir();

        final File CONSERJES = new File(DIR, "conserjes.dat");
        final File HABITACIONES = new File(DIR, "reservas.dat");
        final File ADMIN = new File(DIR, "admin.dat");

        Conserje c1 = new Conserje("ads", new Password("errrrr"), "name");
        Habitacion h1 = new Habitacion("101", TipoHab.INDIVIDUAL, 199.99);
        Admin a1= new Admin("dede", new Password("deeke9"), "dedede");

        IOGenericoUtil.writeObject(c1, CONSERJES);
        IOGenericoUtil.writeObject(h1, HABITACIONES);
        IOGenericoUtil.writeObject(a1, ADMIN);

        Conserje c2 = IOGenericoUtil.readObject(CONSERJES);
        Habitacion h2 = IOGenericoUtil.readObject(HABITACIONES);
        Admin a2 = IOGenericoUtil.readObject(ADMIN);
        System.out.println(c2);
        System.out.println(h2);
        System.out.println(a2);
    }
}
