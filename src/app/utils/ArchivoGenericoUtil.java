import java.io.*;
import java.util.Map;

public class ArchivoGenericoUtil {

    private ArchivoGenericoUtil() {
    }

    /*Metodos estaticos para escribir y leer en archivos. T extiende de Serializable y Map, haciendo que
    * cualquier objeto que no los implemente, no pueda ser pasado por parametro.*/
    public static <T extends Serializable & Map> void escribir(T t, File file) {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

            out.writeObject(t);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 16/05/2018 Verificar que ocurre si el metodo retorna null
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T leer(File file/*, Class laquevenga*/) {

        T t = null;
        /*Object obj;*/

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

             /*obj instanceof laquevenga*/
            t = (T) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return t;
    }
}
