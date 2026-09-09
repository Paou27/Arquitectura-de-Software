package ejercicio2_ventas;

import java.util.Scanner;

public class VentaFacade {
    private Venta ventaActual;
    private Scanner scanner;

    public VentaFacade() {
        scanner = new Scanner(System.in);
    }

    public void iniciarSistema() {
        System.out.println("=== SISTEMA DE VENTAS MINISUPERMERCADO ===");
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Fecha (DD/MM/AAAA): ");
        String fecha = scanner.nextLine();
        System.out.print("Tipo de documento (CI/NIT): ");
        String tipoDoc = scanner.nextLine();
        System.out.print("Número de documento: ");
        String numDoc = scanner.nextLine();

        ventaActual = new Venta(cliente, fecha, tipoDoc, numDoc);

        int opcion = 0;
        do {
            System.out.println("\n--- MENÚ DE OPCIONES ---");
            System.out.println("1. Agregar Producto Simple");
            System.out.println("2. Agregar Producto Compuesto (Combo)");
            System.out.println("3. Vender (Procesar cobro)");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarProductoSimple();
                    break;
                case 2:
                    agregarProductoCompuesto();
                    break;
                case 3:
                    procesarVenta();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 3 && opcion != 4);
    }

    private void agregarProductoSimple() {
        System.out.print("Descripción del producto: ");
        String desc = scanner.nextLine();
        System.out.print("Cantidad: ");
        int cant = Integer.parseInt(scanner.nextLine());
        System.out.print("Precio unitario: ");
        double precio = Double.parseDouble(scanner.nextLine());

        IDetalleVenta producto = ProductoFactory.crearProductoSimple(desc, cant, precio);
        ventaActual.agregarDetalle(producto);
        System.out.println("¡Producto agregado correctamente!");
    }

    private void agregarProductoCompuesto() {
        System.out.print("Nombre del Combo/Pack: ");
        String nombreCombo = scanner.nextLine();
        System.out.print("Cantidad de combos: ");
        int cantCombo = Integer.parseInt(scanner.nextLine());

        ProductoCompuesto combo = ProductoFactory.crearProductoCompuesto(nombreCombo, cantCombo);

        System.out.print("¿Cuántos productos simples integran este combo?: ");
        int numProd = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numProd; i++) {
            System.out.println("  Producto #" + (i + 1) + " del combo:");
            System.out.print("  - Descripción: ");
            String desc = scanner.nextLine();
            System.out.print("  - Cantidad interna: ");
            int cant = Integer.parseInt(scanner.nextLine());
            System.out.print("  - Precio unitario: ");
            double precio = Double.parseDouble(scanner.nextLine());

            combo.agregarProducto(ProductoFactory.crearProductoSimple(desc, cant, precio));
        }

        ventaActual.agregarDetalle(combo);
        System.out.println("¡Combo agregado correctamente!");
    }

    private void procesarVenta() {
        System.out.println("\n--- ELIJA EL TIPO DE PAGO ---");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta de crédito");
        System.out.println("3. Transferencia bancaria");
        System.out.print("Opción: ");
        int opc = Integer.parseInt(scanner.nextLine());

        String tipoPago = "";
        switch (opc) {
            case 1: tipoPago = "Efectivo"; break;
            case 2: tipoPago = "Tarjeta de crédito"; break;
            case 3: tipoPago = "Transferencia bancaria"; break;
            default: tipoPago = "Efectivo (Por defecto)";
        }

        ventaActual.mostrarDetalleVenta(tipoPago);
    }
}