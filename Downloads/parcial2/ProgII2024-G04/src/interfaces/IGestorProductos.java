/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;


import java.util.List;
import productos.modelos.Categoria;
import productos.modelos.Estado;
import productos.modelos.Producto;

/**
 *
 * @author fabri
 */
public interface IGestorProductos {
    
    public static final String EXITO = "Producto creado/modificado con éxito";
    public static final String ERROR_CODIGO = "El código del producto es incorrecto";
    public static final String ERROR_DESCRIPCION = "La descripción del producto es incorrecta";
    public static final String ERROR_PRECIO = "El precio del producto es incorrecto";
    public static final String ERROR_CATEGORIA = "La categoría del producto es incorrecta";
    public static final String ERROR_ESTADO = "El precio del producto es incorrecto";
    public static final String PRODUCTOS_DUPLICADOS = "Ya existe un producto con ese código";
    public static final String VALIDACION_EXITO = "Los datos del producto son correctos";
    public static final String PRODUCTO_INEXISTENTE = "No existe el producto especificado";
    public static final String EXITO_PRODUCTO = "El producto de borro con exito";
    public static final String ERROR_ELIMINACION = "El producto no pudo eliminarse";
    public static final String LECTURA_ERROR = "Error al leer los productos";
    public static final String CREACION_ERROR = "Error al crear el archivo de productos";
    public static final String LECTURA_OK = "Se pudieron leer los productos";
    public static final String CREACION_OK = "Se pudo crear el archivo de productos";
    public static final String ESCRITURA_OK = "Se pudieron guardar los productos";
    public static final String ESCRITURA_ERROR = "Error al guardar los productos";

 

    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado);

    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado);

    public List<Producto> menu();

    public List<Producto> buscarProductos(String descripcion);

    public String borrarProducto(Producto producto);

    public boolean existeEsteProducto(Producto producto);

    public List<Producto> verProductosPorCategoria(Categoria categoria);

    public Producto obtenerProducto(Integer codigo);    
    
}

