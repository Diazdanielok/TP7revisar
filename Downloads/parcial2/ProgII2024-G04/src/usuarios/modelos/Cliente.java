/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import java.util.ArrayList;
import pedidos.modelos.Pedido;

/**
 *
 * @author estudiante
 */
public class Cliente extends Usuario {

    private ArrayList<Pedido> pedidos = new ArrayList<>();

    // Constructor para la clase Cliente
    public Cliente(String correo, String contrasenia, String apellido, String nombre) {
        super(correo, contrasenia, apellido, nombre);
    }

    public void asignarPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public ArrayList<Pedido> verPedidos() {
        return pedidos;
    }

    public void agregarPedido(Pedido pedido) {
        /*
        Si el pedido no está en el ArrayList, lo agrega al final. Si
        el pedido está en el ArrayList, reemplaza el pedido con el 
        método set(index, element)... reemplaza por element el elemento
        que está en la posición index.
         */
        if (!pedidos.contains(pedido)) {
            pedidos.add(pedido);
        } else {
            pedidos.set(pedidos.indexOf(pedido), pedido);
        }
    }

    public void cancelarPedido(Pedido pedido) {
        pedidos.remove(pedido);
    }

    // redefinicion de toString para que devuelva el nombre correspondiente al Perfil. Para usar en método escribir() de GestorUsuarios.
    @Override
    public String toString() {
        return "CLIENTE";
    }
}
