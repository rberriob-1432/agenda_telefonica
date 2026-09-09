package aplicacion.servicios;

import aplicacion.puertos.entrada.BuscarContactosCasoUso;
import aplicacion.puertos.salida.ConseguirTodosContactosPuerto;
import aplicacion.servicios.dto.ContactoRespuestaDto;
import aplicacion.servicios.dto.comando.BuscarContactosComando;
import aplicacion.servicios.dto.comando.TipoBusqueda;
import dominio.modelo.Contacto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public final class BuscarContactosServicio
        implements BuscarContactosCasoUso {

    private final ConseguirTodosContactosPuerto
            conseguirTodosContactosPuerto;

    private final Validator validator;

    @Override
    public List<ContactoRespuestaDto> execute(
            final BuscarContactosComando comando) {

        validateCommand(comando);

        final Contacto contacto =
                conseguirTodosContactosPuerto.getAll();

        final List<ContactoRespuestaDto> resultados =
                new ArrayList<>();

        final String criterio =
                comando.criterio()
                        .trim()
                        .toLowerCase();

        for (byte i = 0;
             i < contacto.getCantidadContactos();
             i++) {

            boolean coincide = switch (comando.tipoBusqueda()) {

                case NOMBRE ->
                        contacto.getNombre(i)
                                .value()
                                .toLowerCase()
                                .startsWith(criterio);

                case CORREO ->
                        contacto.getCorreo(i)
                                .value()
                                .toLowerCase()
                                .contains(criterio);
            };

            if (coincide) {

                resultados.add(
                        new ContactoRespuestaDto(
                                contacto.getId(i).value(),
                                contacto.getNombre(i).value(),
                                contacto.getTelefono(i).value(),
                                contacto.getCorreo(i).value()
                        )
                );
            }
        }

        return resultados;
    }

    private void validateCommand(
            final BuscarContactosComando comando) {

        final Set<ConstraintViolation<BuscarContactosComando>>
                violations =
                validator.validate(comando);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}