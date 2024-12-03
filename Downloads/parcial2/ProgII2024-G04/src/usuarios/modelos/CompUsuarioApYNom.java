/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import java.util.Comparator;

/**
 *
 * @author DANIEL
 */
public class CompUsuarioApYNom implements Comparator<Usuario> {

    @Override
    public int compare(Usuario t, Usuario t1) {
        if (t.verApellido().compareToIgnoreCase(t1.verApellido()) == 0) {
            return t.verNombre().compareToIgnoreCase(t1.verNombre());
        } else {
            return t.verApellido().compareToIgnoreCase(t1.verApellido());
        }
    }

}
