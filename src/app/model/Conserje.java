package app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

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
    * Se crea un cliente mediante la ayuda de un menu
    */
    
    public Cliente AltaCliente(Scanner scan)
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
    	
    	return new Cliente(nom,ape,dni,nac,dir);
    	
    	
    }
    /*
     * Este metodo crea una reserva
     * Falta 
     * que se verifiquen los datos de las fechas sean los correctos 
     * De alguna forma verificar que este disponible la habitacion en dichas fechas
     * Menu para Seleccionar CLiente y Habitacion
     */
    public Reserva AltaReserva(Scanner scan, Cliente Caux, Habitacion Haux)
    {
    	System.out.println("CREACION DE LA RESERVA\n");
    	System.out.println("Fecha de ingreso:(dd/MM/yyyy)\n");
    	String fechaIngreso=scan.nextLine();
    	LocalDate ingreso= LocalDate.parse(fechaIngreso,FechaHoraUtil.formatoFecha);//Tambien se le pasa el formato
    	System.out.println("Fecha de salida:(dd/MM/yyyy)\n");
    	String fechaSalida=scan.nextLine();
    	LocalDate salida= LocalDate.parse(fechaSalida,FechaHoraUtil.formatoFecha);
    	
    	
    	return new Reserva(Caux,Haux,ingreso,salida);
    	
    	
    }
    /*
     * Muestra el listado de Productos y da a seleccionar cual se agrega a la cuenta.
     */
    public void AgregarConsumo(Scanner scan, Reserva aux)
    {
    	Menu.ListadoProductos();
    	System.out.println("Selecciona el numero de producto:\n");
    	String numero=scan.nextLine();
    	aux.getConsumos().add(Producto.buscarPorID(numero));
    	
    	
    }
    
    /*
     * Opcion para modificar datos de un cliente
     * 
     * Deberia ser capaz de buscar al cliente en vez de pasarselo x parametro
     */
    public void ModificarCliente(Scanner scan, Cliente aux)
    {
    	String opcion=Menu.MenuModificarCliente(scan);
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
