package ejercicio2_ventas;

import java.util.ArrayList;

public class Venta {
    private String nombre;
    private String fecha;
    private String tipoDocumento;
    private String numeroDocumento;
    private ArrayList<IDetalleVenta> detalles;

    public Venta(String nombre, String fecha, String tipoDocumento, String numeroDocumento) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.detalles = new ArrayList<>();
    }

    public void agregarDetalle(IDetalleVenta detalle) {
        detalles.add(detalle);
    }

    public double calcularTotal() {
        double total = 0;
        for (IDetalleVenta d : detalles) {
            total += d.getPrecio();
        }
        return total;
    }

    public void mostrarDetalleVenta(String formaPago) {
        System.out.println("\n=============================================");
        System.out.println("              DETALLE DE VENTA               ");
        System.out.println("=============================================");
        System.out.println("Cliente: " + nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Documento: " + tipoDocumento + " - " + numeroDocumento);
        System.out.println("---------------------------------------------");
        System.out.println("PRODUCTOS:");
        for (IDetalleVenta d : detalles) {
            System.out.println(" - " + d.getDescripcion() + " | Cant: " + d.getCantidad() + " | Subtotal: Bs. " + d.getPrecio());
        }
        System.out.println("---------------------------------------------");
        System.out.println("Forma de Pago Seleccionada: " + formaPago);
        System.out.println("TOTAL A COBRAR: Bs. " + calcularTotal());
        System.out.println("=============================================\n");
    }
}