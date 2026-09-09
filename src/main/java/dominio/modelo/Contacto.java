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

    public byte getCantidadContactos() {
        return cantidadContactos;
    }

    public void setCantidadContactos(final byte cantidadContactos) {
        this.cantidadContactos = cantidadContactos;
    }

    public Nombre getNombre(final byte indice) {
        return nombres[indice];
    }

    public void setNombre(
            final byte indice,
            final Nombre nombre) {
        nombres[indice] = nombre;
    }

    public Id getId(final byte indice) {
        return ids[indice];
    }

    public void setId(
            final byte indice,
            final Id id) {
        ids[indice] = id;
    }

    public Telefono getTelefono(final byte indice) {
        return telefonos[indice];
    }

    public void setTelefono(
            final byte indice,
            final Telefono telefono) {
        telefonos[indice] = telefono;
    }

    public Correo getCorreo(final byte indice) {
        return correos[indice];
    }

    public void setCorreo(
            final byte indice,
            final Correo correo) {
        correos[indice] = correo;
    }
}
