package truequear.service;

import truequear.service.dto.ValoracionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Valoracion.
 */
public interface ValoracionService {

    /**
     * Save a valoracion.
     *
     * @param valoracionDTO the entity to save
     * @return the persisted entity
     */
    ValoracionDTO save(ValoracionDTO valoracionDTO);

    /**
     * Get all the valoracions.
     *
     * @return the list of entities
     */
    List<ValoracionDTO> findAll();


    /**
     * Get the "id" valoracion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ValoracionDTO> findOne(Long id);

    /**
     * Delete the "id" valoracion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the valoracion corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ValoracionDTO> search(String query);
}
