/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.PersonaModelo;
import Modelo.ProveedorModelo;
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
public class ProveedorControlador {

    private ProveedorModelo proveedorModelo;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearProveedor(ProveedorModelo prM) {
        try {
            String consultaSQL = "INSERT INTO proveedor(contacto, idpersona) VALUES (?, ?)";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, prM.getContacto());
            ejecutar.setInt(2, prM.getIdPersona());
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }
    }

    public int buscarIdPersonaProveedor(String cedula) {
        try {
            String consultaSQL = "select idpersona from persona where cedula='" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idPersonas = resultado.getInt("idPersona");
                return idPersonas;
            } else {
                System.out.println("ingrese una cedula valida ");
            }

        } catch (SQLException e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public ProveedorModelo buscarDatosProveedor(String cedula) {
        ProveedorModelo pr = new ProveedorModelo();
        try {
            String consultaSQL = "SELECT * FROM persona p "
                    + "JOIN proveedor pr ON p.idpersona = pr.idpersona "
                    + "WHERE p.cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                pr.setNombre(resultado.getString("nombres"));
                pr.setApellido(resultado.getString("apellidos"));
                pr.setCedula(resultado.getString("cedula"));
                pr.setDireccion(resultado.getString("direccion"));
                pr.setFechaNacimiento(resultado.getString("fechaNacimiento"));
                pr.setTelefono(resultado.getString("telefono"));
                pr.setContacto(resultado.getString("contacto"));

                pr.imprimir();
                resultado.close();
                return pr;
            } else {
                System.out.println("Ingrese una cédula válida");
                resultado.close();
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return pr;

    }

    public void ActualisarProveedor(String cedula, ProveedorModelo prM, PersonaModelo pM) {
        try {
            String consultaSQLPersona = "UPDATE persona SET "
                    + "cedula = ?, "
                    + "nombres = ?, "
                    + "apellidos = ?, "
                    + "direccion = ?, "
                    + "fechaNacimiento = ?, "
                    + "telefono = ? "
                    + "WHERE cedula = ?";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQLPersona);
            ejecutar.setString(1, cedula);
            ejecutar.setString(2, pM.getNombre());
            ejecutar.setString(3, pM.getApellido());
            ejecutar.setString(4, pM.getDireccion());
            ejecutar.setString(5, pM.getFechaNacimiento());
            ejecutar.setString(6, pM.getTelefono());
            ejecutar.setString(7, cedula);

            int resPersona = ejecutar.executeUpdate();

            if (resPersona > 0) {
                System.out.println("LOS DATOS PERSONALES HAN SIDO ACTUALIZADOS CON ÉXITO");

                String consultaSQL = "UPDATE proveedor SET "
                        + "contacto = ? "
                        + "WHERE idpersona = (SELECT idpersona FROM persona WHERE cedula = ?)";
                ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
                ejecutar.setString(1, prM.getContacto());
                ejecutar.setString(2, cedula);

                int resCliente = ejecutar.executeUpdate();

                if (resCliente > 0) {
                    System.out.println("EL NUEVO CONTACTO HA SIDO ACTUALIZADO CON ÉXITO");
                } else {
                    System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                }

                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
            }

            ejecutar.close();

        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }
    }

    public void eliminarProveedor(String cedula) {
        try {
            String consultaSQL = "DELETE FROM persona WHERE cedula='" + cedula + "'";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL PROVEEDOR HA SIDO ELIMINADA");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public ArrayList<ProveedorModelo> listaProveedorPersona() {
        ArrayList<ProveedorModelo> listaProveeedor = new ArrayList<>();

        try {
            String consultaSQL = "SELECT persona.cedula, "
                    + "persona.nombres, "
                    + "persona.apellidos, "
                    + "persona.telefono, "
                    + "proveedor.contacto "
                    + "FROM proveedor "
                    + "JOIN persona ON proveedor.idpersona = persona.idpersona "
                    + "WHERE persona.cedula = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                ProveedorModelo proveedor = new ProveedorModelo();
                proveedor.setCedula(resultado.getString("cedula"));
                proveedor.setNombre(resultado.getString("nombres"));
                proveedor.setApellido(resultado.getString("apellidos"));
                proveedor.setContacto(resultado.getString("contacto"));
                proveedor.setTelefono(resultado.getString("telefono"));
                listaProveeedor.add(proveedor);

            }
            resultado.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaProveeedor;
    }

}
