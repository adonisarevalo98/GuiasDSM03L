package com.example.firebase_login;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firebase_login.datos.Farmaco;


import java.util.List;
public class AdaptadorFarmaco extends ArrayAdapter<Farmaco> {
    List<Farmaco> farmacos;
    private Activity context;
    public AdaptadorFarmaco(@NonNull Activity context, @NonNull List<Farmaco> farmacos) {
        super(context, R.layout.farmaco_layout, farmacos);
        this.context = context;
        this.farmacos = farmacos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;

        if (view == null)
            rowview = layoutInflater.inflate(R.layout.farmaco_layout,null);
        else rowview = view;

        TextView tvNombre = rowview.findViewById(R.id.tvNombre);
        TextView tvPrecio = rowview.findViewById(R.id.tvPrecio);

        tvNombre.setText("Nombre : "+farmacos.get(position).getNombre());
        tvPrecio.setText("Precio : $" + farmacos.get(position).getPrecio());

        return rowview;
    }
}
