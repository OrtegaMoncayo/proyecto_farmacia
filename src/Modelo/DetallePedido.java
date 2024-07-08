/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class DetallePedido extends Pedidos{
    private int cantidad;
    private double precioUnitario;

    public DetallePedido() {
    }

    public DetallePedido(int cantidad, double precioUnitario, String fechaPedido, int total) {
        super(fechaPedido, total);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
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
    
    
}
