/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class DetalleProducto {

    private int cantidad;
    private double precioUnitario;
    private int idProducto;
    private int idPedido;

    public DetalleProducto() {
    }

    public DetalleProducto(int cantidad, double precioUnitario, int idProducto, int idPedido) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idProducto = idProducto;
        this.idPedido = idPedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String imprimirDetalle() {
        return "Datos Pedidos\n"
                + "Cantidad:" + getCantidad() + "\n"
                + "Precio Unitario:" + getPrecioUnitario() + "\n";
    }

}
