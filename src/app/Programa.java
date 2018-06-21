/*package app;

import app.controlador.Controlador;
import app.model.Admin;
import app.model.Hotel;
import app.model.Password;
import app.utils.IOGenericoUtil;

import java.io.File;

public class Programa {

    public static void main(String[] args) {

        Hotel hotel = Hotel.getInstancia();
        Controlador controlador = new Controlador(hotel);
        controlador.inicio();
    }
}
*/package app;

import Util.JsonUtil;
import model.Helado;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Ejecutable {

    public static void main(String[] args) {

        System.out.println("============= ENCODE =========== ");
        Helado h1 = new Helado("CHOCOLATE",70);
        Helado h2 = new Helado("CHOCOLATE2",71);
        Helado h3 = new Helado("CHOCOLATE3",72);
        Helado h4 = new Helado("CHOCOLATE4",73);

        ArrayList<Helado> listaHelado = new ArrayList<Helado>();
        listaHelado.add(h1);
        listaHelado.add(h2);
        listaHelado.add(h3);
        listaHelado.add(h4);

        System.out.println("Muestra los objetos JSON en el arreglo: ");

        JSONArray listaJSON = new JSONArray(listaHelado);
        System.out.println(listaJSON);

        System.out.println("================= DECODE ===============");
        String contenidoJSON = JsonUtil.leer();

        JSONArray losWeyes = null;

        try{
            losWeyes = new JSONArray(contenidoJSON);
        }catch(JSONException e){
        }

        ArrayList<Helado> listaHelado2 = new ArrayList<>();

        for(int i = 0; i < losWeyes.length(); i++ ){
            try{
                JSONObject obj = losWeyes.getJSONObject(i);
                listaHelado2.add(new Helado(obj.getString("nombre"), obj.getDouble("precio")));

            }catch(JSONException e){

            }

        }
        System.out.println("for each a continuacion");
        for(Helado h: listaHelado2){
            System.out.println(h);
        }
		/*public String pasarAJSONArray (ArrayList<Helado> listaHelado){
        JSONArray listaJSON = new JSONArray(listaHelado);
        System.out.println(listaJSON);

        return listaJSON.toString();
		}*/
    }

}
