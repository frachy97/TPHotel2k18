package app.archivos;

/**
 * Clase contenedora de rutas donde se guardará y leerá la información relevante para la administración del hotel.
 * Sus atributos son estáticos y finales. Esta clase no es instanciable.
 */
public class Archivos {


    /*19/06/2018 Reemplazo de File por String*/
    public static final String DIR = "dir/";

    public static final String ADMIN = DIR + "admin.dat";
    public static final String CONSERJES = DIR + "conserjes.dat";
    public static final String CLIENTES = DIR + "clientes.dat";
    public static final String RESERVAS = DIR + "reservas.dat";
    public static final String HABITACIONES = DIR + "habitaciones.dat";
    public static final String INGRESOS = DIR + "ingresos.dat";
    public static final String CONTADOR_RESERVAS = DIR + "contador_reservas.dat";

    private Archivos() {
    }
}
