package com.example.luis.listacompra;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.insertar:
                    Fragment fragmento_insertar=new FragmentoInsertar();
                    cargaFragmento(fragmento_insertar);
                    break;
                case R.id.listar:
                    Fragment fragmento_listar=new FragmentoListar();
                    cargaFragmento(fragmento_listar);
                    break;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Pongo que cargue por defecto el Fragment de insertar
        Fragment fragmento_seleccionado=new FragmentoInsertar();
        cargaFragmento(fragmento_seleccionado);
        //*******************************//
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void cargaFragmento(Fragment f)
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.contenedor_fragment, f);
        ft.commit();
    }
}
