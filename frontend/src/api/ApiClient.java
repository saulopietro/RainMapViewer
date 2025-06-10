/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package api;

/**
 *
 * @author saulo
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ApiClient {

    public static String get(String urlString) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    
     private static final String BASE_URL = "http://localhost:8080/";

    public static String getAll(String endpoint) throws MalformedURLException, ProtocolException, IOException{
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }
    
    public static void delete(String endpoint) throws IOException {
    URL url = new URL("http://localhost:8080/" + endpoint);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("DELETE");
    conn.setRequestProperty("Accept", "application/json");

    int responseCode = conn.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_NO_CONTENT) {
        throw new RuntimeException("Erro ao excluir alerta. Código HTTP: " + responseCode);
    }

    conn.disconnect();
}

}

