/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios.modelos;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Fabri
 */
public class ModeloComboPerfil extends DefaultComboBoxModel {

    /**
     * Constructor
     */
    public ModeloComboPerfil() {
        for (Perfil perfil : Perfil.values()) {
            this.addElement(perfil);
        }
    }

    /**
     * Devuelve la categoria seleccionada
     *
     * @return Categoria - categoría seleccionada
     */
    public Perfil obtenerPerfil() {
        return (Perfil) this.getSelectedItem();
    }

    /**
     * Selecciona la Categoria especificada
     *
     * @param perfil categoría
     */
    public void seleccionarPerfil(Perfil perfil) {
        this.setSelectedItem(perfil);
    }
}
