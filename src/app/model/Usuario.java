package app.model;

public abstract class Usuario {

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

    @Override
    public String toString() {
        return "\nUsuario: " + id + "\nNombre: " + nombre;
    }
}
