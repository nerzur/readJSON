package com.read.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Esta clase contiene la funcionalidad de obtener los valores de un JSON y mostrarlos en pantalla. Para posteriormente
 * el usuario introducir la misma cantidad de elementos y conformar un nuevo JSON con estos valores.
 * Se realiza con la finalizad de traducir ficheros JSON de gran tamaño a través de un traductor.
 *
 * @author Nerzur
 * @version 1.0
 */
public class readJson {

    /**
     * Esta función devuelve un {@link JSONObject} a partir de la lectura de un fichero JSON.
     * @param path Ruta del fichero a leer.
     * @return Devuelve un {@link JSONObject} a partir del fichero.
     * @throws IOException
     * @throws ParseException
     */
    private static JSONObject readFile(String path) throws IOException, ParseException {
        Object ob = new JSONParser().parse(new FileReader(path));

        // typecasting ob to JSONObject
        return (JSONObject) ob;
    }

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introduzca la dirección del archivo de origen");
        String path = line.readLine();
        JSONObject js = readFile(path);
        System.out.println("Introduzca la dirección del archivo de destino");
        String dest = line.readLine();
        Set keys = js.keySet();

        //Obteniendo las llaves.
        final ArrayList<String> l = new ArrayList<>();
        keys.forEach(v -> {
            l.add((String) v);
        });

        //Mostrando los valores.
        System.out.println("\n\n***********************Inicio***********************************");
        l.forEach(v -> {
            if (js.get(v).equals(""))
                System.out.println("-----");
            else
                System.out.println(js.get(v));
        });
        System.out.println("***********************FIN***********************************");

        System.out.println("\n\n------>>>>>>>>COPIE EL CONTENIDO ANTERIOR (SIN INCORPORAR LÍNEAS DE INICIO Y FIN)------->>>>>>>>");
        System.out.println("------>>>>>>>>TRADUZCA EL CONTENIDO EN SU TRADUCTOR FAVORITO Y PEGUE EN LA CONSOLA EL RESULTADO------->>>>>>>>");

        JSONObject jsonObject = new JSONObject();
        System.out.println();

        //Leyendo los valores introducidos en la consola traducidos.
        for (int i = 0; i < l.size(); i++) {
            String line1 = line.readLine();
            if (line1.equals("-----"))
                line1 = "";
            else if (line1.equals("\n"))
                line.readLine();
            jsonObject.put(l.get(i), line1);
        }

        //Almacenando en un fichero.
        System.out.println("\n\n------>>>>>>>>COMENZANDO ESCRITURA EN ARCHIVO------->>>>>>>>");
        FileWriter file = new FileWriter(dest);
        file.write(jsonObject.toJSONString());
        file.flush();
        file.close();
        System.out.println("\n\n------>>>>>>>>SE HA ALMACENADO CORRECTAMENTE EL ARCHIVO EN: " + dest + "------->>>>>>>>");

    }
}
