package com.example.luis.listacompra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 15/12/2017.
 */

public class AccesoBD {
    public static void grabarProducto(String producto, String cantidad, Context contexto) {
        SQLiteDatabase db=obtenerDB(contexto);
        String sql="INSERT INTO productos(producto, cantidad) VALUES(?,?);";
        String[] args={producto, cantidad};
        db.execSQL(sql,args);
    }
    public static List<Producto> listarProductos(Context contexto) {
        List<Producto> lista_compra=new ArrayList<>();
        SQLiteDatabase db=obtenerDB(contexto);
        String sql="SELECT * FROM productos";
        Cursor c=db.rawQuery(sql, null);
        while(c.moveToNext())
        {
            int indice_producto=c.getColumnIndex("producto");
            int indice_cantidad=c.getColumnIndex("cantidad");
            int indice_id=c.getColumnIndex("id");
            String producto=c.getString(indice_producto);
            String cantidad=c.getString(indice_cantidad);
            int id=c.getInt(indice_id);
            Producto p=new Producto(producto, cantidad, id);
            lista_compra.add(p);
        }
        return lista_compra;
    }
    private static SQLiteDatabase obtenerDB(Context contexto)
    {
        SQLiteOpenHelper sq=new SQLiteOpenHelper(contexto, "cesta_compra_v2", null,1) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                String creacion_bd="CREATE TABLE productos (id INTEGER PRIMARY KEY , producto TEXT, cantidad TEXT);";
                db.execSQL(creacion_bd);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        };
        SQLiteDatabase db=sq.getWritableDatabase();
        return db;
    }

    public static void eliminarProducto(Producto producto, Context contexto) {
        SQLiteDatabase db=obtenerDB(contexto);
        String sql="DELETE FROM productos WHERE id=?";
        String[] args={String.valueOf(producto.getId())};

        db.execSQL(sql,args);

    }
}
