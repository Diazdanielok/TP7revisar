/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import java.awt.event.ActionEvent;
import interfaces.IControladorPrincipal;
import interfaces.IControladorUsuarios;
import javax.swing.JOptionPane;
import principal.vistas.VentanaPrincipal;
import usuarios.controladores.ControladorUsuarios;

/**
 *
 * @author DANIEL
 */
public class ControladorPrincipal implements IControladorPrincipal {

    private VentanaPrincipal ventanaPrincipal;
    
    
    public ControladorPrincipal() {
        this.ventanaPrincipal = new VentanaPrincipal(this);
        this.ventanaPrincipal.setLocationRelativeTo(null);
        ventanaPrincipal.setTitle(TITULO);
        ventanaPrincipal.setVisible(true);
    }
    
    // método main... aquí vamos probando el programa con la interfaz gráfica
    public static void main(String[] args) {
        ControladorPrincipal cp =  new ControladorPrincipal();
    }

    @Override
    public void btnUsuariosClic(ActionEvent evt) {
        IControladorUsuarios controladorUsuarios = new ControladorUsuarios(ventanaPrincipal);
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        // utilizo un confirm dialog
        int opcion = JOptionPane.showConfirmDialog(this.ventanaPrincipal, "¿Seguro que desea salir de la aplicación?", "Confirmar salir", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            this.ventanaPrincipal.dispose();
        }
    }

    @Override
    public void btnProductosClic(ActionEvent evt) {
        /*
            Funcionalidad no implementada. Muestro mensaje
            de diálogo para informar al usuario.
        */
        String mensajeFuncionalidadNoDisp = "Esta funcionalidad aún no está disponible.";
        JOptionPane.showMessageDialog(this.ventanaPrincipal, mensajeFuncionalidadNoDisp, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void btnPedidosClic(ActionEvent evt) {
        /*
            Funcionalidad no implementada. Muestro mensaje
            de diálogo para informar al usuario.
        */
        String mensajeFuncionalidadNoDisp = "Esta funcionalidad aún no está disponible.";
        JOptionPane.showMessageDialog(this.ventanaPrincipal, mensajeFuncionalidadNoDisp, "Advertencia", JOptionPane.INFORMATION_MESSAGE);
    }

}
