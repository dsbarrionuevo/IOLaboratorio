package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;


public class ActivityPrincipal extends Activity {

    private Button btnIngresarSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnIngresarSalir = (Button) findViewById(R.id.btnIngresarSalir);
    }
}
