package aplicacion.puertos.entrada;
import dominio.modelo.Contacto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ConseguirContactoPorId {
    Contacto execute(@NotNull @Valid ConseguirContactoPorId query);
}