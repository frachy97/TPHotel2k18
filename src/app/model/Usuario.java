package app.model;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Usuario implements Serializable {

    private String id;
    private Password password;
    private String nombre;

    public Usuario(String id, Password password, String nombre) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public Password getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "\nDNI: " + id + "\nNombre: " + nombre;
    }


    /*05/06/2018
     * Modifica los datos de cualquier usuario
     */

    public void modificarDatosUsuario(Scanner scanner) {

        boolean requisitosContrasenia;
        String userConfirm = "";
        String id = null;
        String psw = null;
        String nombre = null;

        while (!userConfirm.equals("s")) {

            System.out.println("Ingresar nuevo id: ");
            id = scanner.nextLine();
         

            /*Asigno false para que entre al "while" mas de una vez en caso de que el usuario haya ingresado mal
            su posible contrasenia*/
            requisitosContrasenia = false;
            while (!requisitosContrasenia) {
                System.out.println("Ingrese nueva contrasenia (alfanumerica 8-20 digitos): ");
                psw = scanner.nextLine();

                if (Password.hasLongitudCorrecta(psw) && Password.isAlfanumerico(psw)) {
                    requisitosContrasenia = true;
                } else {
                    System.out.println("La contrasenia ingresada no cumple todos los requisitos: ");
                }
            }

            System.out.println("Ingresar nuevo nombre: ");
            nombre = scanner.nextLine();

            System.out.println("Usted ha ingresado los siguientes datos: " +
                    "\nid: " + id +
                    "\npassword: " + psw +
                    "\nnombre: " + nombre +
                    "\nDesea confirmar los datos? s/n");
            userConfirm = scanner.nextLine();

            if (userConfirm.equals("s")) {
                setId(id);
                setPassword(new Password(psw));
                setNombre(nombre);
            }
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
