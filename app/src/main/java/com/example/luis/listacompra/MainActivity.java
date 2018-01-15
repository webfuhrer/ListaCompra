package com.example.luis.listacompra;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentoInsertar.InsercionProducto, FragmentoListar.ActualizarFragmentoListar{
    FragmentoListar fl=new FragmentoListar();
    FragmentoInsertar fi=new FragmentoInsertar();
    ActualizarListView actualizador_list_view;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.insertar:

                    cargaFragmento(fi, "fragmento_insertar");
                    break;
                case R.id.listar:

                    cargaFragmento(fl, "fragmento_listar");
                    break;
            }

            return true;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (navigation!=null) {//Esto es para pantallas pequeñas
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            cargaFragmento(new FragmentoInsertar(), "fragmento_insertar");
        }
        else
//No hay BottomNavigation, osea que estoy en la tablet
        {
            cargaFragmentos(fi, fl);//Para tablet
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);

    }
    private void cargaFragmento(Fragment f, String etiqueta)//Para movil
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.contenedor_fragment, f,etiqueta );

        ft.commit();

    }
    private void cargaFragmentos(Fragment f1, Fragment f2)//Para tablet
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.contenedor_fragment, f1, "fragmento_insertar");
        ft.replace(R.id.contenedor_fragmentB, f2, "fragmento_listar");

        ft.commit();



    }


    //El FragmentoListar "avisa" al Main de que borre.
    //Para comunicarse, el FragmentoListar tiene una interface con un método
    //que se implementa aquí
    @Override
    public void borrarElementos( int id_borrar) {
        Producto producto_falso=new Producto("", "",id_borrar);
        AccesoBD.eliminarProducto(producto_falso, this);
        List<Producto> lista_productos=AccesoBD.listarProductos(this);
        //AdaptadorListview adaptador=new AdaptadorListview(lista_productos, getActivity().getBaseContext());
        actualizador_list_view=((FragmentoListar)getFragmentManager().findFragmentByTag("fragmento_listar")).actualizar_list_view;
        actualizador_list_view.antualizacionListView(lista_productos);

    }
//El método cargaDatosEnListView() es abstracto en FragmentoListar y se implementa aquí
    //De esta forma, cuando se va a crear el Fragment, se invoca a este método para que
    //rellene el ListView
    @Override
    public void cargaDatosEnListView() {
        /***********carga del ListView ***********/
      FragmentoListar f_listar=(FragmentoListar)getFragmentManager().findFragmentByTag("fragmento_listar");
        actualizador_list_view=f_listar.actualizar_list_view;
        List<Producto> lista_productos=AccesoBD.listarProductos(this);
        actualizador_list_view.antualizacionListView(lista_productos);

    }

    @Override
    public void insertarProducto(Producto p) {//Este método es abstracto en FragmentoInsertar y se implementa aquí
        AccesoBD.grabarProducto(p.getProducto(), p.getCantidad(), this);

        FragmentoListar fl=(FragmentoListar)getFragmentManager().findFragmentByTag("fragmento_listar");
        if (fl!=null) {//Si se está mostrando el FragmentoListar(tablet)
            actualizador_list_view = fl.actualizar_list_view;
            List<Producto> lista_productos = AccesoBD.listarProductos(this);
            actualizador_list_view.antualizacionListView(lista_productos);
        }
    }
    public interface ActualizarListView
    {
        void antualizacionListView(List<Producto> lista_productos);
    }
}
