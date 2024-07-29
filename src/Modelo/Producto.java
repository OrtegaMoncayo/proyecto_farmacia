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
    private String marca;
    private int stock;
    private double precio;
    private int stockMinimo ;
    private int codigo;

    public Producto() {
    }

    public Producto(String nombreProducto, String marca, int stock, double precio, int stockMinimo, int codigo) {
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.codigo = codigo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    

    public String imprimir() {
        return "DATOS PERSONALES\n"
                + "ID:" + getNombreProducto() + "\n"
                + "NOMBRE;" + getMarca() + "\n"
                + "APELLIDO:" + getStock() + "\n"
                + "CEDULA;" + getPrecio() + "\n"
                + "NOMBRE;" + getStock() + "\n"
                + "APELLIDO:" + getStockMinimo() + "\n"
                + "APELLIDO:" + getCodigo() + "\n";
    }

}
