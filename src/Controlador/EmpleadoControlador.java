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
import java.sql.SQLException;
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

        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
    }

    public int buscarIdPersonaEmpleado(String cedula) {
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

    public EmpleadoModelo buscarDatosCliente(String cedula) {
        EmpleadoModelo eM = new EmpleadoModelo();
        try {
            String consultaSQL = "SELECT * FROM persona p "
                    + "JOIN empleado e ON p.idpersona = e.idpersona "
                    + "WHERE p.cedula = '" + cedula + "';";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            resultado = ejecutar.executeQuery();
            if (resultado.next()) {

                eM.setNombre(resultado.getString("nombres"));
                eM.setApellido(resultado.getString("apellidos"));
                eM.setCedula(resultado.getString("cedula"));
                eM.setDireccion(resultado.getString("direccion"));
                eM.setFechaNacimiento(resultado.getString("fechaNacimiento"));
                eM.setTelefono(resultado.getString("telefono"));
                eM.setPuesto(resultado.getString("puesto"));
                eM.setSalario(resultado.getDouble("salario"));

                eM.imprimir();
                resultado.close();
                return eM;
            } else {
                System.out.println("Ingrese una cédula válida");
                resultado.close();
            }
        } catch (SQLException e) {
            System.out.println("Comuníquese con el administrador" + e);
        }
        return eM;

    }

    public void ActualisarEmpleado(String cedula, EmpleadoModelo eM, PersonaModelo pM) {
        try {
       
        String consultaSQLPersona = "UPDATE persona SET cedula = ?, nombres = ?, apellidos = ?, direccion = ?, fechaNacimiento = ?, telefono = ? WHERE cedula = ?";
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
            System.out.println("LOS DATOS DEL EMPLEADO HAN SIDO ACTUALIZADOS CON ÉXITO");

            
            String consultaSQL = "UPDATE empleado SET puesto = ?, salario = ? WHERE idpersona = (SELECT idpersona FROM persona WHERE cedula = ?)";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, eM.getPuesto());
            ejecutar.setDouble(2, eM.getSalario());
            ejecutar.setString(3, cedula);
            
            int res = ejecutar.executeUpdate();
            
            if (res > 0) {
                System.out.println("EL PUESTO Y EL SALARIO HA SIDO ACTUALIZADO CON ÉXITO");
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

    public void eliminarEmpleado(String cedula) {
        try {
            String consultaSQL = "DELETE FROM persona WHERE cedula='" + cedula + "'";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            int res = ejecutar.executeUpdate();
            if (res > 0) {
                System.out.println("EL EMPLEADO HA SIDO ELIMINADA");
                ejecutar.close();
            } else {
                System.out.println("ERROR AL ELIMINAR PERSONA");
            }
        } catch (SQLException e) {
            System.out.println("CORRA EL FIN DEL MUNDO ESTA CERCA");
        }

    }

    public ArrayList<EmpleadoModelo> listaEmpleadoPersona(String cedula) {
        ArrayList<EmpleadoModelo> listaEmpleado = new ArrayList<>();

        try {
            String consultaSQL = "SELECT persona.cedula, "
                    + "persona.nombres, "
                    + "persona.apellidos, "
                    + "persona.direccion, "
                    + "persona.telefono, "
                    + "empleado.puesto "
                    + "FROM empleado "
                    + "JOIN persona ON empleado.idpersona = persona.idpersona "
                    + "WHERE persona.cedula = ?;";
            ejecutar = (PreparedStatement) connection.prepareCall(consultaSQL);
            ejecutar.setString(1, cedula);
            resultado = ejecutar.executeQuery();
            while (resultado.next()) {
                EmpleadoModelo empleado = new EmpleadoModelo();
                empleado.setCedula(resultado.getString("cedula"));
                empleado.setNombre(resultado.getString("nombres"));
                empleado.setApellido(resultado.getString("apellidos"));
                empleado.setDireccion(resultado.getString("direccion"));
                empleado.setTelefono(resultado.getString("telefono"));
                empleado.setPuesto(resultado.getString("puesto"));
                listaEmpleado.add(empleado);

            }
            resultado.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de clientes: " + e);

        }

        return listaEmpleado;
    }

}
