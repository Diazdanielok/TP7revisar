/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuarios.modelos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import pedidos.modelos.Pedido;

/**
 *
 * @author 54381
 */
public abstract class Usuario implements Comparable<Usuario>{
//     private String correo;
//    private String clave;
//    private String nombre;
//    private String apellido;
//    private Perfil perfil;
//    private String claverepetida;
//    private List<Pedido>listaPedidos = new ArrayList<>();
//
//    public Usuario(String correo, String apellido, Perfil perfil, String nombre, String clave,String claverepetida) {
//        this.correo = correo;
//        this.clave = clave;
//        this.nombre = nombre;
//        this.apellido = apellido;
//        this.claverepetida = claverepetida;
//        this.perfil= perfil;
//    }
    private String correo;
    private String clave;
    private String nombre;
    private String apellido;
   
    private List<Pedido>listaPedidos = new ArrayList<>();

    public Usuario(String correo, String apellido, String nombre, String clave) {
        this.correo = correo;
        this.clave = clave;
        this.nombre = nombre;
        this.apellido = apellido;
        
    }

//    public String verClaverepetida() {
//        return claverepetida;
//    }
//
//    public void asignarClaverepetida(String claverepetida) {
//        this.claverepetida = claverepetida;
//    }

    
    
    public String verCorreo() {
        return correo;
    }

    public void asignarCorreo(String correo) {
        this.correo = correo;
    }

    public String verClave() {
        return clave;
    }

    public void asignarClave(String clave) {
        this.clave = clave;
    }

    public String verNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    public String verApellido() {
        return apellido;
    }

    public void asignarApellido(String apellido) {
        this.apellido = apellido;
    }


    public void asignarPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
  
    
 public void mostrar(){
    System.out.println("Correo: "+ correo + "\nClave: "+ clave + "\nApellido: "+ apellido + "\nNombre: "+ nombre);
    }
 
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.correo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }

        final Usuario other = (Usuario) obj;
        return Objects.equals(this.correo, other.correo);
    }
    
     public abstract List<Pedido> verPedidos();

    @Override
    public int compareTo(Usuario o){
        if(this.apellido.equals(o.apellido)){
        return this.nombre.compareTo(o.nombre);
        }
        return this.apellido.compareTo(o.apellido);
    }

    
}