package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.labsis.grupodesarrollo.iolaboratorio.Util.Cliente;
import org.labsis.grupodesarrollo.iolaboratorio.Util.DBHelper;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;

/**
 * Created by Diego on 31/07/2015.
 */
public class ActivityIngresar extends Activity {

    private EditText txtNombre, txtClave;
    private Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtClave = (EditText) findViewById(R.id.txtClave);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enviar al servidor los datos de login
                //al recibir su respuesta, esta puede ser:
                //ok, por lo que paso a guardar en mi bd local el usuario y lo logueo
                //o "datos incorrectos", por lo que debo probar con otro nombre de usuario o clave
                String nombre = txtNombre.getText().toString();
                String clave = txtClave.getText().toString();
                //correr llamada al servidor en asyntask...
                if (nombre != "" && clave != "") login(nombre, clave);
            }
        });
    }

    protected void login(String nombre, String clave) {
        final Usuario usuario = new Usuario(nombre, clave);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int idUsuario = Cliente.getInstancia().iniciarSesion(usuario);
                if (idUsuario != -1) {
                    DBHelper db = new DBHelper(ActivityIngresar.this);
                    usuario.setId(idUsuario);
                    db.insertarUsuario(usuario);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ActivityIngresar.this, ActivityPrincipal.class));
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ActivityIngresar.this, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }

}
