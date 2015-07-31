package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            }
        });

    }
}
