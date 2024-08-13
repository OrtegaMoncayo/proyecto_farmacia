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
import java.sql.SQLException;
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

    public void crearPedido(Pedidos p) {
            
        
        try {
            String consultaSQL = "INSERT INTO pedidos (idcli, pedi_fecha, pedi_total) VALUES (?, ?, ?)";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setInt(1, p.getIdCliente());
            ejecutar.setString(2, p.getFechaPedido());
            ejecutar.setDouble(3, p.getTotal());
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PEDIDO HA SIDO CREADO CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("ERROR: " + e);
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

        } catch (SQLException e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public int buscarIdPedido(String cedula) {
        int idPedido=0; 
        try {
            String consultaSQL = "SELECT pe.idpedi "
                + "FROM pedidos pe "
                + "JOIN cliente c ON pe.idcli = c.idcli "
                + "JOIN persona p ON c.idpersona = p.idpersona "
                + "WHERE p.cedula = ?;";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                idPedido = resultado.getInt("idpedi");
                return idPedido;
            } else {
                System.out.println("Ingrese un ID de pedido válido");
            }

        } catch (SQLException e) {
            System.out.println("COMUNÍQUESE CON EL ADMINISTRADOR DEL SISTEMA: " + e);
        }
        return idPedido;
    }

    public void ActualisarPedido(String cedula, Pedidos p) {
        try {
            String consultaSQL = "UPDATE pedidos SET "
                    + "pedi_fecha =? , "
                    + "pedi_total = ? "
                    + "WHERE idcli = (SELECT idcli FROM cliente "
                    + "WHERE idpersona = (SELECT idpersona FROM persona "
                    + "WHERE cedula =?));";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, p.getFechaPedido());
            ejecutar.setDouble(2, p.getTotal());
            ejecutar.setString(3, cedula);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
    }

    public void eliminarPedido(String cedula, String nombreProducto) {
        try {
            String consultaEliminarDetalles = "DELETE dp FROM detalle_producto dp "
                    + "JOIN pedidos pe ON dp.idpedi = pe.idpedi "
                    + "JOIN cliente c ON pe.idcli = c.idcli "
                    + "JOIN persona p ON c.idpersona = p.idpersona "
                    + "JOIN producto pr ON dp.idprod = pr.idprod "
                    + "WHERE p.cedula = ? "
                    + "AND pr.prod_nombre = ?;";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaEliminarDetalles);
            ejecutar.setString(1, cedula);
            ejecutar.setString(2, nombreProducto);
            int resDetalle = ejecutar.executeUpdate();

            if (resDetalle > 0) {

                String consultaEliminarPedidos = "DELETE pe FROM pedidos pe "
                        + "JOIN cliente c ON pe.idcli = c.idcli "
                        + "JOIN persona p ON c.idpersona = p.idpersona "
                        + "WHERE p.cedula = ? "
                        + "AND pe.idpedi IN ("
                        + "    SELECT dp.idpedi "
                        + "    FROM detalle_producto dp "
                        + "    JOIN producto pr ON dp.idprod = pr.idprod "
                        + "    WHERE pr.prod_nombre = ?);";
                ejecutar = (PreparedStatement) connection.prepareStatement(consultaEliminarPedidos);
                ejecutar.setString(1, cedula);
                ejecutar.setString(2, nombreProducto);
                int resPedido = ejecutar.executeUpdate();

                if (resPedido > 0) {
                    System.out.println("LA PEDIDO HA SIDO ELIMINADA");

                } else {
                    System.out.println("ERROR AL ELIMINAR PERSONA");
                }
                ejecutar.close();
            } else {
                System.out.println("");
            }
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public ArrayList<Pedidos> listaPedidos(String cedula) {
        ArrayList<Pedidos> listaPedido = new ArrayList<>();

        try {
            String consultaSQL = "select p.cedula , p.nombres, p.apellidos,c.carnetPromocion,\n"
                    + " pr.pedi_fecha,pr.pedi_total,\n"
                    + " prd.prod_nombre,prd.prod_marca,prd.prod_precio,\n"
                    + " dp.cantidad,dp.precio_unitario \n"
                    + " from persona p \n"
                    + " join cliente c on c.idpersona=p.idpersona\n"
                    + " join pedidos pr on pr.idcli=c.idcli\n"
                    + " join detalle_producto dp on dp.idpedi=pr.idpedi\n"
                    + " join producto prd on prd.idprod=dp.idprod\n"
                    + " where p.cedula=?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {

                Pedidos p = new Pedidos();
                ClienteModelo cM = new ClienteModelo();
                Producto pr = new Producto();
                DetalleProducto dp = new DetalleProducto();

                cM.setCedula(resultado.getString("cedula"));
                cM.setNombre(resultado.getString("nombres"));
                cM.setApellido(resultado.getString("apellidos"));
                cM.setCarnetPromocion(resultado.getBoolean("carnetPromocion"));

                p.setFechaPedido(resultado.getString("pedi_fecha"));
                p.setTotal(resultado.getInt("pedi_total"));

                pr.setNombreProducto(resultado.getString("prod_nombre"));
                pr.setMarca(resultado.getString("prod_marca"));
                pr.setPrecio(resultado.getDouble("prod_precio"));

                dp.setCantidad(resultado.getInt("cantidad"));
                dp.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                p.setCliente(cM);
                p.setProducto(pr);
                p.setDetalleProducto(dp);
                listaPedido.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaPedido;
    }

    public ArrayList<Pedidos> listaPedidos() {
        ArrayList<Pedidos> listaPedido = new ArrayList<>();

        try {
            String consultaSQL = " select p.cedula as Cedula, p.nombres, p.apellidos,c.carnetPromocion,\n"
                    + " pr.pedi_fecha,pr.pedi_total,\n"
                    + " prd.prod_nombre,prd.prod_marca,prd.prod_precio,\n"
                    + " dp.cantidad,dp.precio_unitario \n"
                    + " from persona p \n"
                    + " join cliente c on c.idpersona=p.idpersona\n"
                    + " join pedidos pr on pr.idcli=c.idcli\n"
                    + " join detalle_producto dp on dp.idpedi=pr.idpedi\n"
                    + " join producto prd on prd.idprod=dp.idprod;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {

                Pedidos p = new Pedidos();
                ClienteModelo cM = new ClienteModelo();
                Producto pr = new Producto();
                DetalleProducto dp = new DetalleProducto();

                cM.setCedula(resultado.getString("cedula"));
                cM.setNombre(resultado.getString("nombres"));
                cM.setApellido(resultado.getString("apellidos"));
                cM.setCarnetPromocion(resultado.getBoolean("carnetPromocion"));

                p.setFechaPedido(resultado.getString("pedi_fecha"));
                p.setTotal(resultado.getInt("pedi_total"));

                pr.setNombreProducto(resultado.getString("prod_nombre"));
                pr.setMarca(resultado.getString("prod_marca"));
                pr.setPrecio(resultado.getDouble("prod_precio"));

                dp.setCantidad(resultado.getInt("cantidad"));
                dp.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                p.setCliente(cM);
                p.setProducto(pr);
                p.setDetalleProducto(dp);
                listaPedido.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaPedido;
    }

}
