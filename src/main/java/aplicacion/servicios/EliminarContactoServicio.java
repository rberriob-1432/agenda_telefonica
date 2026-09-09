package aplicacion.servicios;

import aplicacion.puertos.entrada.EliminarContactoCasoUso;
import aplicacion.puertos.salida.ConseguirContactoPorIdPuerto;
import aplicacion.puertos.salida.EliminarContactoPuerto;
import aplicacion.servicios.dto.comando.EliminarContactoComando;
import aplicacion.servicios.dto.mapeador.ContactoAplicacionMapeador;
import dominio.excepciones.InvalidoIdExcepcion;
import dominio.ov.Id;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class EliminarContactoServicio
        implements EliminarContactoCasoUso {
    private final EliminarContactoPuerto eliminarContactoPuerto;
    private final ConseguirContactoPorIdPuerto conseguirContactoPorIdPuerto;
    private final Validator validator;

    @Override
    public void execute(
            final EliminarContactoComando comando) {

        validateCommand(comando);

        final Id id =
                ContactoAplicacionMapeador
                        .fromDeleteCommandToId(comando);

        ensureContactoExists(id);

        eliminarContactoPuerto.delete(id);
    }

    private void validateCommand(
            final EliminarContactoComando comando) {

        final Set<ConstraintViolation<EliminarContactoComando>> violations =
                validator.validate(comando);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureContactoExists(
            final Id id) {

        conseguirContactoPorIdPuerto
                .getById(id)
                .orElseThrow(
                        () -> InvalidoIdExcepcion
                                .becauseValueIsEmpty()
                );
    }
}
