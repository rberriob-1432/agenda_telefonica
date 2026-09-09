package aplicacion.servicios.dto.comando;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record ActualizarContactoComando(
        @NotBlank(message = "id no debe ser vacio") String id,
        @NotBlank(message = "nombre no debe ser vacio")
        @Size(min = 3, message = "nombre debe tener al menos 3 caracteres")
        String nombre,
        @NotBlank(message = "correo no debe ser vacio")
        @Email(message = "correo debe tener un formato valido")
        String correo,
        @NotBlank(message = "telefono no puede estar vacío")
                String telefono

) {
}