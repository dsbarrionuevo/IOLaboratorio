package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.labsis.grupodesarrollo.iolaboratorio.Util.Cliente;
import org.labsis.grupodesarrollo.iolaboratorio.Util.OperacionesComunes;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;


public class ActivityPrincipal extends Activity {

    private Button btnIngresarSalir;
    private Button btnConsultarListado;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_principal);

        btnIngresarSalir = (Button) findViewById(R.id.btnIngresarSalir);
        btnIngresarSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_btnIngresarSalir(v);
            }
        });
        btnConsultarListado = (Button)findViewById(R.id.btnConsultarListadoConectados);
        btnConsultarListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_btnConsultar(view);
            }
        });
    }




    public void onClick_btnConsultar(View view){
        Intent i = new Intent(this,ActivityConnectedList.class);
        startActivity(i);
    }

    public void onClick_btnIngresarSalir(View view){
        if(OperacionesComunes.isConnectivityAvailable(this)) {
            RegistrarIngresoAsyncTask registrar = new RegistrarIngresoAsyncTask();
            registrar.execute();
        }
    }

    private class RegistrarIngresoAsyncTask extends AsyncTask<Usuario,Void,String>{

        @Override
        protected String doInBackground(Usuario... params) {
            Usuario u = params[0];
            return Cliente.getInstancia().registarIngreso(u,mContext);
        }

        protected void onPostExecute(String result){
            if(result != ""){
                Toast.makeText(ActivityPrincipal.this, result, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ActivityPrincipal.this, "Hubo problemas al registrar entrada/salida", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
