/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos.modelos;

import java.util.Comparator;

/**
 *
 * @author estudiante
 */
public class CompPedidoNumAsc implements Comparator<Pedido> {

    /*
    Comparator para comparar objetos de la clase Pedido.
     */
    @Override
    public int compare(Pedido t, Pedido t1) {
        return t.verNumero() - t1.verNumero();
    }

}
