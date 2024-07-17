/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class ClienteModelo extends PersonaModelo{
    private boolean carnetPromocion;
    private int idPersona;

    public ClienteModelo() {
    }

    public ClienteModelo(boolean carnetPromocion, int idPersona) {
        this.carnetPromocion = carnetPromocion;
        this.idPersona = idPersona;
    }

    public ClienteModelo(boolean carnetPromocion, int idPersona, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento) {
        super(idPersona, nombre, apellido, cedula, direccion, telefono, fechaNacimiento);
        this.carnetPromocion = carnetPromocion;
        this.idPersona = idPersona;
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
