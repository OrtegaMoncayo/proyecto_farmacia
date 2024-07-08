/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class ProveedorModelo extends PersonaModelo{
    private String contacto;

    public ProveedorModelo() {
    }

    public ProveedorModelo(String contacto, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento) {
        super(nombre, apellido, cedula, direccion, telefono, fechaNacimiento);
        this.contacto = contacto;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
      public String imprimir() {
        return "DATOS PERSONALES\n"
                + "NOMBRE;" + getNombre() + "\n"
                + "APELLIDO:" + getApellido() + "\n"
                + "CEDULA;" + getCedula() + "\n"
                + "DIRECCION:" + getDireccion() + "\n"
                + "FECHA DE NACIMIENTO;" + getFechaNacimiento() + "\n"
                + "TELEFONO:" + getTelefono() + "\n"
                + "CLAVE:" + getContacto() + "\n";
    }
}
