package ejercicio2_ventas;

import java.util.ArrayList;
import java.util.List;

public class ProductoCompuesto implements IDetalleVenta {
    private String descripcion;
    private int cantidad;
    private List<IDetalleVenta> subProductos;

    public ProductoCompuesto(String descripcion, int cantidad) {
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.subProductos = new ArrayList<>();
    }

    public void agregarProducto(IDetalleVenta producto) {
        subProductos.add(producto);
    }

    @Override
    public String getDescripcion() {
        return descripcion + " (Combo)";
    }

    @Override
    public int getCantidad() {
        return cantidad;
    }

    @Override
    public double getPrecio() {
        double subtotal = 0;
        for (IDetalleVenta p : subProductos) {
            subtotal += p.getPrecio();
        }
        return subtotal * cantidad;
    }
}