/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package principal.vistas;

import interfaces.IControladorPrincipal;
import javax.swing.JButton;

/**
 *
 * @author  Fabri
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    // creación de la referencia para el controlador principal
    private IControladorPrincipal controlador;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal(IControladorPrincipal controlador) {
        initComponents();
        this.controlador = controlador; // en el constructor de la ventana defino el controlador
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonUsuarios = new javax.swing.JButton();
        botonProductos = new javax.swing.JButton();
        botonPedidos = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        botonUsuarios.setText("Usuarios");
        botonUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosClic(evt);
            }
        });

        botonProductos.setText("Productos");
        botonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductosClic(evt);
            }
        });

        botonPedidos.setText("Pedidos");
        botonPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPedidosClic(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirClic(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(botonPedidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonUsuarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                        .addComponent(botonSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(botonUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonPedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addGap(30, 30, 30)
                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUsuariosClic(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosClic
        this.controlador.btnUsuariosClic(evt);
    }//GEN-LAST:event_btnUsuariosClic

    private void btnProductosClic(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductosClic
        this.controlador.btnProductosClic(evt);
    }//GEN-LAST:event_btnProductosClic

    private void btnSalirClic(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirClic
        this.controlador.btnSalirClic(evt);
    }//GEN-LAST:event_btnSalirClic

    private void btnPedidosClic(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPedidosClic
        this.controlador.btnPedidosClic(evt);
    }//GEN-LAST:event_btnPedidosClic

    // ¿van todos estos metodos get/set?¿son necesarios?
    public IControladorPrincipal verControlador() {
        return controlador;
    }

    public void asignarControlador(IControladorPrincipal controlador) {
        this.controlador = controlador;
    }

    public JButton verBotonPedidos() {
        return botonPedidos;
    }

    public void asignarBotonPedidos(JButton botonPedidos) {
        this.botonPedidos = botonPedidos;
    }

    public JButton verBotonProductos() {
        return botonProductos;
    }

    public void asignarBotonProductos(JButton botonProductos) {
        this.botonProductos = botonProductos;
    }

    public JButton verBotonSalir() {
        return botonSalir;
    }

    public void asignarBotonSalir(JButton botonSalir) {
        this.botonSalir = botonSalir;
    }

    public JButton verBotonUsuarios() {
        return botonUsuarios;
    }

    public void asignarBotonUsuarios(JButton botonUsuarios) {
        this.botonUsuarios = botonUsuarios;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonPedidos;
    private javax.swing.JButton botonProductos;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonUsuarios;
    // End of variables declaration//GEN-END:variables
}