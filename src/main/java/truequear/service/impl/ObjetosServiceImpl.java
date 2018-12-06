package truequear.service.impl;

import truequear.service.ObjetosService;
import truequear.domain.Objetos;
import truequear.repository.ObjetosRepository;
import truequear.repository.search.ObjetosSearchRepository;
import truequear.service.dto.ObjetosDTO;
import truequear.service.mapper.ObjetosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Objetos.
 */
@Service
@Transactional
public class ObjetosServiceImpl implements ObjetosService {

    private final Logger log = LoggerFactory.getLogger(ObjetosServiceImpl.class);

    private final ObjetosRepository objetosRepository;

    private final ObjetosMapper objetosMapper;

    private final ObjetosSearchRepository objetosSearchRepository;

    public ObjetosServiceImpl(ObjetosRepository objetosRepository, ObjetosMapper objetosMapper, ObjetosSearchRepository objetosSearchRepository) {
        this.objetosRepository = objetosRepository;
        this.objetosMapper = objetosMapper;
        this.objetosSearchRepository = objetosSearchRepository;
    }

    /**
     * Save a objetos.
     *
     * @param objetosDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ObjetosDTO save(ObjetosDTO objetosDTO) {
        log.debug("Request to save Objetos : {}", objetosDTO);

        Objetos objetos = objetosMapper.toEntity(objetosDTO);
        objetos = objetosRepository.save(objetos);
        ObjetosDTO result = objetosMapper.toDto(objetos);
        objetosSearchRepository.save(objetos);
        return result;
    }

    /**
     * Get all the objetos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ObjetosDTO> findAll() {
        log.debug("Request to get all Objetos");
        return objetosRepository.findAll().stream()
            .map(objetosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one objetos by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ObjetosDTO> findOne(Long id) {
        log.debug("Request to get Objetos : {}", id);
        return objetosRepository.findById(id)
            .map(objetosMapper::toDto);
    }

    /**
     * Delete the objetos by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Objetos : {}", id);
        objetosRepository.deleteById(id);
        objetosSearchRepository.deleteById(id);
    }

    /**
     * Search for the objetos corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ObjetosDTO> search(String query) {
        log.debug("Request to search Objetos for query {}", query);
        return StreamSupport
            .stream(objetosSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(objetosMapper::toDto)
            .collect(Collectors.toList());
    }
}
