package aplicacion.servicios;
import aplicacion.puertos.entrada.CrearContactoCasoUso;
import aplicacion.puertos.salida.ConseguirContactoPorCorreoPuerto;
import aplicacion.puertos.salida.GuardarContactoPuerto;
import aplicacion.servicios.dto.comando.AgregarContactoComando;
import aplicacion.servicios.dto.mapeador.ContactoAplicacionMapeador;
import dominio.modelo.Contacto;
import dominio.ov.Correo;
import dominio.ov.Id;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import java.util.Set;
@RequiredArgsConstructor
public final class CrearContactoServicio implements CrearContactoCasoUso {

    private final GuardarContactoPuerto guardarContactoPuerto;
    private final ConseguirContactoPorCorreoPuerto conseguirContactoPorCorreoPuerto;
    private final Validator validator;
    private final Contacto contacto;

    @Override
    public Contacto execute(
            @NotNull @Valid final AgregarContactoComando comando) {

        validateCommand(comando);

        final Correo correo =
                ContactoAplicacionMapeador
                        .fromCreateCommandToCorreo(comando);
        ensureCorreoNoRegistrado(correo);
        final var nombre =
                ContactoAplicacionMapeador
                        .fromCreateCommandToNombre(comando);
        final var telefono =
                ContactoAplicacionMapeador
                        .fromCreateCommandToTelefono(comando);
        final Id id = generarId();
        contacto.agregarContacto(
                nombre,
                telefono,
                correo,
                id
        );

        return guardarContactoPuerto.save(contacto);
    }

    private void validateCommand(
            final AgregarContactoComando comando) {

        final Set<ConstraintViolation<AgregarContactoComando>> violations =
                validator.validate(comando);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureCorreoNoRegistrado(
            final Correo correo) {

        conseguirContactoPorCorreoPuerto
                .getByEmail(correo)
                .ifPresent(
                        ignored -> {
                            throw new IllegalStateException(
                                    "El correo ya está registrado: "
                                            + correo.value());
                        });
    }

    private Id generarId() {
        int mayorId = 0;

        for (byte i = 0;
             i < contacto.getCantidadContactos();
             i++) {

            final String valorId =
                    contacto.getId(i).value();

            try {
                final int idActual =
                        Integer.parseInt(valorId);

                if (idActual > mayorId) {
                    mayorId = idActual;
                }

            } catch (NumberFormatException ignored) {
            }
        }
        return new Id(String.valueOf(mayorId + 1));
    }
}