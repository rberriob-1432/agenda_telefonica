package dominio.ov;

import dominio.excepciones.NombreInvalidoException;

import java.util.Objects;

public record Nombre(String value) {

    private static final int LONGITUD_MINIMA = 3;

    public Nombre {
        final String normalizedValue =
                Objects.requireNonNull(value, "Nombre no puede ser null").trim();

        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw NombreInvalidoException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < LONGITUD_MINIMA) {
            throw NombreInvalidoException.becauseLengthIsTooShort(LONGITUD_MINIMA);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}