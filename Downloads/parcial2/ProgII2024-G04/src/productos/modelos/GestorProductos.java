package productos.modelos;

import interfaces.IGestorProductos;
import static interfaces.IGestorProductos.ERROR_CATEGORIA;
import static interfaces.IGestorProductos.ERROR_CODIGO;
import static interfaces.IGestorProductos.ERROR_DESCRIPCION;
import static interfaces.IGestorProductos.ERROR_ELIMINACION;
import static interfaces.IGestorProductos.ERROR_ESTADO;
import static interfaces.IGestorProductos.ERROR_PRECIO;
import static interfaces.IGestorProductos.EXITO;
import static interfaces.IGestorProductos.EXITO_PRODUCTO;
import static interfaces.IGestorProductos.PRODUCTOS_DUPLICADOS;
import static interfaces.IGestorProductos.PRODUCTO_INEXISTENTE;
import static interfaces.IGestorProductos.VALIDACION_EXITO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pedidos.modelos.GestorPedidos;
import pedidos.modelos.Pedido;
import pedidos.modelos.ProductoDelPedido;

public class GestorProductos implements IGestorProductos {

    private List<Producto> productos = new ArrayList<>();
    private String archivoProductos = "archivoProductos"; // Nombre del archivo para persistencia
    private static GestorProductos gestor; // Instancia única del Singleton
    public static final String SEPARADOR = " - "; // Separador utilizado en el archivo para dividir los campos

    // Constructor privado para garantizar que solo haya una instancia (Singleton)
    private GestorProductos() {
        this.leerDesdeArchivo(); // Llama a la lectura del archivo al inicializar
    }

    // Método para obtener la instancia única (Singleton)
    public static GestorProductos instanciar() {
        if (gestor == null) {
            gestor = new GestorProductos(); // Si no existe, crea la instancia
        }
        return gestor;
    }

    @Override
    public String crearProducto(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        // Validación de datos antes de crear el producto
        if (!validarDatos(codigo, descripcion, precio, categoria, estado).equals(VALIDACION_EXITO)) {
            return validarDatos(codigo, descripcion, precio, categoria, estado);
        }

        Producto nuevoProducto = new Producto(codigo, descripcion, categoria, estado, precio);

        // Verifica si el producto ya existe en la lista
        if (!productos.contains(nuevoProducto)) {
            productos.add(nuevoProducto); // Agrega el producto a la lista
            escribirEnArchivo(); // Guarda los cambios en el archivo
            return EXITO;
        } else {
            return PRODUCTOS_DUPLICADOS; // Si ya existe, devuelve un mensaje de error
        }
    }

    @Override
    public String modificarProducto(Producto productoAModificar, int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        // Validación de los nuevos datos antes de modificar el producto
        if (!validarDatos(codigo, descripcion, precio, categoria, estado).equals(VALIDACION_EXITO)) {
            return validarDatos(codigo, descripcion, precio, categoria, estado);
        }

        // Verifica si el producto existe en la lista
        if (!productos.contains(productoAModificar)) {
            return PRODUCTO_INEXISTENTE; // Si no existe, devuelve un mensaje de error
        } else {
            // Modifica los datos del producto
            //productoAModificar.asignarCodigo(codigo);
            productoAModificar.asignarDescripcion(descripcion);
            productoAModificar.asignarPrecio(precio);
            productoAModificar.asignarCategoria(categoria);
            productoAModificar.asignarEstado(estado);

            escribirEnArchivo(); // Guarda los cambios en el archivo
            return EXITO;
        }
    }

    @Override
    public List<Producto> menu() {

        Collections.sort(productos, new CompProductoCatYDesc());

        return productos;
    }

   public List<Producto> buscarProductos(String descripcion) {

        List<Producto> coincidenciasDescripcion = new ArrayList<>();

        // Esta verificacion ahorra recorrer el ArrayList productos innecesariamente
        if (descripcion == null) {
            return coincidenciasDescripcion;
        }

        for (Producto p : productos) {
            if (p.toString().toLowerCase().contains(descripcion.toLowerCase())) {
                coincidenciasDescripcion.add(p);
            }
        }

        Collections.sort(coincidenciasDescripcion, new CompProductoCatYDesc());

        return coincidenciasDescripcion;
    }


    @Override
    public boolean existeEsteProducto(Producto producto) {
        // Verifica si el producto existe en la lista
        return productos.contains(producto);
    }

    public List<Producto> verProductosPorCategoria(Categoria categoria) {

        List<Producto> productosCategoriaCoincidente = new ArrayList<>();

        if (categoria == null) {
            return productosCategoriaCoincidente;
        }

        for (Producto p : productos) {
            if (p.verCategoria().equals(categoria)) {
                productosCategoriaCoincidente.add(p);
            }
        }

        Collections.sort(productos, new CompProductoDesc());

        return productosCategoriaCoincidente;
    }
    @Override
    public Producto obtenerProducto(Integer codigo) {
        // Obtiene un producto por su código
        for (Producto producto : productos) {
            if (producto.verCodigo() == codigo) {
                return producto;
            }
        }
        return null; // Si no lo encuentra, devuelve null
    }

    @Override
    public String borrarProducto(Producto producto) {
        // Comprueba si el producto está en algún pedido antes de permitir su eliminación
        GestorPedidos gestorPedidos = GestorPedidos.instanciar();
        boolean estaEnPedidos = false;

        // Verifica si el producto está en algún pedido
        for (Pedido pedido : gestorPedidos.verPedidos()) {
            for (ProductoDelPedido productoPedido : pedido.verProductoDelPedido()) {
                if (productoPedido.verProducto().equals(producto)) {
                    estaEnPedidos = true;
                    break;
                }
            }
        }

        if (!productos.contains(producto)) {
            return PRODUCTO_INEXISTENTE; // Si no existe, devuelve un mensaje de error
        }

        if (!estaEnPedidos) {
            productos.remove(producto); // Elimina el producto de la lista
            escribirEnArchivo(); // Guarda los cambios en el archivo
            return EXITO_PRODUCTO;
        } else {
            return ERROR_ELIMINACION; // Si el producto está en un pedido, no puede eliminarse
        }
    }

    private String validarDatos(int codigo, String descripcion, float precio, Categoria categoria, Estado estado) {
        // Valida los datos del producto
        if (codigo <= 0) return ERROR_CODIGO;
        if (descripcion == null || descripcion.isEmpty()) return ERROR_DESCRIPCION;
        if (precio <= 0) return ERROR_PRECIO;
        if (categoria == null) return ERROR_CATEGORIA;
        if (estado == null) return ERROR_ESTADO;
        return VALIDACION_EXITO;
    }

    // Método para escribir los productos en el archivo de texto
    private void escribirEnArchivo() {
        try {
            File archivo = new File(archivoProductos); // Crea un objeto File para el archivo

            // Verifica y crea los directorios necesarios si no existen
            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs();
            }

            // Si el archivo no existe, lo crea
            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            // Escribe los productos en el archivo
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
                for (Producto producto : productos) {
                    writer.write(producto.verCodigo() + SEPARADOR +
                            producto.verDescripcion() + SEPARADOR +
                            producto.verCategoria() + SEPARADOR +
                            producto.verEstado() + SEPARADOR +
                            producto.verPrecio());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            // Manejo de excepciones si hay errores al escribir en el archivo
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para leer los productos desde el archivo de texto
    private void leerDesdeArchivo() {
        File archivo = new File(archivoProductos); // Crea un objeto File para el archivo
        if (!archivo.exists()) {
            System.out.println(CREACION_OK); // Si el archivo no existe, muestra un mensaje
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(SEPARADOR); // Separa los datos por el delimitador
                int codigo = Integer.parseInt(datos[0]); // Extrae el código
                String descripcion = datos[1]; // Extrae la descripción
                Categoria categoria;
                try {
                    // Mapea los valores del archivo al enum Categoria
                    switch (datos[2].toLowerCase()) {
                        case "entrada":
                            categoria = Categoria.ENTRADA;
                            break;
                        case "plato principal":
                            categoria = Categoria.PLATO_PRINCIPAL;
                            break;
                        case "postre":
                            categoria = Categoria.POSTRE;
                            break;
                        default:
                            throw new IllegalArgumentException("Categoría no válida: " + datos[2]);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Categoría no válida: " + datos[2]);
                    continue; // Si la categoría es inválida, salta al siguiente producto
                }
                Estado estado;
                try {
                    // Mapea los valores del archivo al enum Estado
                    switch (datos[3].toLowerCase()) {
                        case "disponible":
                            estado = Estado.DISPONIBLE;
                            break;
                        case "no disponible":
                            estado = Estado.NO_DISPONIBLE;
                            break;
                        default:
                            throw new IllegalArgumentException("Estado no válido: " + datos[3]);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Estado no válido: " + datos[3]);
                    continue; // Si el estado es inválido, salta al siguiente producto
                }

                float precio = Float.parseFloat(datos[4]); // Extrae el precio

                Producto producto = new Producto(codigo, descripcion, categoria, estado, precio);
                productos.add(producto); // Añade el producto a la lista
            }
            System.out.println(LECTURA_OK); // Mensaje de éxito al finalizar la lectura
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(LECTURA_ERROR + ": " + e.getMessage()); // Manejo de errores
        }
    }
}

