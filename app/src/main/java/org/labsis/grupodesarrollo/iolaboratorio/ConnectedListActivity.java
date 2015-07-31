package org.labsis.grupodesarrollo.iolaboratorio;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.labsis.grupodesarrollo.iolaboratorio.Adapter.ConnectedListAdapter;


public class ConnectedListActivity extends ActionBarActivity {

    private ListView lsV_connected_list;
    private ConnectedListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connected_list);
        inflateLayout();
        loadData();

    }



    public void inflateLayout(){

        lsV_connected_list = (ListView) findViewById(R.id.lsV_connected_list);
        lsV_connected_list.setAdapter(mAdapter);
    }

    public void loadData(){

    }
}
