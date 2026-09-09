package dominio.excepciones;

public final class CorreoYaRegistradoException extends DomainException {

    private CorreoYaRegistradoException(final String message) {
        super(message);
    }

    public static CorreoYaRegistradoException becauseEmailWasAlreadyRegistered(
            final String correo) {

        return new CorreoYaRegistradoException(
                "El correo ya está registrado: " + correo
        );
    }
}