package dominio.modelo;
import dominio.ov.Correo;
import dominio.ov.Id;
import dominio.ov.Nombre;
import dominio.ov.Telefono;
public class Contacto {

    private static final byte MAX_CONTACTOS = 100;

    private final Nombre[] nombres;
    private final Telefono[] telefonos;
    private final Correo[] correos;
    private final Id[] ids;

    private byte cantidadContactos;

    public Contacto() {
        this.nombres = new Nombre[MAX_CONTACTOS];
        this.telefonos = new Telefono[MAX_CONTACTOS];
        this.correos = new Correo[MAX_CONTACTOS];
        this.ids = new Id[MAX_CONTACTOS];
        this.cantidadContactos = 0;
    }

    public void agregarContacto(
            final Nombre nombre,
            final Telefono telefono,
            final Correo correo,
            final Id id) {
        if (cantidadContactos >= MAX_CONTACTOS) {
            throw new IllegalStateException("La agenda está llena");
        }

        nombres[cantidadContactos] = nombre;
        telefonos[cantidadContactos] = telefono;
        correos[cantidadContactos] = correo;
        ids[cantidadContactos] = id;

        cantidadContactos++;
    }

    public byte getCantidadContactos() {
        return cantidadContactos;
    }

    public Nombre getNombre(final byte indice) {
        return nombres[indice];
    }

    public Id getId(final byte indice) {
        return ids[indice];
    }

    public Telefono getTelefono(final byte indice) {
        return telefonos[indice];
    }

    public Correo getCorreo(final byte indice) {
        return correos[indice];
    }
}