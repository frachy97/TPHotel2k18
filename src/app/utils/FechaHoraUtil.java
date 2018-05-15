package app.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaHoraUtil {

    public static final DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter formatoFecha= DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
    public static final int horaCheckIn = 15;
    public static final int horaCheckOut = 11;
    public static final int minutoCero = 0;

    public static String formatearFechaYHora(LocalDateTime ldt) {
        return ldt.format(formatoFechaHora);
    }

    public static String formatearFecha(LocalDateTime ldt) {
        return ldt.format(formatoFecha);
    }

    public static String formatearHora(LocalDateTime ldt) {
        return ldt.format(formatoHora);
    }

}
