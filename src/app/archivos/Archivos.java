package app.archivos;

import java.io.File;

public class Archivos {

    /*30/05/2018 Variables estaticas creadas.*/

    public static final File DIR = new File("dir/");
    public static final File ADMIN = new File(DIR, "admin.dat");
    public static final File CONSERJES = new File(DIR, "conserjes.dat");
    public static final File CLIENTES = new File(DIR, "clientes.dat");
    public static final File RESERVAS = new File(DIR, "reservas.dat");
    public static final File HABITACIONES = new File(DIR, "habitaciones.dat");
    public static final File INGRESOS = new File(DIR, "ingresos.dat");
}
