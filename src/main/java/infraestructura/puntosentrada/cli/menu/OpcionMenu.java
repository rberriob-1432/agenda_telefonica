package infraestructura.puntosentrada.cli.menu;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpcionMenu {

    LISTAR_CONTACTOS(
            1,
            "Listar todos los contactos"
    ),

    BUSCAR_CONTACTO(
            2,
            "Buscar contacto por ID"
    ),

    BUSCAR_CONTACTOS(
            3,
            "Búsqueda avanzada"
    ),

    CREAR_CONTACTO(
            4,
            "Crear contacto"
    ),

    ACTUALIZAR_CONTACTO(
            5,
            "Actualizar contacto"
    ),

    ELIMINAR_CONTACTO(
            6,
            "Eliminar contacto"
    ),

    EXPORTAR_CONTACTOS(
            7,
            "Exportar contactos"
    ),

    SALIR(
            0,
            "Salir"
    );

    private final int numero;
    private final String descripcion;

    public static Optional<OpcionMenu> desdeNumero(
            final int numero) {

        for (final OpcionMenu opcion : values()) {
            if (opcion.numero == numero) {
                return Optional.of(opcion);
            }
        }

        return Optional.empty();
    }
}