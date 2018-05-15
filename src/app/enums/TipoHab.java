package app.enums;

public enum TipoHab {

    INDIVIDUAL("Individual");

    String tipo;

    TipoHab(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
