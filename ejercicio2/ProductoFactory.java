package ejercicio2_ventas;

public class ProductoFactory {

    public static IDetalleVenta crearProductoSimple(String descripcion, int cantidad, double precioUnitario) {
        return new ProductoSimple(descripcion, cantidad, precioUnitario);
    }

    public static ProductoCompuesto crearProductoCompuesto(String descripcion, int cantidad) {
        return new ProductoCompuesto(descripcion, cantidad);
    }
}