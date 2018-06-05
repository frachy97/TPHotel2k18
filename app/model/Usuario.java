package app.model;

import java.io.Serializable;

public abstract class Usuario implements Serializable {

    private String id;
    private Password password;
    private String nombre;

    public Usuario(String id, Password password, String nombre) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public Password getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "\nUsuario: " + id + "\nNombre: " + nombre;
    }
}
