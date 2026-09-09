package dominio.excepciones;

public final class NombreInvalidoException extends DomainException {

    private static final String NOMBRE_VACIO =
            "El nombre no puede estar vacío.";

    private static final String NOMBRE_CORTO =
            "El nombre debe tener al menos %d caracteres.";

    private NombreInvalidoException(final String message) {
        super(message);
    }

    public static NombreInvalidoException becauseValueIsEmpty() {
        return new NombreInvalidoException(NOMBRE_VACIO);
    }

    public static NombreInvalidoException becauseLengthIsTooShort(
            final int minimumLength) {

        return new NombreInvalidoException(
                String.format(NOMBRE_CORTO, minimumLength)
        );
    }
}