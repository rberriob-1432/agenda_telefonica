package aplicacion.servicios.dto.query;

import jakarta.validation.constraints.NotBlank;

public record ConseguirContactoPorId(
        @NotBlank(message = "El id no puede ser nulo")
        String id
) {
}