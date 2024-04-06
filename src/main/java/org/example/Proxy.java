package org.example;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;
import org.json.JSONObject;
public class Proxy{

    // Para correrlo en aws se pone las ips para cada instancia de Mathservice que para el ejemplo se utilizaron 54.224.206.182:4567, 54.91.255.119:4567
    private static final List<String> urls = Arrays.asList("http://localhost:4567/", "http://localhost:4567/");
    private static int cont = 0;

    public static void main(String... args){
        port(getPort());
        staticFiles.location("/public");

        get("client/linearsearch", (req,res) -> {
            String list = req.queryParams("list").toString();
            String value = req.queryParams("value").toString();
            String redireccion = RoundRobin("linearsearch");
            redireccion = redireccion + "?list=" + list + "&value=" + value;
            String respuesta = HttpConnectionExample.httResponse(redireccion);
            JSONObject jsonObject = new JSONObject(respuesta);
            return jsonObject;
        });

        get("client/binarysearch", (req,res) -> {
            String list = req.queryParams("list").toString();
            String value = req.queryParams("value").toString();
            String redireccion = RoundRobin("binarysearch");
            redireccion = redireccion + "?list=" + list + "&value=" + value;
            String respuesta = HttpConnectionExample.httResponse(redireccion);
            JSONObject jsonObject = new JSONObject(respuesta);
            return jsonObject;
        });
    }

    /**
     * Este metodo es un balanceador de cargas
     * @param path es la url del metodo de busqueda que se va a utilizar
     * @return una parte de la url que se va a utilizar para el servicio MathServices
     * @throws MalformedURLException
     */
    private static String RoundRobin(String path) throws MalformedURLException {
        // Get the URL for the current index
        String getUrl = urls.get(cont);
        // Increment the index and wrap it around if it reaches the end of the list
        cont = (cont + 1) % urls.size();
        return getUrl + path;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4569;
    }

}