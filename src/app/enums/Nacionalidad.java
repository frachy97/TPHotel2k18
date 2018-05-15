package app.enums;

public enum Nacionalidad {

    ARGENTINA("Argentina"), BRASIL("Brasil");

    String pais;

    Nacionalidad(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return pais;
    }
}
