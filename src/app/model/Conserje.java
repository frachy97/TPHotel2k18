package app.model;

public class Conserje extends Usuario {

    private boolean habilitado = false;

    public Conserje(String id, Password password, String nombre) {
        super(id, password, nombre);
    }

    @Override
    public String toString() {
        return super.toString() + "\nHabilitado: " + habilitado + "\n";
    }
}
