/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class Inventario extends Producto {

    private int stockActual;
    private int stockMinimo;

    public Inventario() {
    }

    public Inventario(int stockActual, int stockMinimo, String nombreProducto, String descripcion, int stock, double precio) {
        super(nombreProducto, descripcion, stock, precio);
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String imprimir() {
        return "DATOS PERSONALES\n"
                + "NOMBRE PRODUCTO:" + getNombreProducto() + "\n"
                + "DESCRIPCION:" + getDescripcion() + "\n"
                + "STOCK:" + getStock() + "\n"
                + "PRECIO:" + getPrecio() + "\n"
                + "STOCK ACTUAL:" + getStockActual() + "\n"
                + "STOCK MINIMO:" + getStockMinimo() + "\n";
    }

}
