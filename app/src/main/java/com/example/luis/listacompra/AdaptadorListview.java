package com.example.luis.listacompra;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by luis on 15/12/2017.
 */

public class AdaptadorListview implements ListAdapter {
    private List<Producto> lista_productos;

    public void setLista_productos(List<Producto> lista_productos) {
        this.lista_productos = lista_productos;
    }

    private Context contexto;
    public AdaptadorListview(List<Producto> lista_productos, Context contexto) {
        this.lista_productos = lista_productos;
        this.contexto=contexto;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return lista_productos.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lf= LayoutInflater.from(contexto);
        View v=lf.inflate(R.layout.producto_cantidad, parent, false);
        TextView tv_producto=v.findViewById(R.id.tv_producto);
        TextView tv_cantidad=v.findViewById(R.id.tv_cantidad);
        tv_producto.setText(lista_productos.get(position).getProducto());
        tv_cantidad.setText(lista_productos.get(position).getCantidad());
        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }


}
