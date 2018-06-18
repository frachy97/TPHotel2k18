package app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {

    private String nombre;
    private String apellido;
    private String dni;
    private String nacionalidad;
    private String direccion;
    private List<String> historial = new ArrayList<>();

    public Cliente(String nombre, String apellido, String dni, String nacionalidad, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
    }

    
    public void agregarEntradaHistorial(String entrada)
    {
    	historial.add(entrada);
    }
   
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return apellido + ", " + nombre;
    }

    public List<String> getHistorial() {
        return historial;
    }

    @Override
    public String toString() {
        return "\nDNI: " + dni + "\nNombre: " + apellido + ", " + nombre + "\nDireccion: " +
                direccion + "\nNacionalidad: " + nacionalidad.toString() + "\n";
    }

}
