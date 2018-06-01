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

    public Cliente altaCliente(Scanner scan) {
        System.out.println("CREACION DE CLIENTE\n");
        System.out.println("Ingrese nombre:");
        String nom = scan.nextLine();
        System.out.println("Ingrese apellido:");
        String ape = scan.nextLine();
        System.out.println("Ingrese dni:");
        String dni = scan.nextLine();
        System.out.println("Ingrese nacionalidad:");
        String nac = scan.nextLine();
        System.out.println("Ingrese direccion:");
        String dir = scan.nextLine();

        System.out.println("1-Confirmar ");
        System.out.println("2-Volver a ingresar los datos ");
        String confirmar = scan.nextLine();
        if (confirmar.equals("1")) {
            Cliente aux = new Cliente(nom, ape, dni, nac, dir);
            System.out.println("Se ha creado el siguiente Cliente " + aux);
            return aux;
        } else {
            Cliente aux = altaCliente(scan);//recursividad, se vuelve a crear el cliente
            return aux;
        }
    }

    /*
     * REALMENTE NO SIRVE PORQUE SE VA A MODIFICAR SEGUN SE HAGA LA RESERVA
     * VALE LA PENA DEJARLO?
     */
    public void modificarEstadoHab(Scanner scan, Habitacion hab) {
        Menu.listadoEstadoHab();
        System.out.println("Ingrese opcion a elegir:\n");
        String opcion = scan.nextLine();
        hab.setEstado(EstadoHab.buscarPorID(opcion));
        System.out.println("Se ha cambiado el eltado de la habitacion a " + EstadoHab.buscarPorID(opcion));
    }


    public Reserva altaReserva(Scanner input, Hotel hotel) {

        boolean valid = false;
        LocalDate ingreso = null;
        LocalDate salida = null;

        Cliente cAux=null;
        boolean repetir=true;
        
        System.out.println("CREACION DE LA RESERVA\n");
        while(repetir==true)
        {
        	System.out.println("1-Crear Cliente");
        	System.out.println("2-Buscar Cliente");
        	String opcion=input.nextLine();
        
        	switch(opcion)
        	{
        	case"1":
        		cAux = altaCliente(input);
        		repetir=false;
        		break;
        	case"2":
        		cAux = hotel.buscarCliente(input);
        		if(cAux==null)
        		{
        			cAux = altaCliente(input);
        		}
        		repetir=false;
        		break;
        	default:
        		System.out.println("Opcion incorrecta");
        	}
        }


        Habitacion hAux = hotel.buscarHabitacion(input);

        while (!valid) {
            try {
                System.out.println("Ingrese fecha ingreso (dd/MM/yyyy): ");
                ingreso = LocalDate.parse(input.nextLine(), FechaHoraUtil.formatoFecha);
                System.out.println(ingreso);
                if (FechaHoraUtil.yaEsPasada(ingreso)) {
                    throw new Exception("La fecha ya es pasada");
                }
                System.out.println("Ingrese fecha salida (dd/MM/yyyy): ");
                salida = LocalDate.parse(input.nextLine(), FechaHoraUtil.formatoFecha);
                System.out.println(salida);
                if (FechaHoraUtil.yaEsPasada(salida) || salida.isEqual(ingreso)) {
                    throw new Exception("La fecha ya es pasada o es la misma que la de ingreso.");
                }

                for (Reserva reserva : hotel.getReservas().values()) {
                    if (reserva.getHabitacion().getNumero().equals(hAux.getNumero())) {
                        if (FechaHoraUtil.hayConflictosConFechaDeReserva(ingreso, salida,
                                reserva.getFechaIngreso(), reserva.getFechaSalida())) {
                            throw new Exception("Existen conflictos con una reserva ya existente: " + reserva);
                        }
                    }

                }
                System.out.println("Las fechas son validas");
                valid = true;

            } catch (DateTimeParseException e) {
                System.out.println("Fecha no valida");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }



        Reserva reserva = new Reserva(cAux, hAux, ingreso, salida);
        System.out.println(ingreso);
        System.out.println(salida);
        System.out.println("Usted esta a punto de crear la siguiente reserva: " + reserva + "\n");
        Menu.confirmarConTeclaS();
        String aux = input.nextLine();

        if (aux.equals("s")) {
            reserva.confirmarOcupacion();
        }

        return reserva;

    }

    /*
     * Muestra el listado de Productos y da a seleccionar cual se agrega a la cuenta.
     */
    public void agregarConsumo(Scanner scan, Reserva aux) {
        Menu.listadoProductos();
        System.out.println("Selecciona el numero de producto:\n");
        String numero = scan.nextLine();
        aux.getConsumos().add(Producto.buscarPorID(numero));
        System.out.println("Se ha agregado " + Producto.buscarPorID(numero) + " a la reserva.");


    }

    /*
     * Opcion para modificar datos de un cliente
     *
     * Deberia ser capaz de buscar al cliente en vez de pasarselo x parametro
     */
    public void modificarCliente(Scanner scan, Cliente aux) {
        String opcion = Menu.menuModificarCliente(scan);
        switch (opcion) {
            case "1":
                System.out.println("Ingrese el nuevo nombre:\n");
                String nom = scan.nextLine();
                aux.setNombre(nom);
                break;
            case "2":
                System.out.println("Ingrese el nuevo apellido::\n");
                String ape = scan.nextLine();
                aux.setApellido(ape);
                break;
            case "3":
                System.out.println("Ingrese el nuevo dni:\n");
                String dni = scan.nextLine();
                aux.setDni(dni);
                break;
            case "4":
                System.out.println("Ingrese la nuevo nacionalidad:\n");
                String nac = scan.nextLine();
                aux.setNacionalidad(nac);
                break;
            case "5":
                System.out.println("Ingrese la nuevo direccion:\n");
                String dir = scan.nextLine();
                aux.setNacionalidad(dir);
                break;
        }


    }

    public void habilitar() {
        habilitado = true;
    }

    public void deshabilitar() {
        habilitado = false;
    }
}
