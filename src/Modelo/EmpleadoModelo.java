/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 59397
 */
public class EmpleadoModelo extends PersonaModelo{
    private double salario;
    private String puesto;
    private int idPersona;

    public EmpleadoModelo() {
    }

    public EmpleadoModelo(double salario, String puesto, int idPersona) {
        this.salario = salario;
        this.puesto = puesto;
        this.idPersona = idPersona;
    }

    public EmpleadoModelo(double salario, String puesto,int idPersona, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento) {
        super(idPersona, nombre, apellido, cedula, direccion, telefono, fechaNacimiento);
        this.salario = salario;
        this.puesto = puesto;
        this.idPersona = idPersona;
    }

   
    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
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
                + "PUESTO:" + getPuesto() + "\n"
                + "SALARIO:" + getSalario() + "\n";
    }
    
}
