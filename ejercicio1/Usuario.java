package ejercicio1_proxy;

public class Usuario {
    private String nombre;
    private String ci; // Debe incluir 'CI' o 'C.I.'
    private boolean tieneAcceso;

    public Usuario(String nombre, String ci, boolean tieneAcceso) {
        this.nombre = nombre;
        setCi(ci);
        this.tieneAcceso = tieneAcceso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCi() {
        return ci;
    }

    public boolean tieneAcceso() {
        return tieneAcceso;
    }

    // Validación del CI
    public void setCi(String ci) {
        String ciLimpio = ci.trim();
        
        // 1. Verificar si incluye la palabra "CI" o "C.I."
        if (!ciLimpio.toUpperCase().contains("CI")) {
            throw new IllegalArgumentException("Error: El documento debe incluir obligatoriamente el formato 'CI' (Ejemplo: CI 987661).");
        }

        // 2. Extraer solo los números para verificar el límite de dígitos
        String soloNumeros = ciLimpio.replaceAll("[^0-9]", "");
        
        // Límite de 5 a 8 dígitos numéricos (por ejemplo 987661)
        if (soloNumeros.length() < 5 || soloNumeros.length() > 8) {
            throw new IllegalArgumentException("Error: El número de CI debe tener entre 5 y 8 dígitos (Ejemplo: 987661).");
        }

        this.ci = ciLimpio;
    }
}