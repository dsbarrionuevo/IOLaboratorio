package org.labsis.grupodesarrollo.iolaboratorio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.labsis.grupodesarrollo.iolaboratorio.Util.Cliente;
import org.labsis.grupodesarrollo.iolaboratorio.Util.DBHelper;
import org.labsis.grupodesarrollo.iolaboratorio.Util.OperacionesComunes;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Usuario;


public class ActivityPrincipal extends Activity {

    private Button btnIngresarSalir;
    private Button btnConsultarListado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnIngresarSalir = (Button) findViewById(R.id.btnIngresarSalir);
        btnIngresarSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick_btnIngresarSalir(v);
            }
        });
        btnConsultarListado = (Button) findViewById(R.id.btnConsultarListadoConectados);
        btnConsultarListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick_btnConsultar(view);
            }
        });
    }

    public void onClick_btnConsultar(View view) {
        startActivity(new Intent(this, ActivityConnectedList.class));
    }

    private  void marcarEntradaSalida(boolean entrar){
        if(entrar){
            //poner estilo de boton verde y texto
        }else{

        }
    }

    public void onClick_btnIngresarSalir(View view) {
        if (OperacionesComunes.isConnectivityAvailable(this)) {
            DBHelper db = new DBHelper(this);
            final Usuario usuario = db.consultarUsuario();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String situacion = Cliente.getInstancia().registarIngreso(usuario);
                    if (situacion != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(situacion == "entrada") {
                                    Toast.makeText(ActivityPrincipal.this, "Entrada marcada con éxito", Toast.LENGTH_SHORT).show();
                                    btnIngresarSalir.setText("Salir");
                                    //btnIngresarSalir.setBackgroundResource(R.drawable.);
                                }else{
                                    Toast.makeText(ActivityPrincipal.this, "Salida marcada con éxito", Toast.LENGTH_SHORT).show();
                                    btnIngresarSalir.setText("Entrar");
                                }
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ActivityPrincipal.this, "Hubo problemas al marcar", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

}
