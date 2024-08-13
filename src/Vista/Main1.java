/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Controlador.ClearConsola;
import Controlador.ClienteControlador;
import Controlador.DetalleProductoControlador;
import Controlador.EmpleadoControlador;
import Controlador.PedidoControlador;
import Controlador.PersonaControlador;
import Controlador.ProductoControlador;
import Controlador.ProveedorControlador;
import Modelo.ClienteModelo;
import Modelo.DetalleProducto;
import Modelo.EmpleadoModelo;
import Modelo.Pedidos;
import Modelo.PersonaModelo;
import Modelo.Producto;
import Modelo.ProveedorModelo;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author 59397
 */
public class Main1 {

    public static void main(String[] args) {
        Scanner es = new Scanner(System.in);
        int i = 1;
        do {

//---------------------------------------------------------------------------------------------------------------------------------------------------------
            System.out.println("█▄▄ █ █▀▀ █▄░█ █░█ █▀▀ █▄░█ █ █▀▄ █▀█");
            System.out.println("█▄█ █ ██▄ █░▀█ ▀▄▀ ██▄ █░▀█ █ █▄▀ █▄█");
            System.out.println("****************************************");
            System.out.println("*                                      *");
            System.out.println("*       LA FARMACIA ECONOMIC           *");
            System.out.println("*                                      *");
            System.out.println("*        ____                          *");
            System.out.println("*       /    \\ ____  ____  ____       *");
            System.out.println("*      /      \\    \\/    \\/   \\    *");
            System.out.println("*     /        \\              \\      *");
            System.out.println("*    /          \\              \\     *");
            System.out.println("*   /____________\\______________\\    *");
            System.out.println("*                                      *");
            System.out.println("*  Su Salud y Bienestar, Nuestra       *");
            System.out.println("*           Prioridad                  *");
            System.out.println("*                                      *");
            System.out.println("****************************************");

//-------------------------------------------------------------------------------------------------------------------------------------------------------
            System.out.println("*------------------------------------------------*");
            System.out.println("|  SELECCIONE EL MODO EN EL QUE QUIERE ENTRAR\n  |");
            System.out.println("|------------------------------------------------|");
            System.out.println("|1.Crear un Usuario                              |");
            System.out.println("|2.Inicia Sesion                                 |");
            System.out.println("|0.Salir                                         |");
            System.out.println("*------------------------------------------------*");
            int usu = es.nextInt();
            es.nextLine();
            
//---------------------------------------------------------------------------------------------------------------------------------------------------------
            if (usu == 1) {

                System.out.println("*******************************************");
                System.out.println("*                                         *");
                System.out.println("*         CREACIÓN DE NUEVO USUARIO       *");
                System.out.println("*                                         *");
                System.out.println("*          ____                           *");
                System.out.println("*         /    \\                         *");
                System.out.println("*        /      \\                        *");
                System.out.println("*       /        \\                       *");
                System.out.println("*      /          \\                      *");
                System.out.println("*     /____________\\                     *");
                System.out.println("*                                         *");
                System.out.println("*******************************************");
                System.out.println("                                           ");
                System.out.println("*-----------------------------------------*");
                System.out.println("|Ingrese los siguientes datos informativos|");
                System.out.println("*-----------------------------------------*");

//--------------------------------------------CREAR USUARIO/EMPLEADO------------------------------------------------------------------------------------
                PersonaModelo pM = new PersonaModelo();
                System.out.print("Ingrese su Nombre: ");
                pM.setNombre(es.nextLine());
                System.out.print("Ingrese su Apellidos: ");
                pM.setApellido(es.nextLine());
                System.out.print("Ingrese su número de cédula: ");
                pM.setCedula(es.nextLine());
                System.out.print("Ingrese una Dirección: ");
                pM.setDireccion(es.nextLine());
                System.out.print("Ingrese su Fecha de Nacimiento(DD-MM-AAAA): ");
                pM.setFechaNacimiento(es.nextLine());
                System.out.print("Ingrese un número Telefónico: ");
                pM.setTelefono(es.nextLine());
                System.out.print("Ingrese su Usuario: ");
                pM.setUsuario(es.nextLine());
                System.out.print("Ingrese un número de Clave: ");
                pM.setClave(es.nextLine());

                PersonaControlador pC = new PersonaControlador();
                pC.crearPersona(pM, true);

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
                
//--------------------------------------------------------INICIO DE SESIÓN------------------------------------------------------------------------------------------
            } else if (usu == 2) {

                System.out.println("****************************************");
                System.out.println("*                                      *");
                System.out.println("*           INICIAR SESIÓN             *");
                System.out.println("*                                      *");
                System.out.println("*         __________________           *");
                System.out.println("*        |                  |          *");
                System.out.println("*        |   Usuario:       |          *");
                System.out.println("*        |                  |          *");
                System.out.println("*        |   Contraseña:    |          *");
                System.out.println("*        |__________________|          *");
                System.out.println("*                                      *");
                System.out.println("****************************************");

//----------------------------------------AUTENTIFICADOR DEL USUARIO/EMPLEADO------------------------------------------------------------------------------
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
                    int au = 0;
                    do {
                        System.out.println("****************************************");
                        System.out.println("*                                      *");
                        System.out.println("*   MENÚ PRINCIPAL - FARMACIA ECONOMIC *");
                        System.out.println("*                                      *");
                        System.out.println("* 1. Cliente                           *");
                        System.out.println("* 2. Proveedor                         *");
                        System.out.println("* 3. Pedidos                           *");
                        System.out.println("* 4. Producto                          *");
                        System.out.println("* 5. Empleado                          *");
                        System.out.println("* 0. Salir                             *");
                        System.out.println("*                                      *");
                        System.out.println("****************************************");

                        int op = es.nextInt();
                        es.nextLine();

//-------------------------------------------LINEA DE SALIR DEL PROGRAMA-----------------------------------------------------------------------------------
                        
                        if (op == 1) {
                            int j = 1;
                            do {
                                System.out.println("*------------------------------*");
                                System.out.println("*  Elija el tipo de Opción     *");
                                System.out.println("*------------------------------*");
                                System.out.println("*1.Crear Cliente               *");
                                System.out.println("*2.Actualizar Cliente          *");
                                System.out.println("*3.Buscar Cliente              *");
                                System.out.println("*4.Eliminar Cliente            *");
                                System.out.println("*5.Realizar Pedido             *");
                                System.out.println("*0.volver el menu principal    *");
                                System.out.println("*------------------------------*");
                                int sm1 = es.nextInt();
                                es.nextLine();
//--------------------------------------------------------CREAR CLIENTE-------------------------------------------------------------------------------------------------
                                if (sm1 == 1 || sm1 == 2 || sm1 == 3 || sm1 == 4 || sm1 == 5) {

                                    if (sm1 == 1) {

                                        System.out.println("****************************************");
                                        System.out.println("*                                      *");
                                        System.out.println("*       CREACIÓN DE NUEVO CLIENTE      *");
                                        System.out.println("*                                      *");
                                        System.out.println("*        ____                          *");
                                        System.out.println("*       /    \\                        *");
                                        System.out.println("*      /      \\                       *");
                                        System.out.println("*     /        \\                      *");
                                        System.out.println("*    /          \\                     *");
                                        System.out.println("*   /____________\\                    *");
                                        System.out.println("*                                      *");
                                        System.out.println("****************************************");

                                        //CREAR PERSONA
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
                                        System.out.print("Ingrese su Fecha de Nacimiento(AAAA/MM/DD): ");
                                        pM.setFechaNacimiento(es.nextLine());
                                        System.out.print("Ingrese un número Telefónico: ");
                                        pM.setTelefono(es.nextLine());

                                        PersonaControlador pC = new PersonaControlador();
                                        pC.crearPersona(pM, false);

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

//---------------------------------------------------------ACTILIZAR CLIENTE-------------------------------------------------------------------------------------------------
                                    } else if (sm1 == 2) {
                                        System.out.println("Actulizar el cliente");

                                        System.out.print("Ingrese la cédula del cliente: ");
                                        String cedula = es.next();

                                        ClienteControlador cC = new ClienteControlador();

                                        ClienteModelo cM1 = cC.buscarDatosCliente(cedula);
                                        System.out.println(cM1.imprimir());

                                        System.out.println("Actualizar los datos del cliente");
                                        PersonaModelo pM = new PersonaModelo();

                                        System.out.println("Ingrese el nuevo Nombre:");
                                        pM.setNombre(es.next());
                                        System.out.println("Ingrese el nuevo Apellido:");
                                        pM.setApellido(es.next());
                                        System.out.println("Ingrese la nueva Dirección:");
                                        pM.setDireccion(es.next());
                                        System.out.print("Ingrese su Fecha de Nacimiento(AAAA-MM-DD): ");
                                        pM.setFechaNacimiento(es.next());
                                        System.out.println("Ingrese el nuevo Teléfono:");
                                        pM.setTelefono(es.next());

                                        System.out.println("Actulizar el carnet de promoción");
                                        ClienteModelo cM = new ClienteModelo();
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

                                        cC.ActualizarCliente(cedula, cM, pM);

//-----------------------------------------------------------BUSCAR CLIENTE------------------------------------------------------------------------------------------------
                                    } else if (sm1 == 3) {

                                        System.out.println("INGRESE LA CÉDULA");
                                        String cedula = es.next();
                                        ClienteControlador cC = new ClienteControlador();
                                        ClienteModelo cM = cC.buscarDatosCliente(cedula);
                                        System.out.println(cM.imprimir());

//---------------------------------------------------------ELIMINAR CLIENTE-------------------------------------------------------------------------------------------
                                    } else if (sm1 == 4) {

                                        System.out.println("INGRESE LA CÉDULA");
                                        String cedula = es.next();
                                        ClienteControlador cC = new ClienteControlador();
                                        cC.eliminarCliente(cedula);

//--------------------------------------------------------REALIZAR PEDIDO---------------------------------------------------------------------------------------------
                                    } else if (sm1 == 5) {
//                                    Buscar Cliente
                                        System.out.println("Ingrese los siguientes datos informativos");
                                        System.out.println("Ingrese la cédula");
                                        String cedula = es.next();

                                        ClienteControlador cC = new ClienteControlador();
                                        ClienteModelo cM = cC.buscarDatosCliente(cedula);
                                        System.out.println("Ingrese los siguientes datos Proveedor");
                                        int idCliente = cC.buscarIdCliente(cedula);

                                        if (cM == null) {
                                            System.out.println("Cliente no encontrado. Ingrese una cédula válida.");
                                            return;
                                        }

                                        System.out.println(cM.imprimir());

                                        // CREAR PEDIDO
                                        System.out.println("Ingrese los siguientes datos");
                                        Pedidos p = new Pedidos();
                                        p.setIdCliente(idCliente);

                                        System.out.println("Ingrese la Fecha de Pedido (YYYY-MM-DD):");
                                        p.setFechaPedido(es.next());
                                        System.out.println("Ingrese el Total del Pedido:");
                                        p.setTotal(es.nextDouble());

                                        PedidoControlador peC = new PedidoControlador();
                                        peC.crearPedido(p);

                                        int idPedido = peC.buscarIdPedido(cedula);
                                        if (idPedido == 0) {
                                            System.out.println("No se pudo crear el pedido.");
                                            return;
                                        }
                                        System.out.println("ID del pedido: " + idPedido);

                                        // BUSCANDO PRODUCTO
                                        System.out.print("Ingrese el nombre del producto:\n");
                                        String nombreProducto = es.next();
                                        es.nextLine();
                                        System.out.println("Ingrese el código del producto:\n");
                                        int codigo = es.nextInt();
                                        es.nextLine();

                                        ProductoControlador prC = new ProductoControlador();
                                        Producto pr = prC.buscarDatosProducto(nombreProducto, codigo);
                                        int idProducto = prC.buscarIdProducto(codigo);

                                        System.out.println(pr.imprimir());
                                        System.out.println("ID del producto: " + idProducto);

                                        // CREAR DETALLE PEDIDO
                                        DetalleProducto dp = new DetalleProducto();
                                        dp.setIdProducto(idProducto);
                                        dp.setIdPedido(idPedido);

                                        System.out.println("Cantidad del producto:");
                                        dp.setCantidad(es.nextInt());
                                        System.out.println("Precio Unitario del producto:");
                                        dp.setPrecioUnitario(es.nextDouble());

                                        DetalleProductoControlador dpC = new DetalleProductoControlador();
                                        dpC.crearDetalleProducto(dp);

                                        System.out.println("Detalle del producto creado exitosamente.");

                                    } else {
                                        System.out.println("Ingrese un valor correcto");
                                        j = 0;
                                    }
                                }
                            } while (j == 0);

                        } else if (op == 2) {
                            int a = 1;
                            do {
                                System.out.println("*-----------------------------*");
                                System.out.println("|        MENU DE PROVEDOR     |");
                                System.out.println("|-----------------------------|");
                                System.out.println("|1.Crear Proveedor            |");
                                System.out.println("|2.Actualizar Proveedor       |");
                                System.out.println("|3.Buscar Proveedor           |");
                                System.out.println("|4.Lista Proveedor            |");
                                System.out.println("|5.Eliminar Proveedor         |");
                                System.out.println("*-----------------------------*");
                                int sm2 = es.nextInt();
                                es.nextLine();
//-----------------------------------------------------CREAR PROVEEDOR------------------------------------------------------------------------------------------------                            
                                if (sm2 == 1 || sm2 == 2 || sm2 == 3) {
                                    if (sm2 == 1) {
                                        System.out.println("******************************************");
                                        System.out.println("*                                        *");
                                        System.out.println("*        CREACIÓN DE NUEVO PROVEEDOR     *");
                                        System.out.println("*                                        *");
                                        System.out.println("*         ____                           *");
                                        System.out.println("*        / __ \\                          *");
                                        System.out.println("*       / /  \\ \\                         *");
                                        System.out.println("*      / /    \\ \\                        *");
                                        System.out.println("*     / /      \\ \\                       *");
                                        System.out.println("*    /_/        \\_\\                      *");
                                        System.out.println("*                                        *");
                                        System.out.println("******************************************");
                                        System.out.println("Ingrese los siguientes datos informativos");
                                        //CREAR PERSONA
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
                                        System.out.print("Ingrese su Fecha de Nacimiento(AAAA/MM/DD): ");
                                        pM.setFechaNacimiento(es.nextLine());
                                        System.out.print("Ingrese un número Telefónico: ");
                                        pM.setTelefono(es.nextLine());

                                        PersonaControlador pC = new PersonaControlador();
                                        pC.crearPersona(pM, false);

                                        System.out.println("Ingrese los siguientes datos Proveedor");
                                        int idPersona = pC.buscarIdPersona(pM.getCedula());

                                        ProveedorModelo prM = new ProveedorModelo();
                                        System.out.print("Ingrese el Contacto del Proveedor: ");
                                        prM.setContacto(es.nextLine());
                                        prM.setIdPersona(idPersona);

                                        ProveedorControlador prC = new ProveedorControlador();
                                        prC.crearProveedor(prM);

//----------------------------------------------------------ACTULIZAR PROVEEDOR------------------------------------------------------------------------------------------
                                    } else if (sm2 == 2) {

                                        System.out.println("INGRESE LA CEDULA");
                                        String cedula = es.next();

                                        ProveedorControlador prC = new ProveedorControlador();

                                        ProveedorModelo prM1 = prC.buscarDatosProveedor(cedula);
                                        System.out.println(prM1.imprimir());

                                        System.out.println("Actualizar los datos del cliente");
                                        PersonaModelo pM = new PersonaModelo();

                                        System.out.println("Ingrese el nuevo Nombre:");
                                        pM.setNombre(es.next());
                                        System.out.println("Ingrese el nuevo Apellido:");
                                        pM.setApellido(es.next());
                                        System.out.println("Ingrese el nuevo Cedula:");
                                        pM.setCedula(es.next());
                                        System.out.println("Ingrese el nuevo Direccion:");
                                        pM.setDireccion(es.next());
                                        System.out.println("Ingrese el nuevo Fecha de Nacimiento (AAAA/MM/DD):");
                                        pM.setFechaNacimiento(es.next());
                                        System.out.println("Ingrese el nuevo Telefono:");
                                        pM.setTelefono(es.next());

                                        ProveedorModelo prM = new ProveedorModelo();
                                        System.out.println("Ingrese el nuevo Contacto");
                                        prM.setContacto(es.next());

                                        prC.ActualisarProveedor(cedula, prM, pM);

//--------------------------------------------------------BUSCAR PROVEDOR-----------------------------------------------------------------------------------------------
                                    } else if (sm2 == 3) {

                                        System.out.println("INGRESE LA CEDULA");
                                        String cedula = es.next();

                                        ProveedorControlador pC = new ProveedorControlador();
                                        ProveedorModelo p = pC.buscarDatosProveedor(cedula);
                                        System.out.println(p.imprimir());

//--------------------------------------------------------LISTA DE PROVEEDOR------------------------------------------------------------------------------------------------
                                    } else if (sm2 == 4) {
                                        System.out.println("Lista de Provedor");

                                        ProveedorControlador pC = new ProveedorControlador();
                                        ArrayList<ProveedorModelo> listaProveedor = pC.listaProveedorPersona();

                                        for (ProveedorModelo l : listaProveedor) {
                                            System.out.println(l.imprimir());
                                        }

//--------------------------------------------------------ELIMINAR PROVEEDOR--------------------------------------------------------------------------------------------------                                    
                                    } else if (sm2 == 5) {
                                        System.out.println("INGRESE LA CÉDULA");
                                        String cedula = es.next();
                                        ProveedorControlador prC = new ProveedorControlador();
                                        prC.eliminarProveedor(cedula);

                                    }
                                } else {
                                    System.out.println("Ingrese un valor correcto");
                                    a = 0;
                                }
                            } while (a == 0);
                        } else if (op == 3) {
                            int e = 1;
                            do {
                                System.out.println("*------------------------------*");
                                System.out.println("|Elija el tipo de Opción       |");
                                System.out.println("|------------------------------|");
                                System.out.println("|1.Buscar Pedido Realizado     |");
                                System.out.println("|2.Lista Detalle Pedido        |");
                                System.out.println("|3.Eliminar Pedido             |");
                                System.out.println("*------------------------------*");
                                int sm3 = es.nextInt();
                                es.nextLine();
//----------------------------------------------------BUSCAR PEDIDO REALIZADO-----------------------------------------------------------------------------------------
                                if (sm3 == 1 || sm3 == 2 || sm3 == 3) {

                                    if (sm3 == 1) {

                                        System.out.println("INGRESE LA CEDULA");
                                        String cedula = es.next();
                                        PedidoControlador pC = new PedidoControlador();
                                        ArrayList<Pedidos> listaPedido = pC.listaPedidos(cedula);
                                        for (Pedidos l : listaPedido) {
                                            System.out.println(l.imprimirDetallePedido());
                                        }
//----------------------------------------------------LISTA DE PEDIDOS--------------------------------------------------------------------------------------------------
                                    } else if (sm3 == 2) {

                                        PedidoControlador pC = new PedidoControlador();
                                        ArrayList<Pedidos> listaPedido = pC.listaPedidos();
                                        for (Pedidos l : listaPedido) {
                                            System.out.println(l.imprimirDetallePedidoProducto());
                                        }
//------------------------------------------------------ELIMINAR PEDIDOS------------------------------------------------------------------------------------------------
                                    } else if (sm3 == 2) {

                                        System.out.println("INGRESE LA CÉDULA");
                                        String cedula = es.next();
                                        System.out.println("INGRESE EL NOMBRE DEL PRODUCTO");
                                        String nombreProducto = es.next();
                                        PedidoControlador pe = new PedidoControlador();
                                        pe.eliminarPedido(cedula, nombreProducto);
                                    }
                                } else {
                                    System.out.println("Ingrese un valor correcto");
                                    e = 0;
                                }
                            } while (e == 0);
                        } else if (op == 4) {
                            int u = 1;
                            do {
                                System.out.println("*------------------------------*");
                                System.out.println("|Elija el tipo de Opción       |");
                                System.out.println("|------------------------------|");
                                System.out.println("|1.Crear Producto              |");
                                System.out.println("|2.Actulizar Producto          |");
                                System.out.println("|3.Buscar Producto             |");
                                System.out.println("|4.inventario                  |");
                                System.out.println("|5.Elimina Producto            |");
                                System.out.println("*------------------------------*");
                                int sm4 = es.nextInt();
                                es.nextLine();
//------------------------------------------------------CREAR PRODUCTO---------------------------------------------------------------------------------------------
                                if (sm4 == 1 || sm4 == 2 || sm4 == 3 || sm4 == 4 || sm4 == 5) {

                                    if (sm4 == 1) {

                                        Producto pr = new Producto();
                                        System.out.print("Ingrese su Nombre de Producto: ");
                                        pr.setNombreProducto(es.nextLine());
                                        System.out.print("Ingrese su Stock: ");
                                        pr.setStock(es.nextInt());
                                        es.nextLine();
                                        System.out.print("Ingrese su Precio: ");
                                        pr.setPrecio(es.nextDouble());
                                        es.nextLine();
                                        System.out.print("Ingrese su Marca: ");
                                        pr.setMarca(es.nextLine());
                                        System.out.print("Ingrese su Codigo: ");
                                        pr.setCodigo(es.nextInt());
                                        es.nextLine();

                                        ProductoControlador prC = new ProductoControlador();
                                        prC.crearProducto(pr);
//------------------------------------------------------ACTULIZAR PRODUCTO-------------------------------------------------------------------------------------------------
                                    } else if (sm4 == 2) {
                                        System.out.println("Ingrese el Nombre del Producto:");
                                        String nombreProducto = es.next();
                                        es.nextLine();
                                        System.out.println("Ingrese el Código del Producto:");
                                        int codigo = es.nextInt();

                                        ProductoControlador prC = new ProductoControlador();
                                        Producto pr1 = prC.buscarDatosProducto(nombreProducto, codigo);
                                        System.out.println(pr1.imprimir());

                                        System.out.println("Actualizar los datos del producto");
                                        Producto pr = new Producto();

                                        System.out.print("Ingrese el nuevo nombre del producto: ");
                                        pr.setNombreProducto(es.nextLine());
                                        es.nextLine();
                                        System.out.print("Ingrese la nueva marca del producto: ");
                                        pr.setMarca(es.nextLine());
                                        System.out.print("Ingrese el nuevo precio del producto: ");
                                        pr.setPrecio(es.nextDouble());
                                        es.nextLine();
                                        System.out.print("Ingrese el nuevo stock del producto: ");
                                        pr.setStock(es.nextInt());
                                        es.nextLine();
                                        prC.ActualisarProducto(codigo, nombreProducto, pr);
//---------------------------------------------------------BUSCAR PRODUCTO-----------------------------------------------------------------------------------------------
                                    } else if (sm4 == 3) {

                                        System.out.println("Ingrese el Código del Producto:");
                                        int codigo = es.nextInt();

                                        ProductoControlador pC = new ProductoControlador();
                                        ArrayList<Producto> listaProductos = pC.listarProductosConStocksuperio(codigo);
                                        for (Producto l : listaProductos) {
                                            System.out.println(l.imprimirDetalleProducto());

                                        }
                                        pC.stockMinimo(15);
//-------------------------------------------------------LISTA DE PRODUCTO---------------------------------------------------------------------------------------------------
                                    } else if (sm4 == 4) {
                                        ProductoControlador pC = new ProductoControlador();
                                        ArrayList<Producto> listaProductos = pC.listaProducto();
                                        for (Producto l : listaProductos) {
                                            System.out.println(l.imprimirDetalleProducto());
                                        }
                                        pC.stockMinimo(15);
//-------------------------------------------------------ELIMINAR PRODUCTO---------------------------------------------------------------------------------------------------                                    
                                    } else if (sm4 == 5) {
                                        System.out.println("Ingrese el código del producto:");
                                        String nombreProducto = es.next();
                                        es.nextLine();
                                        System.out.println("Ingrese el código del producto:");
                                        int codigo = es.nextInt();
                                        ProductoControlador pC = new ProductoControlador();
                                        pC.eliminarProducto(nombreProducto, codigo);
                                    }
                                } else {
                                    System.out.println("Ingrese un valor correcto");
                                    u = 0;
                                }
                            } while (u == 0);
                        } else if (op == 5) {
                            int p = 1;
                            do {
                                System.out.println("*-------------------------------*");
                                System.out.println("|Elija el tipo de Opción        |");
                                System.out.println("|-------------------------------|");
                                System.out.println("|1.Actualizar Empleados         |");
                                System.out.println("|2.Buscar Empleados             |");
                                System.out.println("|3.Eliminar Empleados           |");
                                System.out.println("*-------------------------------*");
                                int sm5 = es.nextInt();
                                es.nextLine();
//-------------------------------------------------------ACTULIZAR EMPLEADOS----------------------------------------------------------------------------------------------
                                if (sm5 == 1 || sm5 == 2 || sm5 == 3) {

                                    if (sm5 == 1) {

                                        System.out.println("INGRESE LA CEDULA");
                                        String cedula = es.next();

                                        EmpleadoControlador eC = new EmpleadoControlador();

                                        EmpleadoModelo eM1 = eC.buscarDatosCliente(cedula);
                                        System.out.println(eM1.imprimir());

                                        System.out.println("Actualizar los datos del cliente");
                                        PersonaModelo pM = new PersonaModelo();

                                        System.out.println("Ingrese el nuevo Nombre:");
                                        pM.setNombre(es.next());
                                        System.out.println("Ingrese el nuevo Apellido:");
                                        pM.setApellido(es.next());
                                        System.out.println("Ingrese el nuevo Cedula:");
                                        pM.setCedula(es.next());
                                        System.out.println("Ingrese el nuevo Direccion:");
                                        pM.setDireccion(es.next());
                                        System.out.println("Ingrese el nuevo Fecha de Nacimiento (AAAA/MM/DD):");
                                        pM.setFechaNacimiento(es.next());
                                        System.out.println("Ingrese el nuevo Telefono:");
                                        pM.setTelefono(es.next());

                                        EmpleadoModelo eM = new EmpleadoModelo();
                                        System.out.println("Ingrese el nuevo Puesto");
                                        eM.setPuesto(es.next());
                                        System.out.println("Ingrese el nuevo Salario");
                                        eM.setSalario(es.nextDouble());

                                        eC.ActualisarEmpleado(cedula, eM, pM);
//----------------------------------------------------------BUSCAR EMPLEADOS--------------------------------------------------------------------------------------------
                                    } else if (sm5 == 2) {

                                        System.out.println("INGRESE LA CÉDULA");
                                        String cedula = es.next();
                                        EmpleadoControlador eC = new EmpleadoControlador();
                                        ArrayList<EmpleadoModelo> listaEmpleado = eC.listaEmpleadoPersona(cedula);
                                        for (EmpleadoModelo l : listaEmpleado) {
                                            System.out.println(l.imprimir());
                                        }
//--------------------------------------------------------ELIMINAR EMPLEADO------------------------------------------------------------------------------------------------                                    
                                    } else if (sm5 == 3) {
                                        System.out.println("INGRESE LA CÉDULA");
                                        String cedula = es.next();
                                        EmpleadoControlador eC = new EmpleadoControlador();
                                        eC.eliminarEmpleado(cedula);

                                    }

                                } else {
                                    System.out.println("Ingrese un valor correcto");
                                    p = 0;
                                }
                            } while (p != 0);
                        } else {
                            System.out.println("Ingrese un valor correcto");
                            au = 0;
                        }

                    } while (au !=0);
                    es.close();

                }
            } else if (usu == 0) {
                System.out.println("Saliendo del programa...");
                i = 0;
            }
        } while (i != 0);

    }
}
