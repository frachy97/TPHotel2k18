package app.enums;

public enum EstadoHab {

    LIBRE("Libre"), OCUPADA("Ocupada"), RESERVADA("Reservada");

    String estado;

    EstadoHab(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return estado;
    }
}
