package app.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase utilitaria que trabaja con variables de tipo LocalDate y LocalDateTime. Permite formatear fechas y horas
 * de la forma en que se usan localmente (dd/MM/yyyy HH:mm) como asi tambien comparar diferentes fechas para detectar
 * conflictos a la hora de gestionar una posible reserva.
 */

public class FechaHoraUtil {

    private static final DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
    public static final int horaCheckIn = 15;
    public static final int horaCheckOut = 11;
    public static final int minutoCero = 0;

    private FechaHoraUtil() {
    }

    /**
     * Formatea un LocalDateTime y lo devuelve para ser imprimido por pantalla. Muestra tanto la fecha como la hora.
     * @param fecha El objeto LocalDateTime a formatear.
     * @return la representacion textual del formato.
     */
    public static String formatearFechaYHora(LocalDateTime fecha) {
        return fecha.format(formatoFechaHora);
    }

    /**
     * Formatea un LocalDate y lo devuelve para ser imprimido por pantalla. Muestra unicamente una fecha.
     * @param fecha El objeto LocalDate a formatear.
     * @return la representacion textual del formato.
     */  
    public static String formatearFecha(LocalDate fecha) {
        return fecha.format(formatoFecha);
    }

    /**
     * Formatea un LocalDateTime y lo devuelve para ser imprimido por pantalla. Muestra unicamente la hora y minutos.
     * @param fecha El objeto LocalDateTime a formatear.
     * @return la representacion textual del formato.
     */
    public static String formatearHora(LocalDateTime fecha) {
        return fecha.format(formatoHora);
    }

    /*30/05/2018 NUEVO METODO. Este verifica si las fechas introducidas en 
    * Conserje.altaReserva() tiene sobreposicion con una reserva ya generada.*/

    /**
     * Verifica si dos fechas correspondientes a una reserva en creacion pueden ser compatibles con una reserva ya
     * existente o no.
     * @param inicioNueva La fecha de ingreso de la reserva a crear.
     * @param salidaNueva La fecha de salida de la reserva a crear.
     * @param inicioExistente La fecha de ingreso de una reserva ya existente.
     * @param salidaExistente La fecha de salida de una reserva ya existente.
     * @return true si no son compatibles, false si no.
     */
    public static boolean hayConflictosConFechaDeReserva(LocalDate inicioNueva, LocalDate salidaNueva,
                                                         LocalDate inicioExistente, LocalDate salidaExistente) {

        if (inicioNueva.isBefore(inicioExistente) && salidaNueva.isBefore(inicioExistente)) {
            return false;
        } else if (inicioNueva.isAfter(salidaExistente) && salidaNueva.isAfter(salidaExistente)) {
            return false;
        }
        return true;
    }

    /**
     * Comprueba si una fecha es anterior a la actual.
     * @param fecha La fecha a comparar.
     * @return true si la es, false si no.
     */
    public static boolean yaEsPasada(LocalDate fecha) {
        return fecha.isBefore(LocalDate.now());
    }
}
