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
import java.sql.SQLException;
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

        } catch (SQLException e) {
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

        } catch (SQLException e) {
            System.out.println("COMUNIQUESE CON EL ALMINISTRADOR DEL SISTEMA PARA EL SISTEMA" + e);
        }
        return 0;

    }

    public int buscarIdCliente(String cedula) {
        try {
            String consultaSQL = "SELECT c.idcli "
                    + "FROM cliente c "
                    + "JOIN persona p ON c.idpersona = p.idpersona "
                    + "WHERE p.cedula = ?";
            ejecutar = (PreparedStatement) connection.prepareStatement(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                int idcli = resultado.getInt("idcli");
                return idcli;
            } else {
                System.out.println("Ingrese un ID de cliente válido");
            }

        } catch (SQLException e) {
            System.out.println("COMUNÍQUESE CON EL ADMINISTRADOR DEL SISTEMA: " + e);
        }
        return 0;
    }

    public ClienteModelo buscarDatosCliente(String cedula) {
        ClienteModelo cM = new ClienteModelo();
        try {
            String consultaSQL = "SELECT * FROM persona p "
                    + "JOIN cliente c ON p.idpersona = c.idpersona "
                    + "WHERE p.cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                cM.setNombre(resultado.getString("nombres"));
                cM.setApellido(resultado.getString("apellidos"));
                cM.setCedula(resultado.getString("cedula"));
                cM.setDireccion(resultado.getString("direccion"));
                cM.setFechaNacimiento(resultado.getString("fechaNacimiento"));
                cM.setTelefono(resultado.getString("telefono"));
                cM.setCarnetPromocion(resultado.getBoolean("carnetPromocion"));

                cM.imprimir();
                resultado.close();
                return cM;
            } else {
                System.out.println("Ingrese una cédula válida");
                resultado.close();
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return cM;

    }

    public void ActualizarCliente(String cedula, ClienteModelo cM, PersonaModelo pM) {
        try {

            String consultaSQLPersona = "UPDATE persona SET "
                    + "cedula = ?,"
                    + "nombres = ?,"
                    + "apellidos =?,"
                    + "direccion = ?,"
                    + "fechaNacimiento = ?,"
                    + "telefono = ? "
                    + "WHERE cedula = ?;";
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
                System.out.println("LOS DATOS PERSONALES DEL CLIENTE HAN SIDO ACTUALIZADOS CON ÉXITO");

                // Luego actualizamos la tabla 'cliente'
                String consultaSQL = "UPDATE cliente SET carnetPromocion = ? WHERE idpersona = (SELECT idpersona FROM persona WHERE cedula = ?);";
                ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
                ejecutar.setBoolean(1, cM.getCarnetPromocion());
                ejecutar.setString(2, cedula);

                int resCliente = ejecutar.executeUpdate();

                if (resCliente > 0) {
                    System.out.println("EL CARNET DE PROMOCIÓN HA SIDO ACTUALIZADO CON ÉXITO");
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

    public void eliminarCliente(String cedula) {
        try {
            String consultaSQL = "DELETE FROM persona WHERE cedula='" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("LA CLIENTE HA SIDO ELIMINADA");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
                ejecutar.close();
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public ArrayList<ClienteModelo> listaClientePersona(String cedula) {
        ArrayList<ClienteModelo> listaCliente = new ArrayList<>();

        try {
            String consultaSQL = "SELECT persona.cedula, "
                    + "persona.nombres, "
                    + "persona.apellidos, "
                    + "cliente.carnetPromocion "
                    + "FROM cliente "
                    + "JOIN persona ON cliente.idpersona = persona.idpersona "
                    + "WHERE persona.cedula = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                ClienteModelo cliente = new ClienteModelo();
                cliente.setCedula(resultado.getString("cedula"));
                cliente.setNombre(resultado.getString("nombres"));
                cliente.setApellido(resultado.getString("apellidos"));
                cliente.setDireccion(resultado.getString(""));
                cliente.setFechaNacimiento(resultado.getString(""));
                cliente.setTelefono(resultado.getNString(""));
                cliente.setCarnetPromocion(resultado.getBoolean("carnetPromocion"));
                listaCliente.add(cliente);
            }
            resultado.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaCliente;
    }

}
