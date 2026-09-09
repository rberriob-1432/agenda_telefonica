package infraestructura.adaptador.persistencia.entidad;

public record ContactoEntidad(
        String id,
        String nombre,
        String telefono,
        String correo) {
}