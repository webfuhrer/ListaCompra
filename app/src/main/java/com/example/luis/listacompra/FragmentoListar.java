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

public class FragmentoListar extends Fragment  {
    ListView list_view;
    ActualizarFragmentoListar actualiza_list_view;//Esto en verdad solo borra
    List<Producto> lista_productos;
    // actualizar_list_view SERÁ LLAMADO DESDE EL mAINaCTIVITY CUANDO SE INSERTE UN NUEVO PRODUCTO para actualizar la lista
    MainActivity.ActualizarListView actualizar_list_view=new MainActivity.ActualizarListView() {
        @Override
        public void antualizacionListView(List<Producto> lista_productos) {
            FragmentoListar.this.lista_productos=lista_productos;
            AdaptadorListview adaptador=new AdaptadorListview(lista_productos, getActivity().getBaseContext());
            list_view.setAdapter(adaptador);
        }
    };
    //implemento el método actu




    public FragmentoListar() {
        // Required empty public constructor

    }



    public interface ActualizarFragmentoListar
    {
        void borrarElementos( int id_borrar);
        void cargaDatosEnListView();//Este método se llama al crearse al Fragment para decirle al Main que actualice el ListView
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista=inflater.inflate(R.layout.fragment_fragmento_listar, container, false);

        list_view=(ListView) vista.findViewById(R.id.lv_listar);
        actualiza_list_view=(ActualizarFragmentoListar) getActivity();
        actualiza_list_view.cargaDatosEnListView();
        /*final List<Producto> lista_productos=AccesoBD.listarProductos(getActivity().getBaseContext());
        AdaptadorListview adaptador=new AdaptadorListview(lista_productos, getActivity().getBaseContext());
        list_view.setAdapter(adaptador);*/
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.v("CLICADO", ""+position);
                actualiza_list_view=(ActualizarFragmentoListar) getActivity();
                int id_borrar=lista_productos.get(position).getId();
                actualiza_list_view.borrarElementos(id_borrar);
                   // AccesoBD.eliminarProducto(lista_productos.get(position), getActivity().getBaseContext());
                    //actualizarAdaptador();
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
