/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.ArrayList;
import java.util.List;
import usuarios.modelos.Perfil;
import usuarios.modelos.Usuario;

/**
 *
 * @author fabri
 */
public interface IGestorUsuarios {

    public String crearUsuario(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida);

    public List<Usuario> verUsuarios();

    public List<Usuario> buscarUsuarios(String apellido);

    public String borrarUsuario(Usuario usuario);

    public boolean existeEsteUsuario(Usuario usuario);

    public Usuario obtenerUsuario(String correo);
   
    // método agregado para Parcial2
    public String modificarUsuario(String correoUsuarioAModificar, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida);

    public static final String EXITO = "Usuario creado/modificado con éxito";
    public static final String ERROR_CORREO = "El correo del usuario es incorrecto";
    public static final String ERROR_APELLIDO = "El apellido del usuario es incorrecto";
    public static final String ERROR_NOMBRE = "El nombre del usuario es incorrecto";
    public static final String ERROR_CLAVES = "Las claves especificadas no coinciden o son incorrectas";
    public static final String ERROR_PERFIL = "El perfil del usuario es incorrecto";
    public static final String PERFIL_CAMBIO = "El usuario no puede cambiarse de perfil";
    public static final String ERROR_PERMISOS = "No se tienen los permisos para realizar esta funcionalidad";
    public static final String USUARIOS_DUPLICADOS = "Ya existe un usuario con ese correo";
    public static final String USUARIO_INEXISTENTE = "No existe el usuario especificado";
    public static final String VALIDACION_EXITO = "Los datos del usuario son correctos";
}
