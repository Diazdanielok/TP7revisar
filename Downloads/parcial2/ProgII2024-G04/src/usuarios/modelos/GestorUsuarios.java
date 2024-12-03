/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pedidos.modelos.GestorPedidos;

/**
 *
 * @author DANIEL
 */
public class GestorUsuarios implements IGestorUsuarios {

    private List<Usuario> usuarios = new ArrayList<>();

   
    private static final String ARCHIVO_USUARIOS = "archivoUsuarios.txt";
    // constante para el separador de valores
    private static final String SEPARADOR = "-";

    // Patrón Singleton
    private static GestorUsuarios gestor;

    private GestorUsuarios() {
        this.leer(this.ARCHIVO_USUARIOS);
    }

    public static GestorUsuarios instanciar() {
        if (gestor == null) {
            gestor = new GestorUsuarios();
        }
        return gestor;
    }

    public String verificarDatos(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {
        if (correo == null || !correo.contains("@")) {
            return ERROR_CORREO;
        }
        if (apellido == null || apellido.isEmpty() || !apellido.matches("[\\p{L}' ]+")) {
            return ERROR_APELLIDO;
        }
        if (nombre == null || nombre.isEmpty() || !nombre.matches("[\\p{L}' ]+")) {
            return ERROR_NOMBRE;
        }
        if (clave == null || clave.isEmpty()) {
            return ERROR_CLAVES;
        }
        if (claveRepetida == null || claveRepetida.isEmpty() || !(claveRepetida.equals(clave))) {
            return ERROR_CLAVES;
        }
        if (perfil == null) {
            return ERROR_PERFIL;
        }

        return VALIDACION_EXITO;
    }

    public String crearUsuario(String correo, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {

        String salida = verificarDatos(correo, apellido, nombre, perfil, clave, claveRepetida);

        if (!salida.equals(VALIDACION_EXITO)) {
            return salida;
        }

        switch (perfil) {
            case CLIENTE:
                Usuario cliente = new Cliente(correo, clave, apellido, nombre);
                return agregarUsuario(cliente);

            case EMPLEADO:
                Usuario empleado = new Empleado(correo, clave, apellido, nombre);
                return agregarUsuario(empleado);

            // default para case ENCARGADO (sino hay que poner return para el método)
            default:
                Usuario encargado = new Encargado(correo, clave, apellido, nombre);
                return agregarUsuario(encargado);
        }
    }

    private String agregarUsuario(Usuario u) {
        if (!usuarios.contains(u)) {
            usuarios.add(u);
            this.escribir();
            return EXITO;
        } else {
            return USUARIOS_DUPLICADOS;
        }
    }

    public List<Usuario> verUsuarios() {
        Collections.sort(usuarios, new CompUsuarioApYNom());
        return usuarios;
    }

    public List<Usuario> buscarUsuarios(String apellido) {
        /*busca si
    existen usuarios con el apellido especificado (total o parcialmente).*/

        List<Usuario> coincidenciasapellido = new ArrayList<>();

        // Esta verificacion ahorra recorrer el ArrayList productos innecesariamente
        if (apellido == null) {
            return coincidenciasapellido;
        }

        for (Usuario p : usuarios) {
            if (p.verApellido().toLowerCase().contains(apellido.toLowerCase())) {
                coincidenciasapellido.add(p);
            }
        }

        Collections.sort(coincidenciasapellido, new CompUsuarioApYNom());

        return coincidenciasapellido;
    }

    public boolean existeEsteUsuario(Usuario usuario) /*devuelve true si existe el usuario especificado, false en caso contrario.*/ {
        return usuarios.contains(usuario);
    }

    public Usuario obtenerUsuario(String correo)/* obtiene el usuario con el correo especificado. Si no hay un usuario con el correo, devuelve null.*/ {
        for (Usuario u : usuarios) {

            if (u.verCorreo().equalsIgnoreCase(correo)) {
                return u;
            }
        }
        return null;
    }

    public String borrarUsuario(Usuario usuario) {

        GestorPedidos gp = GestorPedidos.instanciar();

        if (!usuarios.contains(usuario)) {
            return USUARIO_INEXISTENTE;
        }

        // añado un control sobre si el usuario es un Cliente
        if (usuario instanceof Cliente) {
            if (!gp.hayPedidosConEsteCliente((Cliente) usuario)) {
                usuarios.remove(usuario);
                this.escribir();
                return EXITO;
            } else {
                
                return ERROR_PERMISOS;
            }
        } else {
            usuarios.remove(usuario);
            this.escribir();
            return EXITO;
        }

    }

    private void escribir() {
        BufferedWriter bw = null;

        File f = new File(ARCHIVO_USUARIOS);

        try {
            FileWriter fw = new FileWriter(f);
            bw = new BufferedWriter(fw);

            // recorro el ArrayList para escribir los usuarios en el archivo de texto
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                String linea;

                // comienzo el archivo con el perfil de cada usuario...
                linea = u.toString() + SEPARADOR;
                linea += u.verCorreo() + SEPARADOR;
                linea += u.verContrasenia() + SEPARADOR;
                linea += u.verApellido() + SEPARADOR;
                linea += u.verNombre();

                bw.write(linea);

                if (i < this.usuarios.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de entrada o salida de datos");
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    private void leer(String nombreArchivo) {
        BufferedReader br = null;

        File f = new File(nombreArchivo);

        if (f.exists()) {
            try {
                FileReader fr = new FileReader(f);
                br = new BufferedReader(fr);
                String linea;

                while ((linea = br.readLine()) != null) {
                    String[] vectorUsuario = linea.split(SEPARADOR);
                    Perfil perfil = Perfil.valueOf(vectorUsuario[0]);
                    String correo = vectorUsuario[1];
                    String contrasenia = vectorUsuario[2];
                    String apellido = vectorUsuario[3];
                    String nombre = vectorUsuario[4];

                    Usuario usuario;
                    switch (perfil) {
                        case CLIENTE:
                            usuario = new Cliente(correo, contrasenia, apellido, nombre);
                            break;
                        case EMPLEADO:
                            usuario = new Empleado(correo, contrasenia, apellido, nombre);
                            break;
                        default:
                            usuario = new Encargado(correo, contrasenia, apellido, nombre);
                            break;
                    }

                    this.usuarios.add(usuario);
                }
            } catch (FileNotFoundException fnf) {
                System.out.println("No se pudo encontrar el archivo para abrirlo");
            } catch (IOException fr) {
                System.out.println("No se pudo leer el archivo.");
            } catch (IllegalArgumentException fnf) {
                System.out.println("El argumento que ha ingresado no es válido");
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException fr) {
                        fr.printStackTrace();
                    }
                }
            }
        }
    }

   
    public String modificarUsuario(String correoUsuarioAModificar, String apellido, String nombre, Perfil perfil, String clave, String claveRepetida) {

        String salida = verificarDatos(correoUsuarioAModificar, apellido, nombre, perfil, clave, claveRepetida);

        if (!salida.equals(VALIDACION_EXITO)) {
            return salida;
        }

        int indiceUsuarioAModificar = this.usuarios.indexOf(this.obtenerUsuario(correoUsuarioAModificar));

        switch (perfil) {
            case CLIENTE:
                Usuario cliente = new Cliente(correoUsuarioAModificar, clave, apellido, nombre);
                this.usuarios.set(indiceUsuarioAModificar, cliente);
                break;

            case EMPLEADO:
                Usuario empleado = new Empleado(correoUsuarioAModificar, clave, apellido, nombre);
                this.usuarios.set(indiceUsuarioAModificar, empleado);
                break;

            // default para case ENCARGADO (sino hay que poner return para el método)
            default:
                Usuario encargado = new Encargado(correoUsuarioAModificar, clave, apellido, nombre);
                this.usuarios.set(indiceUsuarioAModificar, encargado);
                break;
        }

        this.escribir();

        return EXITO;
    }
}
