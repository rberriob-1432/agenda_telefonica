package aplicacion.servicios.dto.comando;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AgregarContactoComando(

        @NotBlank(message = "nombre no puede estar vacío")
        @Size(min = 3, message = "nombre debe tener al menos 3 caracteres")
        String nombre,

        @NotBlank(message = "telefono no puede estar vacío")
        String telefono,

        @NotBlank(message = "correo no puede estar vacío")
        @Email(message = "correo debe tener un formato válido")
        String correo

) {
}