package app.model;

import java.io.Serializable;

public class Password implements Serializable {

    private static final int LONGITUD_MIN = 6;
    private static final int LONGITUD_MAX = 20;

    private String clave;

    public Password(String clave) {
        this.clave = clave;
    }

    public static boolean isAlfanumerico(String string) {

        char[] charArray = string.toCharArray();
        for (char c : charArray) {
            if (!Character.isLetterOrDigit(c))
                return false;
        }
        return true;
    }

    public static boolean hasLongitudCorrecta(String string) {

        return string.length() >= LONGITUD_MIN && string.length() <= LONGITUD_MAX;
    }

    public String getClave() {
        return clave;
    }
}
