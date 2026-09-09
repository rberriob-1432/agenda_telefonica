package aplicacion.puertos.entrada;
import aplicacion.servicios.dto.comando.AgregarContactoComando;
import dominio.modelo.Contacto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CrearContactoCasoUso {
    Contacto execute(@NotNull @Valid AgregarContactoComando comando);
}