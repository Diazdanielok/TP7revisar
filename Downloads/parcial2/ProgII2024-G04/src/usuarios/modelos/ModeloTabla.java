/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import interfaces.IGestorUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DANIEL
 */
public class ModeloTabla extends AbstractTableModel {

    // IMPLEMENTAR
    private List<Usuario> usuarios = new ArrayList<>();
    private List<String> nombresColumnas = new ArrayList<>();

    public ModeloTabla() {
        this.nombresColumnas.add("Apellido");
        this.nombresColumnas.add("Nombre");
        this.nombresColumnas.add("Perfil");

        IGestorUsuarios gu = GestorUsuarios.instanciar();

        this.usuarios = gu.verUsuarios();
    }

    @Override
    public int getRowCount() {
        return this.usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumnas.size();
    }

    @Override
    public Object getValueAt(int fila, int columna) {

        Usuario usuario = usuarios.get(fila);

        switch (columna) {
            case 0:
                return usuario.verApellido();
            case 1:
                return usuario.verNombre();
            case 2:
                if (usuario instanceof Cliente) {
                    return Perfil.CLIENTE.toString();
                } else if (usuario instanceof Empleado) {
                    return Perfil.EMPLEADO.toString();
                } else {
                    return Perfil.ENCARGADO.toString();
                }
        }

        return "Error";
    }

    @Override
    public String getColumnName(int columna) {
        return this.nombresColumnas.get(columna);
    }

    public void actualizarDatosTabla() {
        IGestorUsuarios gu = GestorUsuarios.instanciar();

        this.usuarios = gu.verUsuarios();

        // TableModel  notifica al JTable que debe actualizar los datos
        this.fireTableDataChanged();
    }

    // Para sobrecargar el  método actualizarDatosTabla()
    public void actualizarDatosTabla(List<Usuario> usuarios) {
        this.usuarios = usuarios;
        this.fireTableDataChanged();
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public void asignarUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
