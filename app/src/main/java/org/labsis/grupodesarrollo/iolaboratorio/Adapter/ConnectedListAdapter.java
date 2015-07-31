package org.labsis.grupodesarrollo.iolaboratorio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.labsis.grupodesarrollo.iolaboratorio.R;

import java.util.ArrayList;

/**
 * Created by ignacio on 31/07/15.
 */
public class ConnectedListAdapter extends BaseAdapter {
    private ArrayList data;
    private Context mContext;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int pos) {
        return data.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = view;
        connectedListHolder holder= null;
        if(rowView ==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.activity_connected_list,viewGroup,false);
            holder = new connectedListHolder();


        }

        return rowView;
                }


    public static class connectedListHolder{

        public String nombre;
        public String fecha_hora_ingreso;
        public String fecha_hora_egreso;





    }
}
