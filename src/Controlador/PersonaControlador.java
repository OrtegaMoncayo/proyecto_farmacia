/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.PersonaModelo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import controlador.ConexionBDD;
import java.sql.ResultSet;

/**
 *
 * @author 59397
 */
public class PersonaControlador {

    private PersonaModelo personaModelo;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearPersona(PersonaModelo pM) {
        try {
            String consultaSQL = "INSERT INTO persona"
                    + "(nombres,apellidos,cedula,direccion,fechaNacimiento,telefono,usuario,clave)"
                    + "VALUES "
                    + "('" + pM.getNombre() + "','" + pM.getApellido() + "',"
                    + "'" + pM.getCedula() + "','" + pM.getDireccion() + "',"
                    + "'" + pM.getFechaNacimiento() + "','" + pM.getTelefono() + "',"
                    + "'" + pM.getUsuario() + "'," + pM.getClave() + ");";
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

    public int buscarIdPersona(String cedula) {
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
    public int buscarClienteIdPersona(String cedula) {
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
    
       public void ActualisarPersona(String cedula, PersonaModelo pM){
          try {
              String consultaSQL = "UPDATE persona SET "
                      + "nombre ='"+pM.getNombre() +"', apellidos = '"+pM.getApellido()+"', "
                      + "direccion ='"+pM.getDireccion()+"', fechaNacimiento = '"+pM.getFechaNacimiento()+"',"
                      + " telefono = '"+pM.getTelefono()+"', usuario = '"+pM.getUsuario()+"',"
                      + " clave = '"+pM.getClave()+"'"
                      + " WHERE cedula = '"+cedula+"';";
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

    public String usuariContracena(String usuario, String clave) {
        try {
            String consultaSQL = "select nombres, apellidos FROM persona WHERE usuario='" + usuario + "' AND clave = '" + clave + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                String nombres = resultado.getString("nombres");
                String apellidos = resultado.getString("apellidos");
                return nombres + " " + apellidos + " ";

            } else {
                System.out.println("USUARIO Y CONTRASEÑA INCORRECTO");
            }
        } catch (Exception e) {
            System.out.println(""+e);
        }
        return null;
    }

}
