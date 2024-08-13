/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class Pedidos {

    private int idPedido;
    private int idCliente;
    private String fechaPedido;
    private double total;
    private DetalleProducto detalleProducto;
    private ClienteModelo cliente;
    private Producto producto;

    public Pedidos() {
    }

    public Pedidos(int idPedido, int idCliente, String fechaPedido, double total, DetalleProducto detalleProducto, ClienteModelo cliente, Producto producto) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.detalleProducto = detalleProducto;
        this.cliente = cliente;
        this.producto = producto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public DetalleProducto getDetalleProducto() {
        return detalleProducto;
    }

    public void setDetalleProducto(DetalleProducto detalleProducto) {
        this.detalleProducto = detalleProducto;
    }

    public ClienteModelo getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModelo cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String imprimir() {
        return "Datos Pedidos\n"
                + "NOMBRE;" + getFechaPedido() + "\n"
                + "APELLIDO:" + getTotal() + "\n";
    }

    public String imprimirDetallePedidoProducto() {
        StringBuilder detalles = new StringBuilder("Datos Pedidos\n");

        if (getCliente() != null) {
            detalles.append("NOMBRE: ").append(getCliente().getNombre()).append("\n")
                    .append("APELLIDO: ").append(getCliente().getApellido()).append("\n")
                    .append("CEDULA: ").append(getCliente().getCedula()).append("\n")
                    .append("CARNET DE PROMOCION: ").append(getCliente().getCarnetPromocion() ? "Sí" : "No").append("\n");
        } else {
            detalles.append("Información del cliente no disponible.\n");
        }
        if (getProducto() != null) {
            detalles.append("NOMBRE DE PRODUCTO: ").append(getProducto().getNombreProducto()).append("\n")
                    .append("MARCA: ").append(getProducto().getMarca()).append("\n")
                    .append("PRECIO: ").append(getProducto().getPrecio()).append("\n");
        } else {
            detalles.append("Información del producto no disponible.\n");
        }

        if (getDetalleProducto() != null) {
            detalles.append("Total: ").append(getTotal()).append("\n")
                    .append("Cantidad: ").append(getDetalleProducto().getCantidad()).append("\n")
                    .append("Precio Unitario: ").append(getDetalleProducto().getPrecioUnitario()).append("\n");
        } else {
            detalles.append("Detalles del producto no disponibles.\n");
        }

        detalles.append("Fecha de pedido: ").append(getFechaPedido()).append("\n");

        return detalles.toString();
    }

    public String imprimirDetallePedido() {
        StringBuilder detalles = new StringBuilder("Datos Pedidos\n");

        if (getCliente() != null) {
            detalles.append("NOMBRE: ").append(getCliente().getNombre()).append("\n")
                    .append("APELLIDO: ").append(getCliente().getApellido()).append("\n")
                    .append("CEDULA: ").append(getCliente().getCedula()).append("\n")
                    .append("CARNET DE PROMOCION: ").append(getCliente().getCarnetPromocion() ? "Sí" : "No").append("\n");
        } else {
            detalles.append("Información del cliente no disponible.\n");
        }
        if (getProducto() != null) {
            detalles.append("NOMBRE DE PRODUCTO: ").append(getProducto().getNombreProducto()).append("\n")
                    .append("MARCA: ").append(getProducto().getMarca()).append("\n")
                    .append("PRECIO: ").append(getProducto().getPrecio()).append("\n");
        } else {
            detalles.append("Información del producto no disponible.\n");
        }

        if (getDetalleProducto() != null) {
            detalles.append("Total: ").append(getTotal()).append("\n")
                    .append("Cantidad: ").append(getDetalleProducto().getCantidad()).append("\n")
                    .append("Precio Unitario: ").append(getDetalleProducto().getPrecioUnitario()).append("\n");
        } else {
            detalles.append("Detalles del producto no disponibles.\n");
        }

        detalles.append("Fecha de pedido: ").append(getFechaPedido()).append("\n");

        return detalles.toString();
    }

}
