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

    public EmpleadoModelo() {
    }

    public EmpleadoModelo(double salario, String puesto, String nombre, String apellido, String cedula, String direccion, String telefono, String fechaNacimiento) {
        super(nombre, apellido, cedula, direccion, telefono, fechaNacimiento);
        this.salario = salario;
        this.puesto = puesto;
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
