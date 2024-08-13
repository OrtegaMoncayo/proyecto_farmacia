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
import java.sql.SQLException;
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
            String consultaSQL = "INSERT INTO producto (prod_nombre, stock, prod_precio, prod_marca,codigo) VALUES (?, ?, ?, ?,?)";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, p.getNombreProducto());
            ejecutar.setInt(2, p.getStock());
            ejecutar.setDouble(3, p.getPrecio());
            ejecutar.setString(4, p.getMarca());
            ejecutar.setInt(5, p.getCodigo());
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PRODUCTO HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }
    }

    public int buscarIdProducto(int codigo) {
        try {
            String consultaSQL = "SELECT idprod FROM producto WHERE codigo = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idProducto = resultado.getInt("idprod");
                return idProducto;
            } else {
                System.out.println("Ingrese un código de producto válido.");
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }
        return 0;
    }

    public Producto buscarDatosProducto(String nombreProducto, int codigo) {
        Producto pr = new Producto();
        try {
            String consultaSQL = "SELECT * FROM producto p "
                    + "WHERE p.prod_nombre = ? "
                    + "AND p.codigo = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, nombreProducto);
            ejecutar.setInt(2, codigo);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                pr.setNombreProducto(resultado.getString("prod_nombre"));
                pr.setMarca(resultado.getString("prod_marca"));
                pr.setPrecio(resultado.getDouble("prod_precio"));
                pr.setStock(resultado.getInt("stock"));
                pr.setCodigo(resultado.getInt("codigo"));

                pr.imprimir();
                resultado.close();
                return pr;
            } else {
                System.out.println("Ingrese una Nombre del Producto");
                resultado.close();
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }
        return pr;

    }

    public void ActualisarProducto(int codigo, String nombreProducto, Producto p) {
        try {
            String consultaSQL = "UPDATE producto SET "
                    + "prod_nombre= ? ,"
                    + "stock =?,"
                    + "prod_precio=?,"
                    + "prod_marca=?"
                    + "WHERE codigo =?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, p.getNombreProducto());
            ejecutar.setInt(2, p.getStock());
            ejecutar.setDouble(3, p.getPrecio());
            ejecutar.setString(4, p.getMarca());
            ejecutar.setInt(5, codigo);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PRODUCTO HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }
    }

    public void eliminarProducto(String nombreProducto, int codigo) {
        try {
            String consultaSQL = "DELETE dp FROM detalle_producto dp "
                    + "JOIN pedidos pe ON dp.idpedi = pe.idpedi "
                    + "JOIN cliente c ON pe.idcli = c.idcli "
                    + "JOIN persona p ON c.idpersona = p.idpersona "
                    + "JOIN producto pr ON dp.idprod = pr.idprod "
                    + "WHERE  pr.prod_nombre = ?"
                    + "and codigo=?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, nombreProducto);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PRODUCTO HA SIDO ELIMINADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public void stockMinimo(int minimo) {
        Producto pr = new Producto();
        try {
            String consultaSQL = "SELECT prod_nombre, prod_marca, stock FROM producto WHERE stock <= ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, minimo);
            resultado = ejecutar.executeQuery();
            System.out.println("Productos con stock por debajo del mínimo de " + minimo + ":");
            boolean hayProductos = false;
            while (resultado.next()) {
                String nombre = resultado.getString("prod_nombre");
                String marca = resultado.getString("prod_marca");
                int stock = resultado.getInt("stock");
                System.out.println("Nombre: " + nombre + ", Marca: " + marca + ", Stock: " + stock);
                hayProductos = true;
            }
            if (!hayProductos) {
                System.out.println("No hay productos con stock por debajo del mínimo.");
            }
        } catch (SQLException e) {
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
            while (resultado.next()) {
                Producto p = new Producto();

                p.setNombreProducto(resultado.getString("prod_nombre"));
                p.setMarca(resultado.getString("prod_marca"));
                p.setPrecio(resultado.getDouble("prod_precio"));
                p.setStock(resultado.getInt("stock"));

                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setCantidad(resultado.getInt("cantidad"));
                detalleProducto.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                p.setDetalleProducto(detalleProducto);
                listaProducto.add(p);

            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaProducto;
    }

    public ArrayList<Producto> listarProductosConStocksuperio(int codigo) {
        ArrayList<Producto> listaProducto = new ArrayList<>();

        try {
            String consultaSQL = "SELECT "
                    + "producto.prod_nombre, "
                    + "producto.prod_marca, "
                    + "producto.prod_precio, "
                    + "producto.stock, "
                    + "detalle_producto.cantidad, "
                    + "detalle_producto.precio_unitario "
                    + "FROM "
                    + "producto "
                    + "JOIN "
                    + "detalle_producto ON producto.idprod = detalle_producto.idprod "
                    + "WHERE producto.codigo = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setInt(1, codigo);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                Producto pr = new Producto();

                pr.setNombreProducto(resultado.getString("prod_nombre"));
                pr.setMarca(resultado.getString("prod_marca"));
                pr.setPrecio(resultado.getDouble("prod_precio"));
                pr.setStock(resultado.getInt("stock"));

                DetalleProducto detalleProducto = new DetalleProducto();
                detalleProducto.setCantidad(resultado.getInt("cantidad"));
                detalleProducto.setPrecioUnitario(resultado.getDouble("precio_unitario"));

                pr.setDetalleProducto(detalleProducto);

                listaProducto.add(pr);

            }
            resultado.close();
            ejecutar.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de Producto: " + e);

        }

        return listaProducto;
    }

}
