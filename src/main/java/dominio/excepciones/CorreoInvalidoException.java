package dominio.excepciones;
public final class CorreoInvalidoException extends DomainException {

    private static final String CORREO_VACIO =
            "El correo no puede estar vacío.";

    private static final String CORREO_MAL_FORMATO =
            "El formato del correo no es válido: %s.";

    private CorreoInvalidoException(final String message) {
        super(message);
    }

    public static CorreoInvalidoException becauseValueIsEmpty() {
        return new CorreoInvalidoException(CORREO_VACIO);
    }

    public static CorreoInvalidoException becauseFormatIsInvalid(
            final String value) {

        return new CorreoInvalidoException(
                String.format(CORREO_MAL_FORMATO, value)
        );
    }
}