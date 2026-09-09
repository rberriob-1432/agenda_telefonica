package aplicacion.puertos.entrada;

import aplicacion.servicios.dto.ContactoRespuestaDto;
import aplicacion.servicios.dto.comando.BuscarContactosComando;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface BuscarContactosCasoUso {

    List<ContactoRespuestaDto> execute(
            @NotNull @Valid BuscarContactosComando comando
    );
}