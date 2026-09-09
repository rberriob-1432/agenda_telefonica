package dominio.excepciones;

public final class TelefonoInvalidoException extends DomainException {

    private static final String NUMERO_VACIO =
            "El teléfono no puede estar vacío.";

    private static final String NUMERO_CORTO    =
            "El teléfono debe tener al menos %d caracteres.";

    private TelefonoInvalidoException(final String message) {
        super(message);
    }

    public static TelefonoInvalidoException becauseValueIsEmpty() {
        return new TelefonoInvalidoException(NUMERO_VACIO);
    }

    public static TelefonoInvalidoException becauseLengthIsTooShort(
            final int minimumLength) {

        return new TelefonoInvalidoException(
                String.format(NUMERO_CORTO , minimumLength)
        );
    }
}