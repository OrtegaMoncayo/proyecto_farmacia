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
    private String nCarnetmedico;

    public ClienteModelo() {
    }

    public ClienteModelo(String nCarnetmedico, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento) {
        super(nombre, apellido, cedula, direccion, telefono, fechaNacimiento);
        this.nCarnetmedico = nCarnetmedico;
    }

    public String getnCarnetmedico() {
        return nCarnetmedico;
    }

    public void setnCarnetmedico(String nCarnetmedico) {
        this.nCarnetmedico = nCarnetmedico;
    }
    
      public String imprimir() {
        return "DATOS PERSONALES\n"
                + "NOMBRE;" + getNombre() + "\n"
                + "APELLIDO:" + getApellido() + "\n"
                + "CEDULA;" + getCedula() + "\n"
                + "DIRECCION:" + getDireccion() + "\n"
                + "FECHA DE NACIMIENTO;" + getFechaNacimiento() + "\n"
                + "TELEFONO:" + getTelefono() + "\n"
                + "CLAVE:" + getnCarnetmedico() + "\n";
    }
}
