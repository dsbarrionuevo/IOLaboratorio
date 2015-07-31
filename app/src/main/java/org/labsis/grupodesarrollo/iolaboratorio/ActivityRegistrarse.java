package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;
import org.labsis.grupodesarrollo.iolaboratorio.util.Cliente;

/**
 * Created by Diego on 31/07/2015.
 */
public class ActivityRegistrarse extends Activity {

    private EditText txtNombre, txtClave;
    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtClave = (EditText) findViewById(R.id.txtClave);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //enviar al servidor los datos de registro
                //al recibir su respuesta, esta puede ser:
                //ok, por lo que paso a guardar en mi bd local el usuario y lo logueo
                //o "usuario ya exsite", por lo que debo probar con otro nombre de usuario
                String nombre = txtNombre.getText().toString();
                String clave = txtClave.getText().toString();
                final Usuario usuarioNuevo = new Usuario(nombre, clave);
                //correr llamada al servidor en asyntask...
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (Cliente.getInstancia().registrarUsuario(usuarioNuevo)) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ActivityRegistrarse.this, "Éxito al registrar usuario", Toast.LENGTH_SHORT).show();
                                    ;
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ActivityRegistrarse.this, "Problemas al registrar usuario", Toast.LENGTH_SHORT).show();
                                    ;
                                }
                            });
                        }
                    }
                }).start();
            }
        });

    }
}
