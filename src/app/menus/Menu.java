package app.menus;

public class Menu {

    public static void adminMain() {
        System.out.println("1. Administrar conserjes");
        System.out.println("2. Administrar habitaciones");
        System.out.println("3. Administrar clientes");
        System.out.println("4. Administrar informacion propia");
        System.out.println();
        System.out.println("0. Cerrar sesion\n");
        seleccionOpcion();
    }

    public static void subMenuInfoPropia() {
        System.out.println("1. Ver informacion propia");
        System.out.println("2. Modificar informacion");
        System.out.println();
        System.out.println("0. Volver\n");
        seleccionOpcion();
    }

    public static void adminSubMenuClientes() {
        System.out.println("1. Ver registros de clientes");
        System.out.println("2. Eliminar cliente del sistema");
        System.out.println();
        System.out.println("0. Volver\n");
        seleccionOpcion();
    }

    public static void adminSubMenuConserjes() {
        System.out.println("1. Dar alta a conserje");
        System.out.println("2. Habilitar/deshabilitar conserje");
        System.out.println("3. Eliminar conserje.");
        System.out.println("4. Ver listado conserjes");
        System.out.println();
        System.out.println("0. Volver\n");
        seleccionOpcion();
    }

    public static void adminSubMenuHabitaciones() {
        System.out.println("1. Crear habitacion");
        System.out.println("2. Modificar habitacion");
        System.out.println("3. Eliminar habitacion");
        System.out.println("4. Ver todas las habitaciones");
        System.out.println();
        System.out.println("0. Volver\n");
        seleccionOpcion();
    }

    private static void seleccionOpcion() {
        System.out.print("Seleccione ... ");
    }

    public static void confirmarConTeclaS() {
        System.out.print("Presione 's' para confirmar: ");
    }

    public static void opcionIncorrecta() {
        System.out.println("Opcion no valida\n");
    }
}
