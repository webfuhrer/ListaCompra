package com.example.luis.listacompra;

/**
 * Created by luis on 15/12/2017.
 */

public class Producto {
    private String producto, cantidad;
    private int id;

    public Producto(String producto, String cantidad, int id) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public int getId() {
        return id;
    }
}
