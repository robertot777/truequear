package truequear.service;

import truequear.service.dto.ObjetosDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Objetos.
 */
public interface ObjetosService {

    /**
     * Save a objetos.
     *
     * @param objetosDTO the entity to save
     * @return the persisted entity
     */
    ObjetosDTO save(ObjetosDTO objetosDTO);

    /**
     * Get all the objetos.
     *
     * @return the list of entities
     */
    List<ObjetosDTO> findAll();


    /**
     * Get the "id" objetos.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ObjetosDTO> findOne(Long id);

    /**
     * Delete the "id" objetos.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the objetos corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ObjetosDTO> search(String query);
}
