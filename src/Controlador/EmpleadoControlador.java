/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.EmpleadoModelo;
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
public class EmpleadoControlador {

    private EmpleadoModelo empleadoModelo;

    ConexionBDD conexion = new ConexionBDD();
    Connection connection = (Connection) conexion.conectar();
    PreparedStatement ejecutar;
    ResultSet resultado;

    public void crearEmpleado(EmpleadoModelo eM) {
        try {
            String consultaSQL = "INSERT INTO empleado"
                    + "(salario, puesto, idpersona)"
                    + "VALUES "
                    + "('" + eM.getSalario() + "','" + eM.getPuesto() + "'," + eM.getIdPersona() + ");";
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

    public int buscarEmpleado(String cedula) {
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

    public void ActualisarEmpleado(String cedula, EmpleadoModelo eM, PersonaModelo pM) {
        try {
            String consultaSQL = "UPDATE empleado SET puesto ="
                    + "'" + eM.getPuesto() + "' and " + eM.getSalario() + " "
                    + "WHERE "
                    + "idpersona = (SELECT idpersona FROM persona WHERE cedula = "
                    + "'" + cedula + "')";
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

    public ArrayList<EmpleadoModelo> listaClientePersona() {
        ArrayList<EmpleadoModelo> listaEmpleado = new ArrayList<>();

        try {
            String consultaSQL = "SELECT persona.cedula,"
                    + " persona.nombres,"
                    + " persona.apellidos,"
                    + " persona.direccion,"
                    + " persona.telefono,"
                    + " empleado.puesto"
                    + "FROM empleado "
                    + "JOIN persona ON empleado.idpersona = persona.idpersona;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {
                EmpleadoModelo empleado = new EmpleadoModelo();
                empleado.setCedula(resultado.getString("Cedula"));
                empleado.setNombre(resultado.getString("Nombre"));
                empleado.setApellido(resultado.getString("Apellido:"));
                empleado.setPuesto(resultado.getString("Puesto de travajo:"));
                listaEmpleado.add(empleado);
            } else {
                System.out.println("ingrese una cedula valida ");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaEmpleado;
    }

}
