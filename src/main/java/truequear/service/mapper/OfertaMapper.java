package truequear.service.mapper;

import truequear.domain.*;
import truequear.service.dto.OfertaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Oferta and its DTO OfertaDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta> {

    @Mapping(source = "nombre.id", target = "nombreId")
    OfertaDTO toDto(Oferta oferta);

    @Mapping(source = "nombreId", target = "nombre")
    Oferta toEntity(OfertaDTO ofertaDTO);

    default Oferta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Oferta oferta = new Oferta();
        oferta.setId(id);
        return oferta;
    }
}
