package ejercicio1proxy;

public class Usuario {
    private String nombre;
    private boolean tieneAcceso;

    public Usuario(String nombre, boolean tieneAcceso) {
        this.nombre = nombre;
        this.tieneAcceso = tieneAcceso;
    }

    public boolean tieneAcceso() {
        return tieneAcceso;
    }

    public String getNombre() {
        return nombre;
    }
}