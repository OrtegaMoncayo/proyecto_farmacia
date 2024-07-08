/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class Producto {
    private String nombreProducto;
    private String descripcion;
    private int stock;
    private double precio;

    public Producto() {
    }

    public Producto(String nombreProducto, String descripcion, int stock, double precio) {
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
      public String imprimir() {
        return "DATOS PERSONALES\n"
                + "ID:" + getNombreProducto() + "\n"
                + "NOMBRE;" + getDescripcion() + "\n"
                + "APELLIDO:" + getStock() + "\n"
                + "CEDULA;" + getPrecio() + "\n";
    }
    
}
