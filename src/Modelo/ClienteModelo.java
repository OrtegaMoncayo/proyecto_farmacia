/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class ClienteModelo extends PersonaModelo {

    private boolean carnetPromocion;
    private int idPersona;
    private int idCliente;

    public ClienteModelo() {
    }

    public ClienteModelo(boolean carnetPromocion, int idPersona, int idCliente) {
        this.carnetPromocion = carnetPromocion;
        this.idPersona = idPersona;
        this.idCliente = idCliente;
    }

    public ClienteModelo(boolean carnetPromocion, int idPersona, int idCliente, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento, String usuario, String clave) {
        super(idPersona, nombre, apellido, cedula, direccion, telefono, fechaNacimiento, usuario, clave);
        this.carnetPromocion = carnetPromocion;
        this.idPersona = idPersona;
        this.idCliente = idCliente;
    }

    public boolean getCarnetPromocion() {
        return carnetPromocion;
    }

    public void setCarnetPromocion(boolean carnetPromocion) {
        this.carnetPromocion = carnetPromocion;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String imprimir() {
        return "DATOS PERSONALES\n"
                + "NOMBRE;" + getNombre() + "\n"
                + "APELLIDO:" + getApellido() + "\n"
                + "CEDULA;" + getCedula() + "\n"
                + "DIRECCION:" + getDireccion() + "\n"
                + "FECHA DE NACIMIENTO;" + getFechaNacimiento() + "\n"
                + "TELEFONO:" + getTelefono() + "\n"
                + "CLAVE:" + getCarnetPromocion() + "\n";
    }

 
}
