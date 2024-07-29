/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DetalleProducto;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ConexionBDD;
import java.sql.ResultSet;

/**
 *
 * @author 59397
 */
public class DetalleProductoControlador {

    private DetalleProducto detallePedido;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearDetalleProducto(DetalleProducto dp) {
        try {
            String consultaSQL = "INSERT INTO detalle_pedido"
                    + "(idprod, idpedi,cantida, precio_unitario)"
                    + "VALUES"
                    + "(" + dp.getIdProducto() + "," + dp.getIdPedido() + ",'" + dp.getCantidad() + "',"
                    + "" + dp.getPrecioUnitario() + "),";
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

}
