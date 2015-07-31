package org.labsis.grupodesarrollo.iolaboratorio.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Diego on 31/07/2015.
 */
public class Cliente {

    private static final String HOST_SERVIDOR = "http://172.16.170.18//io_laboratorio/";

    private static Cliente yo;

    public static Cliente getInstancia() {
        if (yo == null) {
            yo = new Cliente();
        }
        return yo;
    }

    public boolean registrarUsuario(Usuario usuario) {
        try {
            String respuesta = request(HOST_SERVIDOR + "/registrar_usuario.php?usuario=" + usuario.getNombre() + "&clave=" + usuario.getClave());
            JSONObject respuestaJson = new JSONObject(respuesta);
            return true;
        } catch (IOException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        } catch (JSONException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        }
        return false;
    }

    //fuente: http://stackoverflow.com/questions/29294479/android-deprecated-apache-module-httpclient-httpresponse-etc
    private String request(String urlString) throws IOException {
        StringBuffer response = new StringBuffer("");
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //connection.setRequestProperty("User-Agent", "");
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while ((line = rd.readLine()) != null) {
            response.append(line);
        }
        return response.toString();
    }

}
