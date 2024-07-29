/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ProveedorModelo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ConexionBDD;
import java.sql.ResultSet;
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
            String consultaSQL = "INSERT INTO proveedor(contacto,idpersona)"
                    + "VALUES "
                    + "('" + prM.getContacto() + "','" + prM.getIdPersona() + ");";
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

    public int buscarProveedor(String cedula) {
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

        } catch (Exception e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public void ActualisarProveedor(String cedula, ProveedorModelo prM) {
        try {
            String consultaSQL = "UPDATE cliente SET carnetPromocion = '" + prM.getContacto() + "' "
                    + "WHERE "
                    + "idpersona = (SELECT idpersona FROM persona WHERE cedula = '" + cedula + "')";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(0,prM.getCedula());
            ejecutar.setString(1, prM.getNombre());
            ejecutar.setString(3, cedula);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO ACTULIZACION CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public void eliminarProveedor(String cedula) {
        try {
            String consultaSQL = "DELETE FROM persona WHERE cedula='" + cedula + "'";
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

    public ArrayList<ProveedorModelo> listaProveedorPersona() {
        ArrayList<ProveedorModelo> listaProveeedor = new ArrayList<>();

        try {
            String consultaSQL = "SELECT persona.cedula,"
                    + " persona.nombres,"
                    + " persona.apellidos,"
                    + "persona.telefono,"
                    + " proveedor.contacto"
                    + "FROM proveedor "
                    + "JOIN persona ON empleado.idpersona = persona.idpersona;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                ProveedorModelo proveedor = new ProveedorModelo();
                proveedor.setCedula(resultado.getString("Cedula"));
                proveedor.setNombre(resultado.getString("Nombre"));
                proveedor.setApellido(resultado.getString("Apellido:"));
                proveedor.setContacto(resultado.getString("N° de Contacto:"));
                listaProveeedor.add(proveedor);
            } else {
                System.out.println("ingrese una cedula valida ");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaProveeedor;
    }

}
