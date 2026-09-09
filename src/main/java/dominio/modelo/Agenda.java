package dominio.modelo;
public class Agenda {

    private static final byte MAX_CONTACTOS = 100;

    private final String[] nombres;
    private final String[] telefonos;
    private final String[] correos;

    private byte cantidadContactos;

    public Agenda() {
        this.nombres = new String[MAX_CONTACTOS];
        this.telefonos = new String[MAX_CONTACTOS];
        this.correos = new String[MAX_CONTACTOS];
        this.cantidadContactos = 0;
    }

    public void agregarContacto(
            final String nombre,
            final String telefono,
            final String correo) {

        nombres[cantidadContactos] = nombre;
        telefonos[cantidadContactos] = telefono;
        correos[cantidadContactos] = correo;

        cantidadContactos++;
    }

    public byte getCantidadContactos() {
        return cantidadContactos;
    }

    public String getNombre(final byte indice) {
        return nombres[indice];
    }

    public String getTelefono(final byte indice) {
        return telefonos[indice];
    }

    public String getCorreo(final byte indice) {
        return correos[indice];
    }
}