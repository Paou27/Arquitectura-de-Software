package ejercicio1_proxy;

public class LibroReal implements Libro {
    private String titulo;
    private String autor;
    private int anio;
    private String fechaPublicacion; // Restricción: Debe ser fecha en formato obligatorio
    private String contenido;

    public LibroReal(String titulo, String autor, int anio, String fechaPublicacion, String contenido) {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        setFechaPublicacion(fechaPublicacion);
        this.contenido = contenido;
    }

    // Validación estricta de fecha (Formato DD/MM/AAAA)
    public void setFechaPublicacion(String fecha) {
        if (fecha == null || !fecha.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            throw new IllegalArgumentException("Error: La fecha debe ingresarse sí o sí en formato DD/MM/AAAA (Ejemplo: 15/08/2026).");
        }
        this.fechaPublicacion = fecha;
    }

    public void mostrar() {
        System.out.println("\n==================================================");
        System.out.println("LIBRO: " + titulo + " (" + anio + ")");
        System.out.println("Autor: " + autor);
        System.out.println("Fecha de Publicación: " + fechaPublicacion);
        System.out.println("--------------------------------------------------");
        System.out.println("Contenido: " + contenido);
        System.out.println("==================================================\n");
    }

    @Override
    public void leer() {
        mostrar();
    }
}