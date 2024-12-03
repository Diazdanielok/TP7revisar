/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedidos.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import usuarios.modelos.Cliente;

/**
 *
 * @author Fabri
 */
public class Pedido {

    private int numero;
    private LocalDateTime fechaYHora;
    private Estado estado;
    private Cliente cliente;
    private List<ProductoDelPedido> productoDelPedido;

    public Pedido(int numero, LocalDateTime fechaYHora, List<ProductoDelPedido> productoDelPedido, Cliente cliente) {
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.estado = Estado.CREADO; // todo pedido inicia en CREADO
        this.cliente = cliente;
        this.productoDelPedido = productoDelPedido;
    }

    public void mostrar() {
        System.out.println("Nro: " + numero
                + "\nFecha: " + this.fechaYHora.format(this.formatoFecha())
                + "\tHora: " + this.fechaYHora.format(this.formatoHora())
                + "\nCliente: " + cliente.verApellido() + ", " + cliente.verNombre()
                + "\nEstado: " + this.estado.name()
                + "\n\tProducto \t\tCantidad"
                + "\n\t====================");

        for (ProductoDelPedido pdp : productoDelPedido) {
            System.out.println("\t" + pdp.verProducto().toString()
                    + "\t\t" + pdp.verCantidad());
        }
    }

    private DateTimeFormatter formatoFecha() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    private DateTimeFormatter formatoHora() {
        return DateTimeFormatter.ofPattern("hh:mm");
    }

    public int verNumero() {
        return numero;
    }

    public void asignarNumero(int numero) {
        this.numero = numero;
    }

    public LocalDateTime verFechaYHora() {
        return fechaYHora;
    }

    public LocalDate verFecha() {
        return fechaYHora.toLocalDate();
    }

    public LocalTime verHora() {
        return fechaYHora.toLocalTime();
    }

    public void asignarFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public void asignarCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ProductoDelPedido> verProductoDelPedido() {
        return productoDelPedido;
    }

    public void asignarProductoDelPedido(List<ProductoDelPedido> productoDelPedido) {
        this.productoDelPedido = productoDelPedido;
    }

    public Estado verEstado() {
        return estado;
    }

    public void asignarEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente verCliente() {
        return cliente;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.numero;
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        return this.numero == other.numero;
    }


}
