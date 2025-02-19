package com.example.trackingforgym.data;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class DataBase {
    static String resultado;
    static Gson gson = new Gson();
    public static final String BaseURL="https://tackingforgym.000webhostapp.com";
    static String URL;
    static InputStream response;
    static URLConnection connection;
    static String responseBody;

    public static String makeRequest(String url) {
        response=null;
        connection=null;
        responseBody="funcionando";
        String charset = "UTF-8";
        try {
            connection = new URL(url).openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestProperty("Accept-Charset", charset);
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        return responseBody;
    }

    public static String addUser(String nombre, String correo, String clave){
        URL=BaseURL+"/addUser.php?nombre="+nombre+"&clave="+clave+"&correo="+correo;
        return makeRequest(URL);
    }

    public static User getUser(String correo,String clave){
        URL=BaseURL+"/getUser.php?correo="+correo+"&clave="+clave;
        resultado = makeRequest(URL);
        User[] users=gson.fromJson(resultado , User[].class);
        return users[0];
    }
}
