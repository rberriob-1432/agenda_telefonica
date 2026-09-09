
package aplicacion.servicios;
import aplicacion.puertos.entrada.ActualizarContactoCasoUso;
import aplicacion.puertos.salida.ActualizarContactoPuerto;
import aplicacion.puertos.salida.ConseguirContactoPorCorreoPuerto;
import aplicacion.puertos.salida.ConseguirContactoPorIdPuerto;
import aplicacion.servicios.dto.comando.ActualizarContactoComando;
import aplicacion.servicios.dto.mapeador.ContactoAplicacionMapeador;
import dominio.excepciones.ContactoNoEncontradoException;
import dominio.excepciones.CorreoYaRegistradoException;
import dominio.modelo.Contacto;
import dominio.ov.Correo;
import dominio.ov.Id;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public final class ActualizarContactoServicio
        implements ActualizarContactoCasoUso {

    private final ActualizarContactoPuerto actualizarContactoPuerto;
    private final ConseguirContactoPorIdPuerto conseguirContactoPorIdPuerto;
    private final ConseguirContactoPorCorreoPuerto conseguirContactoPorCorreoPuerto;
    private final Validator validator;
    private final Contacto contacto;

    @Override
    public Contacto execute(
            final ActualizarContactoComando comando) {

        validateCommand(comando);

        final Id id =
                ContactoAplicacionMapeador
                        .fromUpdateCommandToId(comando);

        final byte indice = conseguirIndicePorId(id);

        final Correo correo =
                ContactoAplicacionMapeador
                        .fromUpdateCommandToCorreo(comando);

        ensureCorreoNoRegistrado(correo, indice);

        final var nombre =
                ContactoAplicacionMapeador
                        .fromUpdateCommandToNombre(comando);

        final var telefono =
                ContactoAplicacionMapeador
                        .fromUpdateCommandToTelefono(comando);

        contacto.setNombre(indice, nombre);
        contacto.setTelefono(indice, telefono);
        contacto.setCorreo(indice, correo);

        return actualizarContactoPuerto.update(contacto);
    }

    private void validateCommand(
            final ActualizarContactoComando comando) {

        final Set<ConstraintViolation<ActualizarContactoComando>> violations =
                validator.validate(comando);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private byte conseguirIndicePorId(
            final Id id) {

        return conseguirContactoPorIdPuerto
                .getIndicePorId(id)
                .orElseThrow(
                        () -> ContactoNoEncontradoException
                                .becauseIdWasNotFound(id.value())
                );
    }

    private void ensureCorreoNoRegistrado(
            final Correo correo,
            final byte indice) {

        if (!conseguirContactoPorCorreoPuerto.existePorCorreo(correo)) {
            return;
        }

        if (!contacto.getCorreo(indice).equals(correo)) {
            throw CorreoYaRegistradoException
                    .becauseEmailWasAlreadyRegistered(correo.value());
        }
    }
}
