package org.example;


import java.util.ArrayList;
import java.util.Arrays;

import static spark.Spark.*;
import org.json.JSONObject;
public class MathServices {

    public static void main(String... args){
        port(getPort());

        get("linearsearch", (req,res) ->{
            String list = req.queryParams("list").toString();
            String value = req.queryParams("value").toString();
            int respuesta = busquedaLineal(list, value);
            String resultado = formato("linearsearch", list, value, respuesta);
            return  resultado ;
        });

        get("binarysearch", (req,res) ->{
            String list = req.queryParams("list").toString();
            String value = req.queryParams("value").toString();
            int respuesta = busquedaBinaria(list, value);
            String resultado = formato("binarysearch", list, value, respuesta);
            return  resultado ;
        });
    }


    /**
     * Este metodo nos permite hacer la busqueda lineal, es decir recibe una lista de datos no importa si estan ordenados o no, ademas recibe el valor a buscar en caso de que se encuentre este valor retorna el indice en el cual se encuentra este valor en la lista, por el contrario sino encuentra este valor retorna -1
     * @param list es la lista que el usuario nos brinda
     * @param value es el valor que el usuario desea buscar
     * @return el valor del indice o el que esta por defecto sino funciona
     */


    public static int busquedaLineal(String list, String value){
        ArrayList<String> myList = new ArrayList<String>(Arrays.asList(list.split(",")));
        for (int i=0 ; i < myList.size(); i++){
            if (myList.get(i).equals(value)){
                return i ;
            }
        }
        return -1;
    }

    /**
     * Este metodo nos permite hacer la busqueda binaria,  es decir recibe una lista de datos ordenados y el valor a buscar, este metodo es mas eficiente ya que el proceso de busqueda lo hace reduciendo el conjunto a la mitad, en caso de que se encuentre este valor retorna el indice en el cual se encuentra este valor en la lista, por el contrario sino encuentra este valor retorna -1
     * @param list es la lista que el usuario nos brinda
     * @param value es el valor que el usuario desea buscar
     * @return el valor del indice o el que esta por defecto sino funciona
     */

    public static int busquedaBinaria(String list, String value) {
        ArrayList<Integer> lista = convertArrayList(list);
        Integer valor = Integer.parseInt(value);

        int inicio = 0;
        int fin = lista.size();
        int mitad = lista.size()/2;
        try {
            while(inicio <= fin) {
                if (lista.get(mitad) == valor) {
                    return mitad;
                } else if (lista.get(mitad) > valor) {
                    fin = mitad;
                    mitad = fin / 2;
                } else if (lista.get(mitad) < valor) {
                    inicio = mitad;
                    mitad = mitad + 1;
                }
            }
        }catch (Exception e) {
            System.out.println("An exception occurred");
        }
        return -1;
    }

    /**
     * Metodo que convierte un String en un arrayList de enteros
     * @param list es una lista que viene como una cadena
     * @return un arrayList de enteros
     */
    public static ArrayList<Integer> convertArrayList(String list){
        String[] listaString = list.split(",");
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (String s :  listaString) {
            lista.add(Integer.parseInt(s));
        }
        return lista;
    }

    /**
     * Metodo que nos devuelve los datos de salida como lo solicitan
     * @param busqueda metodo de busqueda
     * @param list es la lista que el usuario nos brinda
     * @param value es el valor que el usuario desea buscar
     * @param respuesta es el valor que nos retorna el metodo de busqueda
     * @return un String con todo el formato que se solicita
     */
    public static String formato( String busqueda, String list, String value, int respuesta){
        String valor = String.valueOf(respuesta);;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("operation", busqueda);
        jsonObject.put("inputlist", list);
        jsonObject.put("value", value);
        jsonObject.put("output", valor);

        String jsonString = jsonObject.toString();
        return  jsonString;

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}



