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

    private int idProducto;
    private int idCliente;
    private String nombreProducto;
    private String marca;
    private int stock;
    private double precio;
    private int stockMinimo;
    private int codigo;
    private DetalleProducto detalleProducto;

    public Producto() {
    }

    public Producto(int idProducto, int idCliente, String nombreProducto, String marca, int stock, double precio, int stockMinimo, int codigo, DetalleProducto detalleProducto) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.nombreProducto = nombreProducto;
        this.marca = marca;
        this.stock = stock;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        this.codigo = codigo;
        this.detalleProducto = detalleProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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
        return "DATOS DEL PRODUCTO\n"
                + "NOMBRE DE PRODUCTO:" + getNombreProducto() + "\n"
                + "MARCA:" + getMarca() + "\n"
                + "PRECIO:" + getPrecio() + "\n"
                + "STOCK:" + getStock() + "\n"
                + "CODIGO:" + getCodigo() + "\n";
    }

    public String imprimirDetalleProducto() {
        DetalleProducto detalle = getDetalleProducto();
        if (detalle != null) {
            return "DATOS DEL PRODUCTO\n"
                    + "NOMBRE DE PRODUCTO: " + getNombreProducto() + "\n"
                    + "MARCA: " + getMarca() + "\n"
                    + "PRECIO: " + getPrecio() + "\n"
                    + "STOCK: " + getStock() + "\n"
                    + "CODIGO: " + getCodigo() + "\n"
                    + "Cantidad: " + detalle.getCantidad() + "\n"
                    + "Precio Unitario: " + detalle.getPrecioUnitario() + "\n";
        } else {
            return "DATOS DEL PRODUCTO\n"
                    + "NOMBRE DE PRODUCTO: " + getNombreProducto() + "\n"
                    + "MARCA: " + getMarca() + "\n"
                    + "PRECIO: " + getPrecio() + "\n"
                    + "STOCK: " + getStock() + "\n"
                    + "CODIGO: " + getCodigo() + "\n"
                    + "Detalle del producto no disponible.\n";
        }
    }

}
