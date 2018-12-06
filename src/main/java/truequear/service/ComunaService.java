package truequear.service;

import truequear.service.dto.ComunaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Comuna.
 */
public interface ComunaService {

    /**
     * Save a comuna.
     *
     * @param comunaDTO the entity to save
     * @return the persisted entity
     */
    ComunaDTO save(ComunaDTO comunaDTO);

    /**
     * Get all the comunas.
     *
     * @return the list of entities
     */
    List<ComunaDTO> findAll();


    /**
     * Get the "id" comuna.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ComunaDTO> findOne(Long id);

    /**
     * Delete the "id" comuna.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the comuna corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ComunaDTO> search(String query);
}
