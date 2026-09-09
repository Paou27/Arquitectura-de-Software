package ejercicio1_proxy;

public class ProxyLibro implements Libro {
    private LibroReal libroReal;
    private Usuario usuario;

    public ProxyLibro(LibroReal libroReal, Usuario usuario) {
        this.libroReal = libroReal;
        this.usuario = usuario;
    }

    @Override
    public void leer() {
        System.out.println("Verificando permisos para el usuario: " + usuario.getNombre() + " (" + usuario.getCi() + ")...");
        
        if (usuario.tieneAcceso()) {
            System.out.println(">> Acceso concedido.");
            // Invoca al método mostrar() según requerimiento del docente
            libroReal.mostrar();
        } else {
            System.out.println(">> Acceso denegado: El usuario no cuenta con los permisos necesarios.");
        }
    }
}