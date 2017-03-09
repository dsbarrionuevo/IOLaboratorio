package org.labsis.grupodesarrollo.iolaboratorio.Util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Registro;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by Diego on 31/07/2015.
 */
public class Cliente {

    private static final String HOST_SERVIDOR = "http://172.16.80.88/io_laboratorio_server/";

    private static Cliente yo;

    public static Cliente getInstancia() {
        if (yo == null) {
            yo = new Cliente();
        }
        return yo;
    }

    public int registrarUsuario(Usuario usuario) {
        try {
            String respuesta = request(HOST_SERVIDOR + "/registrar_usuario.php?usuario=" + usuario.getNombre() + "&clave=" + usuario.getClave());
            JSONObject respuestaJson = new JSONObject(respuesta);
            if (respuestaJson.getString("estado").equalsIgnoreCase("ok")) {
                int idUsuarioRegistrado = respuestaJson.getInt("datos");
                return idUsuarioRegistrado;
            }
        } catch (IOException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        } catch (JSONException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        }
        return -1;
    }

    public int iniciarSesion(Usuario usuario) {
        try {
            String respuesta = request(HOST_SERVIDOR + "/iniciar_sesion.php?usuario=" + usuario.getNombre() + "&clave=" + usuario.getClave());
            JSONObject respuestaJson = new JSONObject(respuesta);
            if(respuestaJson.getString("estado").equalsIgnoreCase("ok")){
                JSONObject datosJson = respuestaJson.getJSONObject("datos");
                int idUsuario = datosJson.getInt("id");
                return idUsuario;
            }
        } catch (IOException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        } catch (JSONException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        }
        return -1;
    }

    public String registarIngreso(Usuario usuario) {
        try {
            String respuesta = request(HOST_SERVIDOR + "/registrar_movimiento.php?usuario=" + usuario.getNombre() + "&clave=" + usuario.getClave());
            JSONObject respuestaJson = new JSONObject(respuesta);
            if(respuestaJson.getString("estado").equalsIgnoreCase("ok")){
                if(respuestaJson.getString("situacion").equalsIgnoreCase("entrada")){
                    return "entrada";
                }else{
                    return "salida";
                }
            }
        } catch (IOException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        } catch (JSONException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        }
        return null;
    }

    public LinkedList<Registro> consultarUsuariosRegistrados() {
        LinkedList<Registro> list = new LinkedList<Registro>();
        try {
            String respuesta = request(HOST_SERVIDOR + "/consultar_movimientos.php");
            JSONObject respuestaJson = new JSONObject(respuesta);
            Log.i(Cliente.class.getCanonicalName(), respuestaJson.getString("mensaje"));
            JSONArray arrayJson = respuestaJson.getJSONArray("mensaje");

            for (int i = 0; i < arrayJson.length(); i++) {
                JSONObject json = arrayJson.getJSONObject(i);
                Registro r = new Registro();
                Usuario u = new Usuario();
                u.setNombre(json.getString("nombre"));
                r.setUsuario(u);
                r.setFecha_ingreso(json.getString("fecha_hora_ingreso"));
                r.setFecha_egreso(json.getString("fecha_hora_egreso"));

                list.add(r);
            }
        } catch (IOException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        } catch (JSONException e) {
            Log.e(Cliente.class.getCanonicalName(), e.getMessage());
        }
        return list;
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
