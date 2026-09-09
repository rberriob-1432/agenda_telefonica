package infraestructura.adaptador.persistencia.mapeador;

import dominio.modelo.Contacto;
import infraestructura.adaptador.persistencia.dto.ContactoPersistenceDto;
import infraestructura.adaptador.persistencia.entidad.ContactoEntidad;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ContactoPersistenciaMapeador {

    public ContactoPersistenceDto fromModelToDto(
            final Contacto contacto,
            final byte indice) {

        return new ContactoPersistenceDto(
                contacto.getId(indice).value(),
                contacto.getNombre(indice).value(),
                contacto.getTelefono(indice).value(),
                contacto.getCorreo(indice).value()
        );
    }

    public ContactoEntidad fromDtoToEntity(
            final ContactoPersistenceDto dto) {

        return new ContactoEntidad(
                dto.id(),
                dto.nombre(),
                dto.telefono(),
                dto.correo()
        );
    }

    public ContactoPersistenceDto fromEntityToDto(
            final ContactoEntidad entidad) {

        return new ContactoPersistenceDto(
                entidad.id(),
                entidad.nombre(),
                entidad.telefono(),
                entidad.correo()
        );
    }
}