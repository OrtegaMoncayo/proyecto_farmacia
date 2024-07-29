/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.ClienteModelo;
import Modelo.PersonaModelo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ConexionBDD;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author 59397
 */
public class ClienteControlador {

    private ClienteModelo clienteModelo;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearCliente(ClienteModelo cM) {
        try {
            String consultaSQL = "INSERT INTO cliente"
                    + "(carnetPromocion,idpersona)"
                    + "VALUES "
                    + "(" + cM.getCarnetPromocion() + "," + cM.getIdPersona() + ");";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL CLIENTE HA SIDO CREADA CON ÉXITO");
                ejecutar.close();
            } else {
                System.out.println("FAVOR INGRESE CORRECTAMENTE LOS DATOS SOLICITADOS");
                ejecutar.close();
            }

        } catch (Exception e) {
            System.out.println("ERROR" + e);
        }
    }

    public int buscarClienteIdCliente(String cedula) {
        try {
            String consultaSQL = "select idpersona from persona where cedula='" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idCliente = resultado.getInt("idCliente");
                return idCliente;
            } else {
                System.out.println("ingrese una cedula valida ");
            }

        } catch (Exception e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public void ActualisarCliente(String cedula, ClienteModelo cM, PersonaModelo pM) {
        try {
            String consultaSQL = "UPDATE cliente SET carnetPromocion = '" + cM.getCarnetPromocion() + "' "
                    + "WHERE "
                    + "idpersona = (SELECT idpersona FROM persona WHERE cedula = '" + cedula + "')";
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

    public void eliminarCliente(String cedula) {
        try {
            String consultaSQL = "DELETE FROM persona,cliente WHERE cedula='?'";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, cedula);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA PERSONA HA SIDO ELIMINADA");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
                ejecutar.close();
            }
        } catch (Exception e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public ArrayList<ClienteModelo> listaClientePersona() {
        ArrayList<ClienteModelo> listaCliente = new ArrayList<>();

        try {
            String consultaSQL = "select persona.cedula,\n"
                    + "persona.nombres,\n"
                    + "persona.apellidos,\n"
                    + "cliente.carnetPromocion\n"
                    + "FROM cliente\n"
                    + "JOIN persona ON cliente.idpersona=persona.idpersona;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                ClienteModelo cliente = new ClienteModelo();
                cliente.setCedula(resultado.getString("cedula"));
                cliente.setNombre(resultado.getString("nombres"));
                cliente.setApellido(resultado.getString("apellidos"));
                cliente.setCarnetPromocion(resultado.getBoolean("carnetPromocion"));
                listaCliente.add(cliente);
            } else {
                System.out.println("ingrese una cedula valida ");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);
            
        }

        return listaCliente;
    }

}
