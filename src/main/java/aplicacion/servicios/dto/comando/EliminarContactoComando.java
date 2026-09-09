package aplicacion.servicios.dto.comando;

import jakarta.validation.constraints.NotBlank;

public record EliminarContactoComando(
        @NotBlank(message = "nombre no puede ser nulo") String nombre
) {

}