/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.PersonaControlador;
import Controlador.ProveedorControlador;
import Modelo.ClienteModelo;
import Modelo.PersonaModelo;
import Modelo.ProveedorModelo;
import java.util.Scanner;

/**
 *
 * @author 59397
 */
public class Main {

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        int i = 1;
        do {
            System.out.println("BIENVENIDO EN LA FARMACIA ECONOMIC");
            System.out.println("Elija la opción que Usted requiera ejecutar:\n"
                    + "1.Cliente\n"
                    + "2.Provedor\n"
                    + "3.Producto\n"
                    + "4.Pedidos\n"
                    + "5.Empleados\n"
                    + "0.Salir");
            int op1 = es.nextInt();
            if (op1 == 0) {
                System.out.println("Saliendo del programa...");
                break;
            }
            if (op1 == 1) {
                System.out.println("Elija el tipo de Opción\n"
                        + "1.Crear Cliente\n"
                        + "2.Buscar Cliente\n"
                        + "3.Realizar Pedido");
                int sm1 = es.nextInt();
                if (sm1 == 1 || sm1 == 2 || sm1 == 3) {
                    if (sm1 == 1) {
                        System.out.println("Ingrese los siguientes datos informativos");
                        PersonaModelo pM = new PersonaModelo();
                        System.out.println("Ingrese su Nombre:");
                        pM.setNombre(es.next());
                        System.out.println("Ingrese su Apellidos:");
                        pM.setApellido(es.next());
                        System.out.println("Ingrese su número de cédula:");
                        pM.setCedula(es.next());
                        System.out.println("Ingrese una Dirección:");
                        pM.setDireccion(es.next());
                        System.out.println("Ingrese su Fecha de Nacimiento:");
                        pM.setFechaNacimiento(es.next());
                        System.out.println("Ingrese un número Telefónico:");
                        pM.setTelefono(es.next());

                        PersonaControlador pC = new PersonaControlador();
                        pC.crearPersona(pM);

                        System.out.println("Ingrese los siguientes datos Cliente");
                        
                        int idPersona = pC.buscarIdPersona(pM.getCedula());
                        System.out.println("-------------------" + idPersona);
                        
                        
                        System.out.println("¿Tiene carnet de promoción? (true/false):");
                         boolean carnetPromocion=es.nextBoolean();
                         ClienteModelo cM=new ClienteModelo();
                        cM.setIdPersona(idPersona);

                    } else if (sm1 == 2) {

                    } else if (sm1 == 3) {

                    }
                }

            } else if (op1 == 2) {
                System.out.println("Elija el tipo de Opción\n"
                        + "1.Crear Proveedor\n"
                        + "2.Buscar Proveedor\n"
                        + "3.Realizar Pedido");
                int sm2 = es.nextInt();
                if (sm2 == 1 || sm2 == 2 || sm2 == 3) {
                    if (sm2 == 1) {
                        System.out.println("Ingrese los siguientes datos informativos");
                        PersonaModelo pM = new PersonaModelo();
                        System.out.println("Ingrese su Nombre:");
                        pM.setNombre(es.next());
                        System.out.println("Ingrese su Apellidos:");
                        pM.setApellido(es.next());
                        System.out.println("Ingrese su número de cédula:");
                        pM.setCedula(es.next());
                        System.out.println("Ingrese una Dirección:");
                        pM.setDireccion(es.next());
                        System.out.println("Ingrese su Fecha de Nacimiento:");
                        pM.setFechaNacimiento(es.next());
                        System.out.println("Ingrese un número Telefónico:");
                        pM.setTelefono(es.next());

                        PersonaControlador pC = new PersonaControlador();
                        pC.crearPersona(pM);

                        System.out.println("Ingrese los siguientes datos Proveedor");

                        int idPersona = pC.buscarIdPersona(pM.getCedula());
                        System.out.println("-------------------" + idPersona);

                        ProveedorModelo prM = new ProveedorModelo();
                        System.out.println("Ingrese el Contacto del Proveedor:");
                        prM.setContacto(es.next());
                        prM.setIdPersona(idPersona);

                        ProveedorControlador prC = new ProveedorControlador();
                        prC.crearPersona(prM);

                    } else if (sm2 == 2) {

                    } else if (sm2 == 3) {
                    }
                }

            } else if (op1 == 3) {
                System.out.println("Elija el tipo de Opción\n"
                        + "1.Crear Producto\n"
                        + "2.Buscar Producto\n"
                        + "3.Detalle del Producto");
                int sm3 = es.nextInt();
                if (sm3 == 1 || sm3 == 2 || sm3 == 3) {
                    if (sm3 == 1) {

                    } else if (sm3 == 2) {

                    } else if (sm3 == 3) {
                    }
                }

            } else if (op1 == 4) {
                System.out.println("Elija el tipo de Opción\n"
                        + "1.Crear Pedido\n"
                        + "2.Buscar Pedido Realizado\n"
                        + "3.Detalle Pedido");
                int sm4 = es.nextInt();
                if (sm4 == 1 || sm4 == 2 || sm4 == 3) {
                    if (sm4 == 1) {

                    } else if (sm4 == 2) {

                    } else if (sm4 == 3) {
                    }
                }

            } else if (op1 == 5) {
                System.out.println("Elija el tipo de Opción\n"
                        + "1.Crear Empleados\n"
                        + "2.Buscar Empleados\n");
                int sm5 = es.nextInt();
                if (sm5 == 1 || sm5 == 2) {
                    if (sm5 == 1) {
                        System.out.println("Ingrese los siguientes datos informativos");
                        PersonaModelo pM = new PersonaModelo();
                        System.out.println("Ingrese su Nombre:");
                        pM.setNombre(es.next());
                        System.out.println("Ingrese su Apellidos:");
                        pM.setApellido(es.next());
                        System.out.println("Ingrese su número de cédula:");
                        pM.setCedula(es.next());
                        System.out.println("Ingrese una Dirección:");
                        pM.setDireccion(es.next());
                        System.out.println("Ingrese su Fecha de Nacimiento:");
                        pM.setFechaNacimiento(es.next());
                        System.out.println("Ingrese un número Telefónico:");
                        pM.setTelefono(es.next());

                        PersonaControlador pC = new PersonaControlador();
                        pC.crearPersona(pM);

                        System.out.println("Ingrese los siguientes datos Proveedor");

                        int idPersona = pC.buscarIdPersona(pM.getCedula());
                        System.out.println("-------------------" + idPersona);

                    } else if (sm5 == 2) {

                    }

                }

            }

        } while (i == 1);
        es.close();
    }
}
