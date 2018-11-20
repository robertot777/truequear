package truequear.service;

import truequear.service.dto.OfertaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Oferta.
 */
public interface OfertaService {

    /**
     * Save a oferta.
     *
     * @param ofertaDTO the entity to save
     * @return the persisted entity
     */
    OfertaDTO save(OfertaDTO ofertaDTO);

    /**
     * Get all the ofertas.
     *
     * @return the list of entities
     */
    List<OfertaDTO> findAll();


    /**
     * Get the "id" oferta.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OfertaDTO> findOne(Long id);

    /**
     * Delete the "id" oferta.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the oferta corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<OfertaDTO> search(String query);
}
