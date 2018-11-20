package truequear.service;

import truequear.service.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Cliente.
 */
public interface ClienteService {

    /**
     * Save a cliente.
     *
     * @param clienteDTO the entity to save
     * @return the persisted entity
     */
    ClienteDTO save(ClienteDTO clienteDTO);

    /**
     * Get all the clientes.
     *
     * @return the list of entities
     */
    List<ClienteDTO> findAll();


    /**
     * Get the "id" cliente.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ClienteDTO> findOne(Long id);

    /**
     * Delete the "id" cliente.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the cliente corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<ClienteDTO> search(String query);
}
