package aplicacion.servicios;

import aplicacion.puertos.entrada.ConseguirContactoPorIdCasoUso;
import aplicacion.puertos.salida.ConseguirContactoPorIdPuerto;
import aplicacion.servicios.dto.ContactoRespuestaDto;
import aplicacion.servicios.dto.mapeador.ContactoAplicacionMapeador;
import aplicacion.servicios.dto.query.ConseguirContactoPorIdQuery;
import dominio.excepciones.ContactoNoEncontradoException;
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
    private final Contacto contacto;

    @Override
    public ContactoRespuestaDto execute(
            final ConseguirContactoPorIdQuery query) {

        validateQuery(query);

        final Id id =
                ContactoAplicacionMapeador
                        .fromGetByIdQueryToId(query);

        final byte indice =
                conseguirContactoPorIdPuerto
                        .getIndicePorId(id)
                        .orElseThrow(
                                () -> ContactoNoEncontradoException
                                        .becauseIdWasNotFound(id.value())
                        );

        return new ContactoRespuestaDto(
                contacto.getId(indice).value(),
                contacto.getNombre(indice).value(),
                contacto.getTelefono(indice).value(),
                contacto.getCorreo(indice).value()
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