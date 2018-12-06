package truequear.service.mapper;

import truequear.domain.*;
import truequear.service.dto.RespuestaOfertaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RespuestaOferta and its DTO RespuestaOfertaDTO.
 */
@Mapper(componentModel = "spring", uses = {OfertaMapper.class})
public interface RespuestaOfertaMapper extends EntityMapper<RespuestaOfertaDTO, RespuestaOferta> {

    @Mapping(source = "generaRespuestas.id", target = "generaRespuestasId")
    RespuestaOfertaDTO toDto(RespuestaOferta respuestaOferta);

    @Mapping(source = "generaRespuestasId", target = "generaRespuestas")
    RespuestaOferta toEntity(RespuestaOfertaDTO respuestaOfertaDTO);

    default RespuestaOferta fromId(Long id) {
        if (id == null) {
            return null;
        }
        RespuestaOferta respuestaOferta = new RespuestaOferta();
        respuestaOferta.setId(id);
        return respuestaOferta;
    }
}
