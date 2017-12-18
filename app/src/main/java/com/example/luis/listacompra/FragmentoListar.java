package com.example.luis.listacompra;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class FragmentoListar extends Fragment {
    ListView list_view;
    public FragmentoListar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista=inflater.inflate(R.layout.fragment_fragmento_listar, container, false);
        list_view=(ListView) vista.findViewById(R.id.lv_listar);
        final List<Producto> lista_productos=AccesoBD.listarProductos(getActivity().getBaseContext());
        AdaptadorListview adaptador=new AdaptadorListview(lista_productos, getActivity().getBaseContext());
        list_view.setAdapter(adaptador);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.v("CLICADO", ""+position);
                    AccesoBD.eliminarProducto(lista_productos.get(position), getActivity().getBaseContext());
                    actualizarAdaptador();
                }

        });
        return vista;

    }



private void actualizarAdaptador()
{
    List<Producto> lista_productos=AccesoBD.listarProductos(getActivity().getBaseContext());
    AdaptadorListview adaptador=new AdaptadorListview(lista_productos, getActivity().getBaseContext());
    list_view.setAdapter(adaptador);
}

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
