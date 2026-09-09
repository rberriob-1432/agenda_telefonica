package aplicacion.servicios.dto.query;

import jakarta.validation.constraints.NotBlank;

public record ConseguirContactoPorIdQuery(
        @NotBlank(message = "El id no puede ser nulo")
        String id
) {
}