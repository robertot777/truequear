package truequear.service.mapper;

import truequear.domain.*;
import truequear.service.dto.ObjetosDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Objetos and its DTO ObjetosDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ObjetosMapper extends EntityMapper<ObjetosDTO, Objetos> {

    @Mapping(source = "nombre.id", target = "nombreId")
    ObjetosDTO toDto(Objetos objetos);

    @Mapping(source = "nombreId", target = "nombre")
    Objetos toEntity(ObjetosDTO objetosDTO);

    default Objetos fromId(Long id) {
        if (id == null) {
            return null;
        }
        Objetos objetos = new Objetos();
        objetos.setId(id);
        return objetos;
    }
}
