package org.labsis.grupodesarrollo.iolaboratorio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.labsis.grupodesarrollo.iolaboratorio.R;
import org.labsis.grupodesarrollo.iolaboratorio.entidades.Registro;

import java.util.LinkedList;

/**
 * Created by ignacio on 31/07/15.
 */
public class ConnectedListAdapter extends BaseAdapter {
    private LinkedList<Registro> data;
    private Context mContext;


    public ConnectedListAdapter(Context context, LinkedList<Registro> reg){
        super();
        mContext=context;
        data=reg;

    }
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
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View rowView = view;
        connectedListHolder holder= null;
        if(rowView ==null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.item_connected_list,viewGroup,false);
            holder = new connectedListHolder();
            holder.txV_nombre = (TextView) rowView.findViewById(R.id.txtNombre);
            holder.txV_fecha_hora_ingreso = (TextView) rowView.findViewById(R.id.txV_fecha_ingreso);
            holder.txV_fecha_hora_egreso= (TextView) rowView.findViewById(R.id.txV_fecha_egreso);

        }else{
            holder = (connectedListHolder) rowView.getTag();
        }

        Registro reg = (Registro)getItem(pos);
        holder.txV_nombre.setText(reg.getUsuario().getNombre());
        holder.txV_fecha_hora_ingreso.setText(reg.getFecha_ingreso().toString());
        holder.txV_fecha_hora_egreso.setText(reg.getFecha_egreso().toString());

        return rowView;
    }


    public static class connectedListHolder{

        public TextView txV_nombre;
        public TextView txV_fecha_hora_ingreso;
        public TextView txV_fecha_hora_egreso;





    }
}
