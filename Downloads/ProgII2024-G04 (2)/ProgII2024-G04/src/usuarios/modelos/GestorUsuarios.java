/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import static interfaces.IGestorUsuarios.ERROR_APELLIDO;
import static interfaces.IGestorUsuarios.ERROR_CLAVES;
import static interfaces.IGestorUsuarios.ERROR_CORREO;
import static interfaces.IGestorUsuarios.ERROR_NOMBRE;
import static interfaces.IGestorUsuarios.ERROR_PERFIL;
import static interfaces.IGestorUsuarios.EXITO;
import static interfaces.IGestorUsuarios.USUARIOS_DUPLICADOS;
import static interfaces.IGestorUsuarios.USUARIO_INEXISTENTE;
import static interfaces.IGestorUsuarios.VALIDACION_EXITO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static usuarios.modelos.Perfil.CLIENTE;
import static usuarios.modelos.Perfil.EMPLEADO;
import static usuarios.modelos.Perfil.ENCARGADO;

/**
 *
 * @author fabri
 */
public class GestorUsuarios implements IGestorUsuarios{
        
    ArrayList<Usuario> listausuarios = new ArrayList();
    
    private static GestorUsuarios gestor;
    
//    public static final String EXITO = "Usuario creado/modificado con éxito";
//    public static final String ERROR_CORREO = "El correo del usuario es incorrecto";
//    public static final String ERROR_APELLIDO = "El apellido del usuario es incorrecto";
//    public static final String ERROR_NOMBRE = "El nombre del usuario es incorrecto";
//    public static final String ERROR_CLAVES = "Las claves especificadas no coinciden o son incorrectas";
//    public static final String ERROR_PERFIL = "El perfil del usuario es incorrecto";
//    public static final String USUARIOS_DUPLICADOS = "Ya existe un usuario con ese correo";
//    public static final String VALIDACION_EXITO = "Los datos del usuario son correctos";
//    public static final String USUARIO_INEXISTENTE = "Este usuario no existe";
    
    public static GestorUsuarios instanciar(){  
        if(gestor == null){
            gestor = new GestorUsuarios();
        }
        return gestor;
    }
    
  
    public String crearUsuario(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        String validacion = validarUsuario(correo, clave, apellido, nombre, claveRepetida, perfil);
        if (!validacion.equals(VALIDACION_EXITO)) {
            return validacion; // Retorna el error específico de validación
        }

        // Verificar si ya existe un producto con el mismo código
        if (obtenerUsuario(correo) != null) {
            return USUARIOS_DUPLICADOS;
        }

         
        Usuario nuevoUsuario;
        switch (perfil) {
            case CLIENTE:
                nuevoUsuario = new Cliente(correo,  apellido,  nombre, clave);
                break;
            case EMPLEADO:
                nuevoUsuario = new Empleado(correo,  apellido,  nombre, clave);
                break;
            case ENCARGADO:
                nuevoUsuario = new Encargado(correo,  apellido,  nombre, clave);
                break;
            default:
                return "Perfil no válido"; // Manejo de errores en caso de un perfil no válido
        }

        listausuarios.add(nuevoUsuario);
        return EXITO;
    }
    private String validarUsuario(String correo, String clave, String apellido, String nombre, String claverepetida, Perfil perfil) {
        if (correo == null || !correo.contains("@")) {
            return ERROR_CORREO;
        }
        if(perfil == null){
            return ERROR_PERFIL;
        }
        if (apellido == null || apellido.trim().isEmpty()) {
            return ERROR_APELLIDO;
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            return ERROR_NOMBRE;
        }
        if (clave == null || clave.trim().isEmpty()) {
            return ERROR_CLAVES;
        }
        if (claverepetida == null || !claverepetida.equals(clave) || clave.trim().isEmpty()) {
            return ERROR_CLAVES;
        }
        return VALIDACION_EXITO;
    }
    
    
       public List<Usuario> verUsuarios(){
           Collections.sort(listausuarios);
         return listausuarios;
       }
    
       
       public ArrayList<Usuario> buscarUsuarios(String apellido){
           ArrayList<Usuario> usuariosPorApellido = new ArrayList<>();
        for(Usuario usuario : listausuarios){
            if(usuario.verApellido().contains(apellido)){
                usuariosPorApellido.add(usuario);
            }
        }
        Collections.sort(listausuarios);
        return usuariosPorApellido;
      }
    
       
       public boolean existeEsteUsuario(Usuario usuario){
       if(listausuarios.contains(usuario))
           return true;
       return false;
      }
    
       
    public Usuario obtenerUsuario(String correo){
        for(Usuario usuario : listausuarios){
          if(usuario.verCorreo().equals(correo))
            return usuario;
        }
        return null;
    }

    
    public String borrarUsuario(Usuario usuario) {
        for (Usuario u : listausuarios){
            if (u.equals(usuario)){
                if(u.verPedidos().isEmpty()){
                    listausuarios.remove(u);
                }
            } 
            return EXITO;
        }
        
        return USUARIO_INEXISTENTE;
    }

    
    
}
