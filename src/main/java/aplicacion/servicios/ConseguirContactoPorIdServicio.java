package aplicacion.servicios;

import aplicacion.puertos.entrada.ConseguirContactoPorIdCasoUso;
import aplicacion.puertos.salida.ConseguirContactoPorIdPuerto;
import aplicacion.servicios.dto.query.ConseguirContactoPorIdQuery;
import aplicacion.servicios.dto.mapeador.ContactoAplicacionMapeador;
import dominio.modelo.Contacto;
import dominio.ov.Id;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class ConseguirContactoPorIdServicio
        implements ConseguirContactoPorIdCasoUso {

    private final ConseguirContactoPorIdPuerto conseguirContactoPorIdPuerto;
    private final Validator validator;

    @Override
    public Contacto execute(
            final ConseguirContactoPorIdQuery query) {

        validateQuery(query);

        final Id id =
                ContactoAplicacionMapeador
                        .fromGetByIdQueryToId(query);

        return conseguirContactoPorIdPuerto
                .getById(id)
                .orElseThrow(
                        () -> new IllegalStateException(
                                "No existe un contacto con el ID: "
                                        + id.value()
                        )
                );
    }

    private void validateQuery(
            final ConseguirContactoPorIdQuery query) {

        final Set<ConstraintViolation<ConseguirContactoPorIdQuery>> violations =
                validator.validate(query);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
