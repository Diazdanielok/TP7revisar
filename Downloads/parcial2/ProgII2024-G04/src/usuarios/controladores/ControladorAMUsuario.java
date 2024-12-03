/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.controladores;

import interfaces.IControladorAMUsuario;
import interfaces.IGestorUsuarios;
import static interfaces.IGestorUsuarios.ERROR_CLAVES;
import static interfaces.IGestorUsuarios.USUARIOS_DUPLICADOS;
import static interfaces.IGestorUsuarios.VALIDACION_EXITO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import usuarios.modelos.GestorUsuarios;
import usuarios.modelos.ModeloComboPerfil;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;
import usuarios.vistas.VentanaAMUsuario;

/**
 *
 * @author Fabri
 */
public class ControladorAMUsuario implements IControladorAMUsuario {

    private VentanaAMUsuario ventanaAMUsuario;

   
    private static final String DATOS_INVALIDOS = "Los datos no son válidos. Por favor revise los campos.";

    // Es booleano para que el método btnGuardarClic() sepa si debe agregar un usuario nuevo o si debe modificarlo
    private boolean usuarioNuevo;

    
    public ControladorAMUsuario(java.awt.Dialog ventanaPadre) {
        this.ventanaAMUsuario = new VentanaAMUsuario(ventanaPadre, true, this);
        this.configurarPerfil();
        this.ventanaAMUsuario.setLocationRelativeTo(null);
        this.ventanaAMUsuario.setTitle(TITULO_NUEVO);
        this.usuarioNuevo = true; // nuevo usuario
        this.ventanaAMUsuario.setVisible(true);
    }

    //  MODIFICAR usuario
    public ControladorAMUsuario(java.awt.Dialog ventanaPadre, Usuario usuarioAModificar) {
        this.ventanaAMUsuario = new VentanaAMUsuario(ventanaPadre, true, this);
        this.ventanaAMUsuario.setLocationRelativeTo(null);
        this.configurarPerfil();
        
        this.ventanaAMUsuario.verTextoCorreo().setText(usuarioAModificar.verCorreo());
        this.ventanaAMUsuario.verTextoCorreo().setEditable(false);
        this.ventanaAMUsuario.verTextoCorreo().setOpaque(false);

        this.ventanaAMUsuario.verTextoApellido().setText(usuarioAModificar.verApellido());
        this.ventanaAMUsuario.verTextoNombre().setText(usuarioAModificar.verNombre());
        ((ModeloComboPerfil) this.ventanaAMUsuario.verComboPerfiles().getModel()).seleccionarPerfil(perfilDeUsuario(usuarioAModificar));
        this.ventanaAMUsuario.verComboPerfiles().setEnabled(false);
        this.ventanaAMUsuario.verComboPerfiles().setOpaque(false);
        this.ventanaAMUsuario.verTextoContrasenia().setText(usuarioAModificar.verContrasenia());
        this.ventanaAMUsuario.verTextoContraseniaRep().setText(usuarioAModificar.verContrasenia()); // si el usuario existe las contraseñas deben coincidir...
        this.ventanaAMUsuario.verTextoApellido().requestFocus();
        
        this.ventanaAMUsuario.setTitle(TITULO_MODIFICAR);
        this.usuarioNuevo = false; // se modifica
        this.ventanaAMUsuario.setVisible(true);
    }

    private void configurarPerfil() {
        ModeloComboPerfil modelo = new ModeloComboPerfil();
        this.ventanaAMUsuario.verComboPerfiles().setModel(modelo);
    }

    // método auxiliar para determinar el perfil de un determinado usuario
    private Perfil perfilDeUsuario(Usuario usuario) {
        Perfil perfil;

        if (usuario.getClass().getSimpleName().equalsIgnoreCase(Perfil.CLIENTE.toString())) {
            perfil = Perfil.CLIENTE;
        } else if (usuario.getClass().getSimpleName().equalsIgnoreCase(Perfil.EMPLEADO.toString())) {
            perfil = Perfil.EMPLEADO;
        } else {
            perfil = Perfil.ENCARGADO;
        }

        return perfil;
    }

    @Override
    public void btnGuardarClic(ActionEvent evt) {

        String correo = ventanaAMUsuario.verTextoCorreo().getText().trim();

        String apellido = ventanaAMUsuario.verTextoApellido().getText().trim();

        String nombre = ventanaAMUsuario.verTextoNombre().getText().trim();

        char[] clave = ventanaAMUsuario.verTextoContrasenia().getPassword();

        char[] claveRepetida = ventanaAMUsuario.verTextoContraseniaRep().getPassword();

        //controlar si la siguiente linea es correcta
        Perfil perfil = ((ModeloComboPerfil) ventanaAMUsuario.verComboPerfiles().getModel()).obtenerPerfil();

        IGestorUsuarios gu = GestorUsuarios.instanciar();

        if (usuarioNuevo) {
            if (((GestorUsuarios) gu).verificarDatos(correo, apellido, nombre, perfil, new String(clave), new String(claveRepetida)).equals(VALIDACION_EXITO)) {
                String resultadoOperacion = gu.crearUsuario(correo, apellido, nombre, perfil, new String(clave), new String(claveRepetida));
                if (resultadoOperacion.equals(USUARIOS_DUPLICADOS)) {
                    this.mensajeInformacion(USUARIOS_DUPLICADOS, "Error");
                } else {
                    this.ventanaAMUsuario.dispose(); 
                }
            } else {
                if (((GestorUsuarios) gu).verificarDatos(correo, apellido, nombre, perfil, new String(clave), new String(claveRepetida)).equals(ERROR_CLAVES)) {
                    this.mensajeInformacion(ERROR_CLAVES, "Error");
                } else {
                    this.mensajeInformacion(DATOS_INVALIDOS, "Error");
                }
            }
        } else {
           
           
            String resultadoVerificacion = ((GestorUsuarios) gu).verificarDatos(correo, apellido, nombre, perfil, new String(clave), new String(claveRepetida));

            if (resultadoVerificacion.equals(VALIDACION_EXITO)) {
                gu.modificarUsuario(correo, apellido, nombre, perfil, new String(clave), new String(claveRepetida));
                this.ventanaAMUsuario.dispose();
            } else {
                if (resultadoVerificacion.equals(ERROR_CLAVES)) {
                    this.mensajeInformacion(ERROR_CLAVES, "Error");
                } else {
                    this.mensajeInformacion(DATOS_INVALIDOS, "Error");
                }
            }
        }
    }

    public void mensajeInformacion(String mensaje, String titulo) {
        JOptionPane.showMessageDialog(this.ventanaAMUsuario, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {

        int opcionEscogida = JOptionPane.showOptionDialog(this.ventanaAMUsuario, "¿Desea cancelar la carga del usuario? Se perderán los cambios no guardados.", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"Si", "No"}, "Si");
        if (opcionEscogida == JOptionPane.YES_OPTION) {
            this.ventanaAMUsuario.dispose();
        }
    }

    
    @Override
    public void txtCorreoPresionarTecla(KeyEvent evt) {
        if (KeyEvent.VK_ENTER == evt.getKeyChar()) {
            this.ventanaAMUsuario.verTextoApellido().requestFocus();
        }
    }

    @Override
    public void txtApellidoPresionarTecla(KeyEvent evt) {
        if (KeyEvent.VK_ENTER == evt.getKeyChar()) {
            this.ventanaAMUsuario.verTextoNombre().requestFocus();
        }
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        if (KeyEvent.VK_ENTER == evt.getKeyChar()) {
            this.ventanaAMUsuario.verTextoContrasenia().requestFocus();
        }
    }

    @Override
    public void passClavePresionarTecla(KeyEvent evt) {
        if (KeyEvent.VK_ENTER == evt.getKeyChar()) {
            this.ventanaAMUsuario.verTextoContraseniaRep().requestFocus();
        }
    }

    //  Se ejecuta btnGuardarClic(). 
    @Override
    public void passClaveRepetidaPresionarTecla(KeyEvent evt) {
        if (KeyEvent.VK_ENTER == evt.getKeyChar()) {
            this.btnGuardarClic(null);
        }
    }

}
