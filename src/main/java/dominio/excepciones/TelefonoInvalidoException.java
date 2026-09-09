package dominio.excepciones;

public final class TelefonoInvalidoException extends DomainException {

    private static final String TELEFONO_VACIO =
            "El teléfono no puede estar vacío.";

    private static final String TELEFONO_MAL_FORMATO =
            "El formato del teléfono no es válido: %s.";

    private TelefonoInvalidoException(final String message) {
        super(message);
    }

    public static TelefonoInvalidoException becauseValueIsEmpty() {
        return new TelefonoInvalidoException(TELEFONO_VACIO);
    }

    public static TelefonoInvalidoException becauseFormatIsInvalid(
            final String value) {

        return new TelefonoInvalidoException(
                String.format(TELEFONO_MAL_FORMATO, value)
        );
    }
}