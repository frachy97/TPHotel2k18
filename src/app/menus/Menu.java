package app.menus;

import java.util.Scanner;


import app.enums.Producto;
import app.enums.TipoHab;

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

    public static void conserjeMain() {
        System.out.println("1. Gestion de reservas");
        System.out.println("2. Gestion de clientes");
        System.out.println("3. Gestion de habitaciones");
        System.out.println("4. Modificar informacion propia");
        System.out.println();
        System.out.println("0. Cerrar sesion");
        seleccionOpcion();
    }

    public static void conserjeSubMenuReservas() {
        System.out.println("1. Generar reserva");
        System.out.println("2. Check-in");
        System.out.println("3. Check-out");
        System.out.println("4. Ver reservas");
        System.out.println("5. Cancelar reserva");
        System.out.println("6. Agregar consumo en una habitacion");
        System.out.println();
        System.out.println("0. Volver");
        seleccionOpcion();
    }

    public static void conserjeSubMenuClientes() {
        System.out.println("1. Ver lista de clientes");
        System.out.println("2. Modificar datos de un cliente");
        System.out.println();
        System.out.println("0. Volver");
        seleccionOpcion();
    }

    public static void conserjeSubMenuHabitaciones() {
        System.out.println("1. Ver habitaciones libres");
        System.out.println("2. Ver habitaciones ocupadas");
        System.out.println();
        System.out.println("0. Volver");
        seleccionOpcion();
    }

    public static void menuModificarCliente() {
        System.out.println("MODIFICAR CLIENTE\n");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. DNI");
        System.out.println("4. Nacionalidad");
        System.out.println("5. Direccion");
    }

    public static void listadoProductos() {

        System.out.println("\nLISTADO DE PRODUCTOS\n");
        for (Producto prod : Producto.values()) {
            System.out.println(prod.getID() + "- " + prod + " precio: " + prod.getPrecio());
        }
    }

    public static void listadoTipoHab() {

        System.out.println("\nLISTADO TIPO DE HABITACION\n");
        for (TipoHab tipo : TipoHab.values()) {
            System.out.println(tipo.getID() + "- " + tipo);
        }
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
