package org.labsis.grupodesarrollo.iolaboratorio;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.labsis.grupodesarrollo.iolaboratorio.Adapter.ConnectedListAdapter;
import org.labsis.grupodesarrollo.iolaboratorio.Util.Cliente;
import org.labsis.grupodesarrollo.iolaboratorio.Util.OperacionesComunes;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Registro;

import java.util.ArrayList;
import java.util.LinkedList;


public class ActivityConnectedList extends ActionBarActivity {

    private ListView lsV_connected_list;
    private ConnectedListAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_list);
        mContext = this;
        inflateLayout();
        loadData();

    }



    public void inflateLayout(){

        lsV_connected_list = (ListView) findViewById(R.id.lsV_connected_list);

    }

    public void loadData(){

        if(OperacionesComunes.isConnectivityAvailable(mContext)){
            CargarAdapterAsyncTask cargarAdapter = new CargarAdapterAsyncTask();
            cargarAdapter.execute();
        }

    }


    private class CargarAdapterAsyncTask extends AsyncTask<Void,Void,LinkedList<Registro>> {


        @Override
        protected LinkedList<Registro> doInBackground(Void... voids) {
            LinkedList<Registro> reg =Cliente.getInstancia().consultarUsuariosRegistrados();

                return reg;
            }

        @Override
        protected void onPostExecute(LinkedList<Registro> reg){
            ConnectedListAdapter mAdapter = new ConnectedListAdapter(mContext,reg);
            lsV_connected_list.setAdapter(mAdapter);

        }
    }

}
