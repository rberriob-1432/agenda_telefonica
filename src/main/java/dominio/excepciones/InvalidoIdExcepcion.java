package dominio.excepciones;

public final class InvalidoIdExcepcion extends DomainException {

    private static final String MESSAGE_EMPTY = "Id no puede ser vacio";

    private InvalidoIdExcepcion(final String message) {
        super(message);
    }

    public static InvalidoIdExcepcion becauseValueIsEmpty() {
        return new InvalidoIdExcepcion(MESSAGE_EMPTY);
    }
}