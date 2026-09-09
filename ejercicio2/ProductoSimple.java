package ejercicio2_ventas;

public class ProductoSimple implements IDetalleVenta {
    private String descripcion;
    private int cantidad;
    private double precioUnitario;

    public ProductoSimple(String descripcion, int cantidad, double precioUnitario) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public double getPrecio() {
        return precioUnitario * cantidad;
    }
}