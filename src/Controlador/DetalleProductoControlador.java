/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DetalleProducto;
import Modelo.Pedidos;
import Modelo.Producto;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ConexionBDD;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author 59397
 */
public class DetalleProductoControlador {

    private DetalleProducto detalleProducto;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

      public void crearDetalleProducto(DetalleProducto dp) {
        try {
            String consultaSQL = "INSERT INTO detalle_producto"
                    + "(idprod, idpedi, cantidad, precio_unitario)"
                    + "VALUES (?, ?, ?, ?)";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, dp.getIdProducto());
            ejecutar.setInt(2, dp.getIdPedido());
            ejecutar.setInt(3, dp.getCantidad());
            ejecutar.setDouble(4, dp.getPrecioUnitario());
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL DETALLE DEL PEDIDO HA SIDO CREADO CON ÉXITO");
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void ActualisarDetalleProducto(int idDetalle, DetalleProducto dp) {
        try {
            String consultaSQL = "UPDATE detalle_pedido SET cantidad = ?, precio_unitario = ? WHERE id = ?";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, dp.getCantidad());
            ejecutar.setDouble(2, dp.getPrecioUnitario());
            ejecutar.setInt(3, idDetalle);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL DETALLE DEL PEDIDO HA SIDO ACTUALIZADO CON ÉXITO");
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public void eliminarDetalleProducto(int idDetalle) {
        try {
            String consultaSQL = "DELETE FROM detalle_pedido WHERE id = ?";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, idDetalle);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL DETALLE DEL PEDIDO HA SIDO ELIMINADO CON ÉXITO");
            } else {
                System.out.println("ERROR AL ELIMINAR EL DETALLE DEL PEDIDO");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
