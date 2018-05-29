package app.menus;

import java.util.Scanner;

import app.enums.EstadoHab;
import app.enums.Producto;
import app.enums.TipoHab;

public class Menu {
	
	
	public static String MenuModificarCliente(Scanner scan)
	{
		System.out.println("MODIFICAR CLIENTE\n");
		System.out.println("1-Nombre\n");
		System.out.println("2-Apellido\n");	
		System.out.println("3-Dni\n");	
		System.out.println("4-Nacionalidad\n");
		System.out.println("5-Direccion\n");
		
		String opcion=scan.nextLine();
		return opcion;
	}
	
	public static void ListadoProductos() {
		
	    System.out.println("\nLISTADO DE PRODUCTOS\n");
	    for(Producto prod : Producto.values())
	    {
	        System.out.println(prod.getID()+"- " + prod+" precio: " +prod.getPrecio());
	    }
	}
	
	public static void ListadoTipoHab() {
		
	    System.out.println("\nLISTADO TIPO DE HABITACION\n");
	    for(TipoHab tipo: TipoHab.values())
	    {
	        System.out.println(tipo.getID()+"- " + tipo );
	    }
	}
	public static void ListadoEstadoHab() {
		
	    System.out.println("\nLISTADO ESTADO DE HABITACION\n");
	    for(EstadoHab estado: EstadoHab.values())
	    {
	        System.out.println(estado.getID()+"- " + estado );
	    }
	}
	
	
		
	
	public static void main(String[] args) {
		Menu.ListadoProductos();
		Menu.ListadoTipoHab();
		Menu.ListadoEstadoHab();

	}
	
}
