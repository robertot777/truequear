package truequear.service;

import truequear.service.dto.RespuestaOfertaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing RespuestaOferta.
 */
public interface RespuestaOfertaService {

    /**
     * Save a respuestaOferta.
     *
     * @param respuestaOfertaDTO the entity to save
     * @return the persisted entity
     */
    RespuestaOfertaDTO save(RespuestaOfertaDTO respuestaOfertaDTO);

    /**
     * Get all the respuestaOfertas.
     *
     * @return the list of entities
     */
    List<RespuestaOfertaDTO> findAll();


    /**
     * Get the "id" respuestaOferta.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RespuestaOfertaDTO> findOne(Long id);

    /**
     * Delete the "id" respuestaOferta.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the respuestaOferta corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<RespuestaOfertaDTO> search(String query);
}
