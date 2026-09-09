package aplicacion.puertos.entrada;

import aplicacion.servicios.dto.comando.ActualizarContactoComando;
import dominio.modelo.Contacto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ActualizarContactoCasoUso {
    Contacto execute(@NotNull @Valid ActualizarContactoComando command);
}