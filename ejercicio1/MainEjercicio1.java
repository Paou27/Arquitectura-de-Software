package ejercicio1_proxy;

import java.util.Scanner;

public class MainEjercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SISTEMA DE REGISTRO DE LIBRO Y ACCESO PROXY ===");
        
        // 1. INGRESO DE DATOS DEL LIBRO CON VALIDACIÓN DE FECHA OBLIGATORIA
        LibroReal libro = null;
        while (libro == null) {
            try {
                System.out.print("Ingrese el título del libro: ");
                String titulo = scanner.nextLine();

                System.out.print("Ingrese el autor del libro: ");
                String autor = scanner.nextLine();

                System.out.print("Ingrese el año de edición: ");
                int anio = Integer.parseInt(scanner.nextLine());

                System.out.print("Ingrese la fecha de publicación (Obligatorio DD/MM/AAAA, ej: 15/08/2026): ");
                String fecha = scanner.nextLine();

                System.out.print("Ingrese el contenido del libro: ");
                String contenido = scanner.nextLine();

                // Intentar instanciar el libro (valida la fecha internamente)
                libro = new LibroReal(titulo, autor, anio, fecha, contenido);
            } catch (Exception e) {
                System.out.println("\n[ERROR] " + e.getMessage() + " Intente de nuevo.\n");
            }
        }

        // 2. INGRESO DE DATOS DEL USUARIO CON VALIDACIÓN DE CI (Formato 'CI' y límite de dígitos)
        Usuario usuario = null;
        while (usuario == null) {
            try {
                System.out.println("\n--- REGISTRO DE USUARIO ---");
                System.out.print("Ingrese el nombre del usuario: ");
                String nombre = scanner.nextLine();

                System.out.print("Ingrese el documento de identidad (debe incluir 'CI' y entre 5 y 8 números, Ej: CI 987661): ");
                String ci = scanner.nextLine();

                System.out.print("¿Tiene permisos de acceso al libro? (true/false): ");
                boolean tieneAcceso = Boolean.parseBoolean(scanner.nextLine());

                // Intentar instanciar el usuario (valida el formato de CI internamente)
                usuario = new Usuario(nombre, ci, tieneAcceso);
            } catch (Exception e) {
                System.out.println("\n[ERROR] " + e.getMessage() + " Intente de nuevo.\n");
            }
        }

        // 3. EJECUCIÓN A TRAVÉS DEL PROXY
        System.out.println("\n--- EJECUTANDO ACCESO VÍA PROXY ---");
        Libro proxy = new ProxyLibro(libro, usuario);
        proxy.leer();
    }
}