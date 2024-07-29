/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClienteModelo;
import Modelo.DetalleProducto;
import Modelo.Pedidos;
import Modelo.PersonaModelo;
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
public class PedidoControlador {

    private Pedidos pedidos;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearPedido(Pedidos p, ClienteModelo c) {
        try {
            String consultaSQL = "INSERT INTO pedidos"
                    + "(idcli, pedi_fecha, pedi_total)"
                    + "VALUES"
                    + "(" + c.getIdCliente() + ",'" + p.getFechaPedido() + "',"
                    + "" + p.getTotal() + "),";
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

    public int buscarPedido(String cedula) {
        try {
            String consultaSQL = "SELECT idcli FROM cliente WHERE idpersona = (SELECT idpersona FROM persona WHERE cedula = " + cedula + ")";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                return resultado.getInt("idcli");
            } else {
                System.out.println("ingrese una cedula valida ");
            }

        } catch (Exception e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public void ActualisarPedido(String cedula, Pedidos p) {
        try {
            String consultaSQL = "UPDATE pedidos SET pedi_fecha = '"
                    + p.getFechaPedido() + "', pedi_total = " + p.getTotal() + " "
                    + "WHERE idcli = (SELECT idcli "
                    + "FROM cliente "
                    + "WHERE idpersona = (SELECT idpersona "
                    + "FROM persona "
                    + "WHERE cedula =" + cedula + "))";
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

    public void eliminarEmpleado(String cedula) {
        try {
            String consultaSQL = "DELETE FROM pedidos "
                    + "WHERE idcli = (SELECT idcli "
                    + "FROM cliente "
                    + "WHERE idpersona = (SELECT idpersona "
                    + "FROM persona "
                    + "WHERE cedula =" + cedula + "))";
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

    public ArrayList<Pedidos> listaPedidos() {
        ArrayList<Pedidos> listaPedido = new ArrayList<>();

        try {
            String consultaSQL = "SELECT "
                    + "persona.cedula,"
                    + " persona.nombres,"
                    + " persona.apellidos,"
                    + " persona.telefono,"
                    + " cliente.carnetPromocion,"
                    + " pedidos.pedi_fecha,"
                    + " pedidos.pedi_total,"
                    + " producto.prod_nombre,"
                    + " producto.prod_marca,"
                    + " producto.prod_precio,"
                    + " producto.stock,"
                    + " detalle_producto.cantidad,"
                    + " detalle_producto.precio_unitario"
                    + "FROM "
                    + "persona "
                    + "JOIN "
                    + "cliente ON cliente.idpersona = persona.idpersona"
                    + "JOIN "
                    + "pedidos ON pedidos.idcli = cliente.idcli"
                    + "JOIN "
                    + "detalle_producto ON pedidos.idpedi = detalle_producto.idpedi"
                    + "JOIN "
                    + "producto ON producto.idprod = detalle_producto.idprod;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                Pedidos p = new Pedidos();
                ClienteModelo cM = new ClienteModelo();
                Producto pr = new Producto();
                DetalleProducto dp = new DetalleProducto();

                cM.setCedula(resultado.getString("cedula"));
                cM.setNombre(resultado.getString("nombres"));
                cM.setApellido(resultado.getString("apellidos"));
                cM.setTelefono(resultado.getString("telefono"));
                cM.setCarnetPromocion(resultado.getBoolean("carnetPromocion"));

                p.setFechaPedido(resultado.getString("pedi_fecha"));
                p.setTotal(resultado.getInt("pedi_total"));

                pr.setNombreProducto(resultado.getString("prod_nombre"));
                pr.setMarca(resultado.getString("prod_marca"));
                pr.setPrecio(resultado.getDouble("prod_precio"));
                pr.setStock(resultado.getInt("stock"));

                dp.setCantidad(resultado.getInt("cantidad"));
                dp.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                listaPedido.add(p);
            } else {
                System.out.println("ingrese una cedula valida ");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaPedido;
    }

}
