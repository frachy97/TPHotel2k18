package app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import app.controlador.Controlador;
import app.enums.EstadoHab;
import app.enums.Producto;
import app.menus.Menu;
import app.utils.FechaHoraUtil;

public class Conserje extends Usuario {

    private boolean habilitado = false;

    public Conserje(String id, Password password, String nombre) {
        super(id, password, nombre);
    }

    @Override
    public String toString() {
        return super.toString() + "\nHabilitado: " + habilitado + "\n";
    }
   /*
    * Se crea un cliente si se confirma, sino se vuelve a crear 
    * esto es por si los datos ingresados son no queridos
    */
    
    public Cliente altaCliente(Scanner scan)
    {
    	System.out.println("CREACION DE CLIENTE\n");	
    	System.out.println("Ingrese nombre:\n");
    	String nom=scan.nextLine();
    	System.out.println("Ingrese apellido:\n");
    	String ape=scan.nextLine();
    	System.out.println("Ingrese dni:\n");	
    	String dni=scan.nextLine();
    	System.out.println("Ingrese nacionalidad:\n");
    	String nac=scan.nextLine();
    	System.out.println("Ingrese direccion:\n");	
    	String dir=scan.nextLine();
    	
    	System.out.println("1-Confirmar /n");
    	System.out.println("2-Volver a ingresar los datos /n");
    	String confirmar=scan.nextLine();
    	if(confirmar.equals("1"))
    	{	
    		Cliente aux=new Cliente(nom,ape,dni,nac,dir);
    		System.out.println("Se ha creado el siguiente Cliente "+ aux);
    		return aux;
    	}else{
    		Cliente aux=altaCliente(scan);//recursividad, se vuelve a crear el cliente
    		return aux;
    	}
    }
    
    /*
     * REALMENTE NO SIRVE PORQUE SE VA A MODIFICAR SEGUN SE HAGA LA RESERVA
     * VALE LA PENA DEJARLO?
     */
    public void modificarEstadoHab(Scanner scan, Habitacion hab)
    {
    	Menu.listadoEstadoHab();
    	System.out.println("Ingrese opcion a elegir:\n");
    	String opcion=scan.nextLine();
    	hab.setEstado(EstadoHab.buscarPorID(opcion));
    	System.out.println("Se ha cambiado el eltado de la habitacion a "+EstadoHab.buscarPorID(opcion));
    }
    
   

    public Reserva altaReserva(Scanner scan, Hotel telo) {
		boolean valid = false;
		boolean valid2 = false;

		System.out.println("CREACION DE LA RESERVA\n");

		while(!valid) {
			try {
				System.out.println("Fecha de ingreso:(dd/MM/yyyy)\n");
				String fechaIngreso = scan.nextLine();
				/*Tambien se le pasa el formato*/
				LocalDate ingreso = LocalDate.parse(fechaIngreso, FechaHoraUtil.formatoFecha);
				valid = true;
				
			} catch (DateTimeParseException e) {
				System.out.println("Fecha no valida.");
			}
		}
		
		while(!valid2) {
			try {
				System.out.println("Fecha de salida:(dd/MM/yyyy)\n");
				String fechaSalida = scan.nextLine();
				/*Tambien se le pasa el formato*/
				LocalDate salida = LocalDate.parse(fechaSalida, FechaHoraUtil.formatoFecha);
				valid2 = true;
				
			} catch (DateTimeParseException e) {
				System.out.println("Fecha no valida.");
			}
		}
		
		
		Cliente cAux = telo.buscarCliente(scan);
		Habitacion hAux = telo.buscarHabitacion(scan);

		Reserva reserva = new Reserva(cAux, hAux, ingreso, salida);
		System.out.println("Usted esta a punto de crear la siguiente reserva: " + reserva + "\n");
		System.out.println("Desea continuar confirmala? s/n \n");
		String aux = scan.nextLine();

		if (aux.equals("s")) {
			reserva.setConfirmada(true);
		}

		return reserva;

	}
    /*
     * Muestra el listado de Productos y da a seleccionar cual se agrega a la cuenta.
     */
    public void agregarConsumo(Scanner scan, Reserva aux)
    {
    	Menu.listadoProductos();
    	System.out.println("Selecciona el numero de producto:\n");
    	String numero=scan.nextLine();
    	aux.getConsumos().add(Producto.buscarPorID(numero));
    	System.out.println("Se ha agregado "+Producto.buscarPorID(numero)+" a la reserva.");
    	
    	
    }
    
    /*
     * Opcion para modificar datos de un cliente
     * 
     * Deberia ser capaz de buscar al cliente en vez de pasarselo x parametro
     */
    public void modificarCliente(Scanner scan, Cliente aux)
    {
    	String opcion=Menu.menuModificarCliente(scan);
    	switch(opcion)
    	{
    		case "1":
    			System.out.println("Ingrese el nuevo nombre:\n");
    			String nom=scan.nextLine();
    			aux.setNombre(nom);
    			break;
    		case "2":
    			System.out.println("Ingrese el nuevo apellido::\n");
    			String ape=scan.nextLine();
    			aux.setApellido(ape);
    			break;
    		case "3":
    			System.out.println("Ingrese el nuevo dni:\n");
    			String dni=scan.nextLine();
    			aux.setDni(dni);
    			break;
    		case "4":
    			System.out.println("Ingrese la nuevo nacionalidad:\n");
    			String nac=scan.nextLine();
    			aux.setNacionalidad(nac);
    			break;
    		case "5":
    			System.out.println("Ingrese la nuevo direccion:\n");
    			String dir=scan.nextLine();
    			aux.setNacionalidad(dir);
    			break;
    	}
    	
    	
    	
    }
}
