/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DetalleProducto;
import Modelo.Producto;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ConexionBDD;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 59397
 */
public class ProductoControlador {

    private Producto producto;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearProducto(Producto p) {
        try {
            String consultaSQL = "INSERT INTO producto"
                    + "(prod_nombre, stock, prod_precio ,prod_marca)"
                    + "VALUES"
                    + "(" + p.getNombreProducto() + ","
                    + "'" + p.getStock() + "',"
                    + "'" + p.getPrecio() + "',"
                    + "" + p.getMarca() + "),";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PEDIDO HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }
        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public int buscarCodigoProducto(int codigo) {
        try {
            String consultaSQL = "select codigo from producto where codigo='" + codigo + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idPersonas = resultado.getInt("idPersona");
                return idPersonas;
            } else {
                System.out.println("ingrese una cedula valida ");
            }

        } catch (Exception e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public void ActualisarProducto(int codigo, Producto p) {
        try {
            String consultaSQL = "UPDATE producto SET stock ="
                    + "'" + p.getStock() + "' and " + p.getPrecio() + " "
                    + "WHERE "
                    + "codigo = (SELECT codigo FROM producto WHERE codigo = "
                    + "'" + codigo + "')";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public void eliminarProducto(int codigo) {
        try {
            String consultaSQL = "DELETE FROM producto WHERE codigo='" + codigo + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO ELIMINADA");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
            }
        } catch (Exception e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }
    
   public void stockMinimo(int minimo) {
    try {
        String consultaSQL = "SELECT prod_nombre, prod_marca, stock FROM producto WHERE stock < '" + minimo + "';";
        ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
        resultado = ejecutar.executeQuery();
        System.out.println("Productos con stock por debajo del mínimo de " + minimo + ":");
        if (resultado.next()) {
            String nombre = resultado.getString("prod_nombre");
            String marca = resultado.getString("prod_marca");
            int stock = resultado.getInt("stock");
            System.out.println("Nombre: " + nombre + ", Marca: " + marca + ", Stock: " + stock);
        }
        
    } catch (Exception e) {
        System.out.println("ERROR AL OBTENER PRODUCTOS CON STOCK MÍNIMO: " + e.getMessage());
    }
}

    public ArrayList<Producto> listaProducto() {
        ArrayList<Producto> listaProducto = new ArrayList<>();

        try {
            String consultaSQL = "SELECT "
                    + " producto.prod_nombre, "
                    + " producto.prod_marca, "
                    + " producto.prod_precio, "
                    + " producto.stock, "
                    + " detalle_producto.cantidad, "
                    + " detalle_producto.precio_unitario "
                    + " FROM "
                    + " producto "
                    + " JOIN "
                    + " detalle_producto ON producto.idprod = detalle_producto.idprod; ";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                Producto producto = new Producto();
                DetalleProducto detalleProducto = new DetalleProducto();
                producto.setNombreProducto(resultado.getString("prod_nombre"));
                producto.setMarca(resultado.getString("prod_marca"));
                producto.setPrecio(resultado.getDouble("prod_precio"));
                producto.setStock(resultado.getInt("stock"));

                detalleProducto.setCantidad(resultado.getInt("cantidad"));
                detalleProducto.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                listaProducto.add(producto);
            } else {
                System.out.println("ingrese una cedula valida ");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaProducto;
    }

}
