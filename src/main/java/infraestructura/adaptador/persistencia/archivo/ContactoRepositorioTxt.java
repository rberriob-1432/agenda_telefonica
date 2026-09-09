package infraestructura.adaptador.persistencia.archivo;
import aplicacion.puertos.salida.ActualizarContactoPuerto;
import aplicacion.puertos.salida.ConseguirContactoPorCorreoPuerto;
import aplicacion.puertos.salida.ConseguirContactoPorIdPuerto;
import aplicacion.puertos.salida.ConseguirTodosContactosPuerto;
import aplicacion.puertos.salida.EliminarContactoPuerto;
import aplicacion.puertos.salida.GuardarContactoPuerto;
import dominio.modelo.Contacto;
import dominio.ov.Correo;
import dominio.ov.Id;
import infraestructura.adaptador.persistencia.dto.ContactoPersistenceDto;
import infraestructura.adaptador.persistencia.entidad.ContactoEntidad;
import infraestructura.adaptador.persistencia.excepcion.ExcepcionPersistencia;
import infraestructura.adaptador.persistencia.mapeador.ContactoPersistenciaMapeador;
import infraestructura.configuraciones.PropiedadesApp;
import lombok.RequiredArgsConstructor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public final class ContactoRepositorioTxt
        implements GuardarContactoPuerto,
        ActualizarContactoPuerto,
        ConseguirContactoPorIdPuerto,
        ConseguirContactoPorCorreoPuerto,
        ConseguirTodosContactosPuerto,
        EliminarContactoPuerto {

    private static final String SEPARADOR = "\\|";
    private static final String FORMATO_LINEA =
            "%s|%s|%s|%s";

    private final PropiedadesApp propiedadesApp;
    private final Contacto contacto;

    private Path obtenerRutaArchivo() {
        return Paths.get(
                propiedadesApp.obtener("agenda.archivo")
        );
    }

    @Override
    public Contacto save(final Contacto contacto) {

        try {
            escribirArchivo(contacto);
            return contacto;

        } catch (final IOException excepcion) {
            throw ExcepcionPersistencia
                    .porqueGuardarFallo(
                            obtenerUltimoId(contacto),
                            excepcion
                    );
        }
    }

    @Override
    public Contacto update(final Contacto contacto) {

        try {
            escribirArchivo(contacto);
            return contacto;

        } catch (final IOException excepcion) {
            throw ExcepcionPersistencia
                    .porqueActualizarFallo(
                            obtenerUltimoId(contacto),
                            excepcion
                    );
        }
    }

    @Override
    public Optional<Byte> getIndicePorId(
            final Id id) {

        try {
            return buscarIndicePorId(id);

        } catch (final IOException excepcion) {
            throw ExcepcionPersistencia
                    .porqueBuscarPorIdFallo(
                            id.value(),
                            excepcion
                    );
        }
    }

    @Override
    public boolean existePorCorreo(
            final Correo correo) {

        try {
            return buscarPorCorreo(correo);

        } catch (final IOException excepcion) {
            throw ExcepcionPersistencia
                    .porqueBuscarPorCorreoFallo(
                            correo.value(),
                            excepcion
                    );
        }
    }

    @Override
    public Contacto getAll() {

        try {
            cargarArchivo();
            return contacto;

        } catch (final IOException excepcion) {
            throw ExcepcionPersistencia
                    .porqueBuscarTodosFallo(excepcion);
        }
    }

    @Override
    public void delete(final Id id) {

        try {
            Optional<Byte> indice =
                    buscarIndicePorId(id);

            if (indice.isEmpty()) {
                return;
            }

            eliminarIndice(indice.get());
            escribirArchivo(contacto);

        } catch (final IOException excepcion) {
            throw ExcepcionPersistencia
                    .porqueEliminarFallo(
                            id.value(),
                            excepcion
                    );
        }
    }

    private void escribirArchivo(
            final Contacto contacto)
            throws IOException {

        final List<String> lineas =
                new java.util.ArrayList<>();

        for (byte i = 0;
             i < contacto.getCantidadContactos();
             i++) {

            final String linea =
                    String.format(
                            FORMATO_LINEA,
                            contacto.getId(i).value(),
                            contacto.getNombre(i).value(),
                            contacto.getTelefono(i).value(),
                            contacto.getCorreo(i).value()
                    );

            lineas.add(linea);
        }

        Files.write(
                obtenerRutaArchivo(),
                lineas
        );
    }

    private void cargarArchivo()
            throws IOException {

        final Path ruta =
                obtenerRutaArchivo();

        if (!Files.exists(ruta)) {
            return;
        }

        final List<String> lineas =
                Files.readAllLines(ruta);

        contacto.setCantidadContactos((byte) 0);

        for (final String linea : lineas) {

            if (linea.isBlank()) {
                continue;
            }

            String[] datos =
                    linea.split(SEPARADOR);

            ContactoPersistenceDto dto =
                    new ContactoPersistenceDto(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3]
                    );

            ContactoEntidad entidad =
                    ContactoPersistenciaMapeador
                            .fromDtoToEntity(dto);

            byte indice =
                    contacto.getCantidadContactos();

            contacto.setId(
                    indice,
                    new Id(entidad.id())
            );

            contacto.setNombre(
                    indice,
                    new dominio.ov.Nombre(entidad.nombre())
            );

            contacto.setTelefono(
                    indice,
                    new dominio.ov.Telefono(entidad.telefono())
            );

            contacto.setCorreo(
                    indice,
                    new Correo(entidad.correo())
            );

            contacto.setCantidadContactos(
                    (byte) (indice + 1)
            );
        }
    }

    private Optional<Byte> buscarIndicePorId(
            final Id id)
            throws IOException {

        cargarArchivo();

        for (byte i = 0;
             i < contacto.getCantidadContactos();
             i++) {

            if (contacto.getId(i).equals(id)) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    private boolean buscarPorCorreo(
            final Correo correo)
            throws IOException {

        cargarArchivo();

        for (byte i = 0;
             i < contacto.getCantidadContactos();
             i++) {

            if (contacto.getCorreo(i).equals(correo)) {
                return true;
            }
        }

        return false;
    }

    private void eliminarIndice(
            final byte indice) {

        byte cantidad =
                contacto.getCantidadContactos();

        for (byte i = indice;
             i < cantidad - 1;
             i++) {

            contacto.setId(
                    i,
                    contacto.getId((byte) (i + 1))
            );

            contacto.setNombre(
                    i,
                    contacto.getNombre((byte) (i + 1))
            );

            contacto.setTelefono(
                    i,
                    contacto.getTelefono((byte) (i + 1))
            );

            contacto.setCorreo(
                    i,
                    contacto.getCorreo((byte) (i + 1))
            );
        }

        contacto.setId(
                (byte) (cantidad - 1),
                null
        );

        contacto.setNombre(
                (byte) (cantidad - 1),
                null
        );

        contacto.setTelefono(
                (byte) (cantidad - 1),
                null
        );

        contacto.setCorreo(
                (byte) (cantidad - 1),
                null
        );

        contacto.setCantidadContactos(
                (byte) (cantidad - 1)
        );
    }

    private String obtenerUltimoId(
            final Contacto contacto) {

        if (contacto.getCantidadContactos() == 0) {
            return "desconocido";
        }

        return contacto
                .getId(
                        (byte) (
                                contacto.getCantidadContactos() - 1
                        )
                )
                .value();
    }
}