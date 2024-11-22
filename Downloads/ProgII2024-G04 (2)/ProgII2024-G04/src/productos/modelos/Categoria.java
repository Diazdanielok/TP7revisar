/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package productos.modelos;

/**
 *
 * @author estudiante
 */
public enum Categoria {
    ENTRADA, PLATO_PRINCIPAL, POSTRE;

    @Override
    public String toString() {
        if (this.name().equals("ENTRADA")) {
            return "Entrada";
        } else if (this.name().equals("PLATO_PRINCIPAL")) {
            return "Plato principal";
        } else {
            return "Postre";
        }
    }
}
