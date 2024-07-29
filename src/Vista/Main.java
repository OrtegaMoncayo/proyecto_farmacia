/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ClienteControlador;
import Controlador.EmpleadoControlador;
import Controlador.PersonaControlador;
import Controlador.ProveedorControlador;
import Modelo.ClienteModelo;
import Modelo.EmpleadoModelo;
import Modelo.Pedidos;
import Modelo.PersonaModelo;
import Modelo.ProveedorModelo;
import java.util.ArrayList;
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
            System.out.println("SELECCIONE EL MODO EN EL QUE QUIERE ENTRAR\n"
                    + "1.Crear un Usuario\n"
                    + "2.Inicia Sesion\n"
                    + "0.Salir");
            int usu = es.nextInt();
            es.nextLine();
            if (usu == 1) {
                System.out.println("Ingrese los siguientes datos informativos");
                PersonaModelo pM = new PersonaModelo();
                System.out.print("Ingrese su Nombre: ");
                pM.setNombre(es.nextLine());
                System.out.print("Ingrese su Apellidos: ");
                pM.setApellido(es.nextLine());
                System.out.print("Ingrese su número de cédula: ");
                pM.setCedula(es.nextLine());
                System.out.print("Ingrese una Dirección: ");
                pM.setDireccion(es.nextLine());
                System.out.print("Ingrese su Fecha de Nacimiento: ");
                pM.setFechaNacimiento(es.nextLine());
                System.out.print("Ingrese un número Telefónico: ");
                pM.setTelefono(es.nextLine());
                System.out.print("Ingrese su Usuario: ");
                pM.setUsuario(es.nextLine());
                System.out.print("Ingrese un número de Clave: ");
                pM.setClave(es.nextLine());

                PersonaControlador pC = new PersonaControlador();
                pC.crearPersona(pM);

                System.out.println("Ingrese los siguientes datos Empleado");
                int idPersona = pC.buscarIdPersona(pM.getCedula());
                System.out.println("------------" + idPersona);

                EmpleadoModelo eM = new EmpleadoModelo();
                System.out.print("Ingrese el Puesto del Empleado: ");
                eM.setPuesto(es.next());
                System.out.print("Ingrese el Salario  del Empleado:(0,00) ");
                eM.setSalario(es.nextDouble());
                eM.setIdPersona(idPersona);

                EmpleadoControlador eC = new EmpleadoControlador();
                eC.crearEmpleado(eM);

            } else if (usu == 2) {
                boolean autenticado = false;
                do {
                    System.out.println("INGRESE USUARIO Y CONTRASEÑA");
                    System.out.print("Usuario: ");
                    String usuario = es.nextLine();
                    System.out.print("Contraseña: ");
                    String clave = es.nextLine();

                    PersonaControlador pC1 = new PersonaControlador();
                    String resultado = pC1.usuariContracena(usuario, clave);

                    if (resultado != null) {
                        System.out.println("Bienvenido " + resultado);
                        autenticado = true;
                    } else {
                        System.out.println("Usuario y/o contraseña incorrectos. Intente nuevamente.");
                    }
                } while (!autenticado);
                if (autenticado) {
                    do {
                        System.out.println("BIENVENIDO EN LA FARMACIA ECONOMIC");
                        System.out.println("SELECCIONE EL MODO EN EL QUE QUIERE ENTRAR\n"
                                + "1.Cliente\n"
                                + "2.Proveedor\n"
                                + "3.Pedidos\n"
                                + "4.Producto\n"
                                + "4.Empleado\n"
                                + "0.Salir");
                        int op = es.nextInt();
                        es.nextLine();
                        if (op == 0) {
                            System.out.println("Saliendo del programa...");
                            break;
                        }
                        if (op == 1) {
                            System.out.println("Elija el tipo de Opción\n"
                                    + "1.Crear Cliente\n"
                                    + "2.Actualizar Cliente\n"
                                    + "2.Buscar Cliente\n"
                                    + "3.Realizar Pedido\n");
                            int sm1 = es.nextInt();
                            es.nextLine();
                            if (sm1 == 1 || sm1 == 2 || sm1 == 3) {
                                if (sm1 == 1) {
                                    System.out.println("Ingrese los siguientes datos informativos");
                                    PersonaModelo pM = new PersonaModelo();
                                    System.out.print("Ingrese su Nombre: ");
                                    pM.setNombre(es.nextLine());
                                    System.out.print("Ingrese su Apellidos: ");
                                    pM.setApellido(es.nextLine());
                                    System.out.print("Ingrese su número de cédula: ");
                                    pM.setCedula(es.nextLine());
                                    System.out.print("Ingrese una Dirección: ");
                                    pM.setDireccion(es.nextLine());
                                    System.out.print("Ingrese su Fecha de Nacimiento: ");
                                    pM.setFechaNacimiento(es.nextLine());
                                    System.out.print("Ingrese un número Telefónico: ");
                                    pM.setTelefono(es.nextLine());

                                    PersonaControlador pC = new PersonaControlador();
                                    pC.crearPersona(pM);

                                    System.out.println("Ingrese los siguientes datos Cliente");
                                    ClienteModelo cM = new ClienteModelo();
                                    int idPersona = pC.buscarIdPersona(pM.getCedula());
                                    cM.setIdPersona(idPersona);

                                    int y = 0;
                                    do {
                                        System.out.print("¿Tiene carnet de promoción? (Si/No): ");
                                        String carnet = es.next();
                                        if (carnet.equalsIgnoreCase("si")) {
                                            cM.setCarnetPromocion(true);
                                            y = 1;
                                        } else if (carnet.equalsIgnoreCase("No")) {
                                            cM.setCarnetPromocion(false);
                                            y = 1;
                                        } else {
                                            System.out.println("Ingrese un valor correcto");
                                            y = 0;
                                        }
                                    } while (y == 0);

                                    ClienteControlador cC = new ClienteControlador();
                                    cC.crearCliente(cM);

                                } else if (sm1 == 2) {
                                    System.out.print("Ingrese la cédula del cliente: ");
                                    String cedula = es.next();

                                    ClienteControlador cC = new ClienteControlador();
                                    cC.buscarClienteIdCliente(cedula);
                                    ClienteModelo cM = new ClienteModelo();

                                    if (cM != null) {
                                        System.out.println("Información del Cliente:");
                                        System.out.println("Nombre: " + cM.getNombre());
                                        System.out.println("Apellidos: " + cM.getApellido());
                                        System.out.println("Cédula: " + cM.getCedula());
                                        System.out.println("Dirección: " + cM.getDireccion());
                                        System.out.println("Fecha de Nacimiento: " + cM.getFechaNacimiento());
                                        System.out.println("Teléfono: " + cM.getTelefono());
                                        System.out.println("Carnet de Promoción: " + (cM.getCarnetPromocion() ? "Sí" : "No"));
                                    } else {
                                        System.out.println("Cliente no encontrado.");
                                    }
                                } else if (sm1 == 3) {
                                    ClienteControlador cC=new ClienteControlador();
                                    ArrayList<ClienteModelo> listaCliente ;
                                }
                            } else if (op == 2) {
                                System.out.println("Elija el tipo de Opción\n"
                                        + "1.Crear Proveedor\n"
                                        + "2.Buscar Proveedor\n"
                                        + "3.Pedido realizado");
                                int sm2 = es.nextInt();
                                es.nextLine();
                                if (sm2 == 1 || sm2 == 2 || sm2 == 3) {
                                    if (sm2 == 1) {
                                        System.out.println("Ingrese los siguientes datos informativos");
                                        PersonaModelo pM = new PersonaModelo();
                                        System.out.print("Ingrese su Nombre: ");
                                        pM.setNombre(es.nextLine());
                                        System.out.print("Ingrese su Apellidos: ");
                                        pM.setApellido(es.nextLine());
                                        System.out.print("Ingrese su número de cédula: ");
                                        pM.setCedula(es.nextLine());
                                        System.out.print("Ingrese una Dirección: ");
                                        pM.setDireccion(es.nextLine());
                                        System.out.print("Ingrese su Fecha de Nacimiento: ");
                                        pM.setFechaNacimiento(es.nextLine());
                                        System.out.print("Ingrese un número Telefónico: ");
                                        pM.setTelefono(es.nextLine());

                                        PersonaControlador pC = new PersonaControlador();
                                        pC.crearPersona(pM);

                                        System.out.println("Ingrese los siguientes datos Proveedor");
                                        int idPersona = pC.buscarIdPersona(pM.getCedula());

                                        ProveedorModelo prM = new ProveedorModelo();
                                        System.out.print("Ingrese el Contacto del Proveedor: ");
                                        prM.setContacto(es.nextLine());
                                        prM.setIdPersona(idPersona);

                                        ProveedorControlador prC = new ProveedorControlador();
                                        prC.crearProveedor(prM);

                                    } else if (sm2 == 2) {
                                        // Lógica para buscar proveedor
                                    } else if (sm2 == 3) {
                                        // Lógica para realizar pedido
                                    }
                                } else if (op == 3) {
                                    System.out.println("Elija el tipo de Opción\n"
                                            + "1.Buscar Pedido Realizado\n"
                                            + "2.Detalle Pedido");
                                    int sm4 = es.nextInt();
                                    es.nextLine();
                                    if (sm4 == 1 || sm4 == 2) {
                                        if (sm4 == 1) {
                                            // Lógica para crear pedido
                                        } else if (sm4 == 2) {
                                            // Lógica para buscar pedido realizado
                                        }
                                    } else if (op == 4) {
                                        System.out.println("Elija el tipo de Opción\n"
                                                + "1.Crear Producto\n"
                                                + "2.Buscar Producto\n"
                                                + "3.Detalle del Producto");
                                        int sm3 = es.nextInt();
                                        es.nextLine();
                                        if (sm3 == 1 || sm3 == 2 || sm3 == 3) {
                                            if (sm3 == 1) {
                                                // Lógica para crear producto
                                            } else if (sm3 == 2) {
                                                // Lógica para buscar producto
                                            } else if (sm3 == 3) {
                                                // Lógica para detalle del producto
                                            }
                                        } else if (op == 5) {
                                            System.out.println("Elija el tipo de Opción\n"
                                                    + "1.Crear Empleados\n"
                                                    + "2.Buscar Empleados\n"
                                                    + "2.inventario\n"
                                                    + "2.Pedido de entrega\n"
                                                    + "2.crear producto\n");
                                            int sm5 = es.nextInt();
                                            es.nextLine();
                                            if (sm5 == 1 || sm5 == 2) {
                                                if (sm5 == 1) {
                                                    System.out.println("Ingrese los siguientes datos informativos");
                                                    PersonaModelo pM = new PersonaModelo();
                                                    System.out.print("Ingrese su Nombre: ");
                                                    pM.setNombre(es.nextLine());
                                                    System.out.print("Ingrese su Apellidos: ");
                                                    pM.setApellido(es.nextLine());
                                                    System.out.print("Ingrese su número de cédula: ");
                                                    pM.setCedula(es.nextLine());
                                                    System.out.print("Ingrese una Dirección: ");
                                                    pM.setDireccion(es.nextLine());
                                                    System.out.print("Ingrese su Fecha de Nacimiento: ");
                                                    pM.setFechaNacimiento(es.nextLine());
                                                    System.out.print("Ingrese un número Telefónico: ");
                                                    pM.setTelefono(es.nextLine());
                                                    System.out.print("Ingrese su Usuario: ");
                                                    pM.setUsuario(es.nextLine());
                                                    System.out.print("Ingrese un número de Clave: ");
                                                    pM.setClave(es.nextLine());

                                                    PersonaControlador pC = new PersonaControlador();
                                                    pC.crearPersona(pM);

                                                    System.out.println("Ingrese los siguientes datos Empleado");
                                                    int idPersona = pC.buscarIdPersona(pM.getCedula());

                                                    EmpleadoModelo eM = new EmpleadoModelo();
                                                    System.out.print("Ingrese el Puesto del Empleado: ");
                                                    eM.setPuesto(es.next());
                                                    System.out.print("Ingrese el Salario del Empleado: ");
                                                    eM.setSalario(es.nextDouble());
                                                    eM.setIdPersona(idPersona);

                                                    EmpleadoControlador eC = new EmpleadoControlador();
                                                    eC.crearEmpleado(eM);

                                                } else if (sm5 == 2) {
                                                    // Lógica para buscar empleados
                                                }
                                            }
                                        }

                                    }

                                }

                            }

                        }

                    } while (autenticado);
                    es.close();

                }

            } else if (usu == 0) {
                System.out.println("Saliendo del programa...");
                i = 0;
            }
        } while (i == 0);
        es.close();

    }
}
