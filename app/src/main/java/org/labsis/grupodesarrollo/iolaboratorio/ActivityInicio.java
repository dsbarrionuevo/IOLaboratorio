package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.labsis.grupodesarrollo.iolaboratorio.Util.DBHelper;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;

/**
 * Created by Diego on 31/07/2015.
 */
public class ActivityInicio extends Activity {

    private Button btnIngresar;
    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInicio.this,
                        ActivityIngresar.class);
                startActivity(intent);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInicio.this,
                        ActivityRegistrarse.class);
                startActivity(intent);
            }
        });

        //verifico si existe un usuario en la base de datos local
        DBHelper db = new DBHelper(this);
        Usuario usuario = db.consultarUsuario();
        if (usuario != null) {
            startActivity(new Intent(ActivityInicio.this, ActivityPrincipal.class));
        }
    }

}
