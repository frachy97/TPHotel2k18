package app.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

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

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return apellido + ", " + nombre;
    }

    @Override
    public String toString() {
        return "\nDNI: " + dni + "\nNombre: " + apellido + ", " + nombre + "\nDireccion: " +
                direccion + "\nNacionalidad: " + nacionalidad.toString() + "\n";
    }
}
