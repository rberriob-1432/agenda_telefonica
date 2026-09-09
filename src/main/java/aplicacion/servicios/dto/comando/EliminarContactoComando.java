package aplicacion.servicios.dto.comando;

import jakarta.validation.constraints.NotBlank;

public record EliminarContactoComando(
        @NotBlank(message = "id no puede ser nulo") String id
) {

}