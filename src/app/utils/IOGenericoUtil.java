package app.utils;

import java.io.*;

public class IOGenericoUtil {

    private IOGenericoUtil() {
    }

    public static <T extends Serializable> void escribirObjeto(T t, String file) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

            out.writeObject(t);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T leerObjeto(String file) {

        T t = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            t = (T) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.print("Advertencia: ");
            System.out.println("no existe objeto correspondiente.");
        }

        return t;
    }
}
