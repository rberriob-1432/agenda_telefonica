package aplicacion.puertos.entrada;

import aplicacion.servicios.dto.query.ConseguirContactoPorIdQuery;
import dominio.modelo.Contacto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ConseguirContactoPorIdCasoUso {

    Contacto execute(
            @NotNull @Valid ConseguirContactoPorIdQuery query
    );

}
