package dominio.ov;

import dominio.excepciones.TelefonoInvalidoException;

import java.util.Objects;

public record Telefono(String value) {

    private static final int LONGITUD_MINIMA = 7;

    public Telefono {
        final String normalizedValue =
                Objects.requireNonNull(value, "Telefono no puede ser null").trim();

        validateNotEmpty(normalizedValue);
        validateMinimumLength(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw TelefonoInvalidoException.becauseValueIsEmpty();
        }
    }

    private static void validateMinimumLength(final String normalizedValue) {
        if (normalizedValue.length() < LONGITUD_MINIMA) {
            throw TelefonoInvalidoException.becauseLengthIsTooShort(LONGITUD_MINIMA);
        }
    }

    @Override
    public String toString() {
        return value;
    }
}