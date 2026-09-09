package aplicacion.servicios.dto.comando;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BuscarContactosComando(

        @NotBlank
        String criterio,

        @NotNull
        TipoBusqueda tipoBusqueda

) {
}