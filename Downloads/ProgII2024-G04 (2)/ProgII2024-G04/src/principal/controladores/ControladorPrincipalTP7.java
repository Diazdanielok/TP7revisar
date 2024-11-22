/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.controladores;

import interfaces.IGestorPedidos;
import interfaces.IGestorProductos;
import interfaces.IGestorUsuarios;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.GestorProductos;
import productos.modelos.Producto;
import usuarios.modelos.Cliente;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author Mariana
 */
public class ControladorPrincipalTP7 {
    public static void main(String[] args) {
        IGestorProductos gp = GestorProductos.instanciar();
        IGestorUsuarios gu = GestorUsuarios.instanciar();
        IGestorPedidos gPed = GestorPedidos.instanciar();
//            
//        System.out.println("#####PRODUCTOS#####");
//        /*CREAR PRODUCTO*/
        System.out.println(gp.crearProducto(1, "Empanadas", 1.0f, Categoria.ENTRADA, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(2, "Bife con arroz", 2.0f, Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(3, "Copa helada", 3.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(3, "Producto4", 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //producto repetido, mismo codigo
        System.out.println(gp.crearProducto(0, "Producto4", 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //código inválido vale 0
        System.out.println(gp.crearProducto(4, null, 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //sin descripción -- nula
        System.out.println(gp.crearProducto(4, "", 4.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //descripción inválida  -- cadena vacia
        System.out.println(gp.crearProducto(4, "Producto4", 0.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        //precio inválido  -- precio en 0
        System.out.println(gp.crearProducto(4, "Producto4", 4.0f, null, Estado.DISPONIBLE));
        //sin categoría 
        System.out.println(gp.crearProducto(4, "Producto4", 4.0f, Categoria.POSTRE, null));
        //sin estado
        System.out.println(gp.crearProducto(5, "Queso y dulce", 3.0f, Categoria.POSTRE, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(6, "Ensalada", 3.0f, Categoria.ENTRADA, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(7, "Suprema con pure", 3.0f, Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE));
        System.out.println(gp.crearProducto(9, "Suprema con ensalada", 2.8f, Categoria.PLATO_PRINCIPAL, Estado.DISPONIBLE));

        /*RECUPERAR LOS PRODUCTOS CON EL METODO MENU*/
        System.out.println("######## PRODUCTOS CON MENU (ordena por categ y luego desc ########");
        System.out.println("Productos");
        System.out.println("=========");
        for(Producto p : gp.menu()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
        System.out.println("######## PRODUCTOS POR CODIGO ########");
        /*OBTENER UN PRODUCTO MEDIANTE EL CODIGO*/
        Producto unProducto1 = gp.obtenerProducto(1);
        Producto unProducto2 = gp.obtenerProducto(2);
        
        System.out.println("######## PRODUCTOS DE UNA CATEGORIA ordena por la desc ########");
        /*SUBCONJUNTO DE PRODUCTOS DE UNA CATEGORIA*/
        List<Producto> productosBuscados = gp.verProductosPorCategoria(Categoria.POSTRE);
        System.out.println("Productos filtrados por categoría");
        System.out.println("===================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();

        System.out.println("######## PRODUCTOS POR DESCRIPCION ordena por categ y luego por desc########");
        /*BUSCAR PRODUCTO POR DESCRIPCION*/
        productosBuscados = gp.buscarProductos("Prod");
        System.out.println("Productos buscados por descripcion");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
      
        System.out.println("#####USUARIOS#####");
           
        gu.crearUsuario("cliente3@bar.com", "ApellidoCliente3", "NombreCliente3", Perfil.CLIENTE, "claveCliente3", "claveCliente3");
        gu.crearUsuario("cliente3@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        gu.crearUsuario("cliente1@bar.com", "ApellidoCliente1", "NombreCliente1", Perfil.CLIENTE, "claveCliente1", "claveCliente1");
        gu.crearUsuario("cliente2@bar.com", "ApellidoCliente2", "NombreCliente2", Perfil.CLIENTE, "claveCliente2", "claveCliente2");
        //cliente duplicado
        gu.crearUsuario(null, "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        //sin correo
        gu.crearUsuario("", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        //correo inválido
        gu.crearUsuario("cliente4@bar.com", null, "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        //sin apellido
        gu.crearUsuario("cliente4@bar.com", "", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        //apellido inválido
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", null, Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        //sin nombre
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "", Perfil.CLIENTE, "claveCliente4", "claveCliente4");
        //nombre inválido
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", null, "claveCliente4", "claveCliente4");
        //sin perfil
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, null, "claveCliente4");
        //sin clave
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "", "claveCliente4");
        //clave inválida
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", null);
        //sin repetir la clave
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "");
        //clave repetida inválida
        gu.crearUsuario("cliente4@bar.com", "ApellidoCliente4", "NombreCliente4", Perfil.CLIENTE, "claveCliente4", "claveCliente44");
        //sin coincidir las claves
        
       
        gu.crearUsuario("empleado1@bar.com", "ApellidoEmpleado1", "NombreEmpleado1", Perfil.EMPLEADO, "claveEmpleado1", "claveEmpleado1");
        gu.crearUsuario("empleado2@bar.com", "ApellidoEmpleado2", "NombreEmpleado2", Perfil.EMPLEADO, "claveEmpleado2", "claveEmpleado2");
        gu.crearUsuario("empleado3@bar.com", "ApellidoEmpleado3", "NombreEmpleado3", Perfil.EMPLEADO, "claveEmpleado3", "claveEmpleado3");
        gu.crearUsuario("empleado3@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //empleado duplicado
        gu.crearUsuario(null, "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //sin correo
        gu.crearUsuario("", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //correo inválido
        gu.crearUsuario("empleado4@bar.com", null, "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //sin apellido
        gu.crearUsuario("empleado4@bar.com", "", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //apellido inválido
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", null, Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //sin nombre
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado4");
        //nombre inválido
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", null, "claveEmpleado4", "claveEmpleado4");
        //sin perfil
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, null, "claveEmpleado4");
        //sin clave
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "", "claveEmpleado4");
        //clave sin especificar
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", null);
        //sin repetir clave        
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "");
        //clave repetida inválida
        gu.crearUsuario("empleado4@bar.com", "ApellidoEmpleado4", "NombreEmpleado4", Perfil.EMPLEADO, "claveEmpleado4", "claveEmpleado44");
        //sin coincidir las claves
        
        gu.crearUsuario("encargado1@bar.com", "ApellidoEncargado1", "NombreEncargado1", Perfil.ENCARGADO, "claveEncargado1", "claveEncargado1");
        gu.crearUsuario("encargado2@bar.com", "ApellidoEncargado2", "NombreEncargado2", Perfil.ENCARGADO, "claveEncargado2", "claveEncargado2");
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado3", "NombreEncargado3", Perfil.ENCARGADO, "claveEncargado3", "claveEncargado3");
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //encargado duplicado
        gu.crearUsuario(null, "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //sin correo
        gu.crearUsuario("", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //correo inválido
        gu.crearUsuario("encargado3@bar.com", null, "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //sin apellido
        gu.crearUsuario("encargado3@bar.com", "", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //apellido inválido
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", null, Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //sin nombre
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado4");
        //nombre inválido
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", null, "claveEncargado4", "claveEncargado4");
        //sin perfil
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, null, "claveEncargado4");
        //sin clave
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "", "claveEncargado4");
        //clave sin especificar
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", null);
        //sin repetir clave        
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "");
        //clave repetida inválida
        gu.crearUsuario("encargado3@bar.com", "ApellidoEncargado4", "NombreEncargado4", Perfil.ENCARGADO, "claveEncargado4", "claveEncargado44");
        //sin coincidir las claves


      //  System.out.println("Obtener usuarios y productos");
       // System.out.println("=========");
        Usuario unCliente1 = gu.obtenerUsuario("cliente1@bar.com");
        Producto prod1 = gp.obtenerProducto(1);
        Producto prod2 = gp.obtenerProducto(2);
        
        System.out.println("=========");
        System.out.println("Crear pedidos");
        List<ProductoDelPedido> productosDelPedido1 = new ArrayList<>();
        ProductoDelPedido pdp1 = new ProductoDelPedido(unProducto1, 1);
        ProductoDelPedido pdp2 = new ProductoDelPedido(unProducto2, 2); 
        LocalDateTime fechaYHora = LocalDateTime.now();
        LocalDate fecha = fechaYHora.toLocalDate();
        LocalTime hora = fechaYHora.toLocalTime();
        if (!productosDelPedido1.contains(pdp1))
            productosDelPedido1.add(pdp1);
        if (!productosDelPedido1.contains(pdp2))
            productosDelPedido1.add(pdp2);
        gPed.crearPedido(fecha, hora, productosDelPedido1, (Cliente)unCliente1);
        
        Usuario unCliente2 = gu.obtenerUsuario("cliente2@bar.com");
        List<ProductoDelPedido> productosDelPedido2 = new ArrayList<>();
        ProductoDelPedido pdp3 = new ProductoDelPedido(unProducto1, 10);
        ProductoDelPedido pdp4 = new ProductoDelPedido(unProducto2, 20);
        ProductoDelPedido pdp5 = new ProductoDelPedido(unProducto1, 30);
        //producto repetido        
        if (!productosDelPedido2.contains(pdp3))
            productosDelPedido2.add(pdp3);
        if (!productosDelPedido2.contains(pdp4))
            productosDelPedido2.add(pdp4);
        if (!productosDelPedido2.contains(pdp5))
            productosDelPedido2.add(pdp5);
        gPed.crearPedido(fecha, hora, productosDelPedido2, (Cliente)unCliente2);

        gPed.crearPedido(null, hora, productosDelPedido2, (Cliente)unCliente1);
        //sin fecha
        gPed.crearPedido(fecha, null, productosDelPedido2, (Cliente)unCliente1);
        //sin hora
       ArrayList<ProductoDelPedido> productosDelPedido3 = new ArrayList<>();
        gPed.crearPedido(fecha, hora, null, (Cliente)unCliente1);
        //sin productos
        gPed.crearPedido(fecha, hora, productosDelPedido3, (Cliente)unCliente1);
        //sin productos
        gPed.crearPedido(fecha, hora, productosDelPedido2, null);
//        //sin cliente
        System.out.println("=======");
        System.out.println("Pedidos");
        for(Pedido p : gPed.verPedidos()) {
            p.mostrar();
        }
        System.out.println("=======");
        
        productosBuscados = gp.buscarProductos("Supre");
        System.out.println("Productos buscados por descripcion");
        System.out.println("==================");
        for(Producto p : productosBuscados) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
//        System.out.println(gp.borrarProducto(gp.menu().get(4)));
//        System.out.println(gp.borrarProducto(gp.menu().get(0)));
        
        System.out.println("Productos");
        System.out.println("==================");
        for(Producto p : gp.menu()) {
            p.mostrar();
            System.out.println();
        }
        System.out.println();
        
    }
}

