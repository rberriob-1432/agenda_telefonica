package aplicacion.puertos.entrada;
import aplicacion.servicios.dto.comando.EliminarContactoComando;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface EliminarContactoCasoUso {
    void execute(@NotNull @Valid EliminarContactoComando comando);
}