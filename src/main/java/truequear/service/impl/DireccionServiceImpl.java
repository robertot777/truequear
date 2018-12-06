package truequear.service.impl;

import truequear.service.DireccionService;
import truequear.domain.Direccion;
import truequear.repository.DireccionRepository;
import truequear.repository.search.DireccionSearchRepository;
import truequear.service.dto.DireccionDTO;
import truequear.service.mapper.DireccionMapper;
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
 * Service Implementation for managing Direccion.
 */
@Service
@Transactional
public class DireccionServiceImpl implements DireccionService {

    private final Logger log = LoggerFactory.getLogger(DireccionServiceImpl.class);

    private final DireccionRepository direccionRepository;

    private final DireccionMapper direccionMapper;

    private final DireccionSearchRepository direccionSearchRepository;

    public DireccionServiceImpl(DireccionRepository direccionRepository, DireccionMapper direccionMapper, DireccionSearchRepository direccionSearchRepository) {
        this.direccionRepository = direccionRepository;
        this.direccionMapper = direccionMapper;
        this.direccionSearchRepository = direccionSearchRepository;
    }

    /**
     * Save a direccion.
     *
     * @param direccionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DireccionDTO save(DireccionDTO direccionDTO) {
        log.debug("Request to save Direccion : {}", direccionDTO);

        Direccion direccion = direccionMapper.toEntity(direccionDTO);
        direccion = direccionRepository.save(direccion);
        DireccionDTO result = direccionMapper.toDto(direccion);
        direccionSearchRepository.save(direccion);
        return result;
    }

    /**
     * Get all the direccions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DireccionDTO> findAll() {
        log.debug("Request to get all Direccions");
        return direccionRepository.findAll().stream()
            .map(direccionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one direccion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DireccionDTO> findOne(Long id) {
        log.debug("Request to get Direccion : {}", id);
        return direccionRepository.findById(id)
            .map(direccionMapper::toDto);
    }

    /**
     * Delete the direccion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Direccion : {}", id);
        direccionRepository.deleteById(id);
        direccionSearchRepository.deleteById(id);
    }

    /**
     * Search for the direccion corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DireccionDTO> search(String query) {
        log.debug("Request to search Direccions for query {}", query);
        return StreamSupport
            .stream(direccionSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(direccionMapper::toDto)
            .collect(Collectors.toList());
    }
}
