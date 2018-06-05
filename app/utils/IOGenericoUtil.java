package app.utils;

import java.io.*;

public class IOGenericoUtil {

    private IOGenericoUtil() {
    }

    public static <T extends Serializable> void escribirObjeto(T t, File file) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

            out.writeObject(t);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T leerObjeto(File file) {

        T t = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            t = (T) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
        }

        return t;
    }

    /*public static <T extends Number> void escribirNroPrimitivo(File file) {
        DataOutputStream out = new DataOutputStream(new FileOutputStream())
    }*/
}
