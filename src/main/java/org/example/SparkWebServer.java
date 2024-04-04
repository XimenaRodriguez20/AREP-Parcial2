package org.example;


import java.util.ArrayList;
import java.util.Arrays;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");
        get("hello", (req,res) -> "Hello Docker!");

        get("linearsearch", (req,res) ->{
            String list = req.queryParams("list").toString();
            String value = req.queryParams("value").toString();
            int respuesta = busquedaLineal(list, value);
            return "Hello Docker! " + " lista " + list + " valor " + value + " respuesta final " + respuesta ;
        });

        get("binarysearch", (req,res) ->{
            String list = req.queryParams("list").toString();
            String value = req.queryParams("value").toString();
            int respuesta = busquedaBinaria(list, value);
            return "Hello !";
        });
    }


    /**
     * Este metodo nos permite hacer la busqueda lineal
     * @param list es la lista que el usuario nos brinda
     * @param value es el valor que el usuario desea buscar
     * @return el valor del indice o el que esta por defecto sino funciona
     */


    public static int busquedaLineal(String list, String value){
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(list.split(",")));
        for (int i=0 ; i < myList.size(); i++){
            if (myList.get(i).equals(value)){
                System.out.println(myList.get(i).equals(value));
                return i ;
            }
        }
        return -1;
    }

    /**
     * Este metodo nos permite hacer la busqueda binaria
     * @param list es la lista que el usuario nos brinda
     * @param value es el valor que el usuario desea buscar
     * @return el valor del indice o el que esta por defecto sino funciona
     */

    public static int busquedaBinaria(String list, String value) {
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(list.split(",")));
        int mitad = 0;
        Integer valor = Integer.parseInt(value);
        for (int i = 0; i < myList.size(); i++) {
            mitad = myList.size()/2;
            Integer numero = Integer.parseInt(myList.get(mitad));
            if (numero > valor) {
                System.out.println(myList.get(i).equals(value));
                return i;
            } else if (numero < valor) {


            }
        }
        return -1;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}



