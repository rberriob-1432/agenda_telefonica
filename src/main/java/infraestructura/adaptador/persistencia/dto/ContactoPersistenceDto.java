package infraestructura.adaptador.persistencia.dto;

public record ContactoPersistenceDto(
        String id,
        String nombre,
        String telefono,
        String correo) {
}

