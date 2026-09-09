package aplicacion.puertos.entrada;

import aplicacion.servicios.dto.ContactoRespuestaDto;
import aplicacion.servicios.dto.query.ConseguirContactoPorIdQuery;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ConseguirContactoPorIdCasoUso {

    ContactoRespuestaDto execute(
            @NotNull @Valid ConseguirContactoPorIdQuery query
    );

}