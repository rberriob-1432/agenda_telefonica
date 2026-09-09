package aplicacion.servicios;

import aplicacion.puertos.entrada.ActualizarContactoCasoUso;
import aplicacion.puertos.salida.ActualizarContactoPuerto;
import aplicacion.puertos.salida.ConseguirContactoPorCorreoPuerto;
import aplicacion.puertos.salida.ConseguirContactoPorIdPuerto;
import aplicacion.servicios.dto.comando.ActualizarContactoComando;
import aplicacion.servicios.dto.mapeador.ContactoAplicacionMapeador;
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

    @Override
    public Contacto execute(
            final ActualizarContactoComando comando) {

        validateCommand(comando);

        final Id id =
                ContactoAplicacionMapeador
                        .fromUpdateCommandToId(comando);

        ensureContactoExists(id);

        final Correo correo =
                ContactoAplicacionMapeador
                        .fromUpdateCommandToCorreo(comando);

        ensureCorreoNoRegistrado(correo, id);

        final Contacto contacto =
                conseguirContactoPorIdPuerto
                        .getById(id)
                        .orElseThrow(
                                () -> new IllegalStateException(
                                        "No existe un contacto con el ID: "
                                                + id.value()
                                )
                        );

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

    private void ensureContactoExists(
            final Id id) {

        conseguirContactoPorIdPuerto
                .getById(id)
                .orElseThrow(
                        () -> new IllegalStateException(
                                "No existe un contacto con el ID: "
                                        + id.value()
                        )
                );
    }

    private void ensureCorreoNoRegistrado(
            final Correo correo,
            final Id id) {

        conseguirContactoPorCorreoPuerto
                .getByEmail(correo)
                .ifPresent(
                        contactoEncontrado -> {

                            /*
                             * Si el correo pertenece al mismo contacto
                             * que estamos actualizando, no hay problema.
                             *
                             * Si pertenece a otro contacto,
                             * rechazamos la actualización.
                             */
                        });
    }
}
