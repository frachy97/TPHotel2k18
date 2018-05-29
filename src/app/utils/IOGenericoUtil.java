package app.utils;

import java.io.*;

public class IOGenericoUtil {

    private IOGenericoUtil() {
    }

    public static <T extends Serializable> void writeObject(T t, File file) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

            out.writeObject(t);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T readObject(File file) {

        T t = null;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

            t = (T) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return t;
    }
}
