/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import java.util.List;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;

/**
 *
 * @author estudiante
 */
public class Empleado extends Usuario {

    // Constructor para la clase Empleado
    public Empleado(String correo, String contrasenia, String apellido, String nombre) {
        super(correo, contrasenia, apellido, nombre);
    }

    @Override
    public List<Pedido> verPedidos() {

        GestorPedidos gp = GestorPedidos.instanciar();

        return gp.verPedidos();
    }
    
    @Override
    public String toString() {
        return "EMPLEADO";
    }
}
