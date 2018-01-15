package com.example.luis.listacompra;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentoInsertar extends Fragment {

    EditText et_cantidad;
    EditText et_producto;
    Context contexto;
    InsercionProducto insercion;
    public interface InsercionProducto
    {
        void insertarProducto(Producto p);
    }
    private View.OnClickListener oyente_boton=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String producto=et_producto.getText().toString();
            String cantidad=et_cantidad.getText().toString();
            insercion=(InsercionProducto)getActivity();
            insercion.insertarProducto(new Producto(producto, cantidad, 999));

        }
    };
    public FragmentoInsertar() {
        // Required empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contexto=getActivity().getBaseContext();//Inicializo contexto porque lop necesitar´para grabar en BD
        View vista=inflater.inflate(R.layout.fragment_fragmento_insertar, container, false);
         et_producto=(EditText)vista.findViewById(R.id.et_producto);
         et_cantidad=(EditText)vista.findViewById(R.id.et_cantidad);
        Button btn_grabar=(Button)vista.findViewById(R.id.btn_grabar);
        btn_grabar.setOnClickListener(oyente_boton);
        return vista;
    }




}
