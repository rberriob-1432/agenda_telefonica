package dominio.ov;

import dominio.excepciones.InvalidoIdExcepcion;

import java.util.Objects;

public record Id(String value) {

    public Id {
        final String normalizedValue = Objects.requireNonNull(value, "Id no puede ser nulo").trim();
        validateNotEmpty(normalizedValue);

        value = normalizedValue;
    }

    private static void validateNotEmpty(final String normalizedValue) {
        if (normalizedValue.isEmpty()) {
            throw InvalidoIdExcepcion.becauseValueIsEmpty();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}