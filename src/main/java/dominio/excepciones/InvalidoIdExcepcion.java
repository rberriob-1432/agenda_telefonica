package dominio.excepciones;

public final class InvalidoIdExcepcion extends DomainException {

    private static final String MESSAGE_EMPTY = "The user id must not be empty.";

    private InvalidoIdExcepcion(final String message) {
        super(message);
    }

    public static InvalidoIdExcepcion becauseValueIsEmpty() {
        return new InvalidoIdExcepcion(MESSAGE_EMPTY);
    }
}