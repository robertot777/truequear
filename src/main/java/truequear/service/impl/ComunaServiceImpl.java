package truequear.service.impl;

import truequear.service.ComunaService;
import truequear.domain.Comuna;
import truequear.repository.ComunaRepository;
import truequear.repository.search.ComunaSearchRepository;
import truequear.service.dto.ComunaDTO;
import truequear.service.mapper.ComunaMapper;
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
 * Service Implementation for managing Comuna.
 */
@Service
@Transactional
public class ComunaServiceImpl implements ComunaService {

    private final Logger log = LoggerFactory.getLogger(ComunaServiceImpl.class);

    private final ComunaRepository comunaRepository;

    private final ComunaMapper comunaMapper;

    private final ComunaSearchRepository comunaSearchRepository;

    public ComunaServiceImpl(ComunaRepository comunaRepository, ComunaMapper comunaMapper, ComunaSearchRepository comunaSearchRepository) {
        this.comunaRepository = comunaRepository;
        this.comunaMapper = comunaMapper;
        this.comunaSearchRepository = comunaSearchRepository;
    }

    /**
     * Save a comuna.
     *
     * @param comunaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ComunaDTO save(ComunaDTO comunaDTO) {
        log.debug("Request to save Comuna : {}", comunaDTO);

        Comuna comuna = comunaMapper.toEntity(comunaDTO);
        comuna = comunaRepository.save(comuna);
        ComunaDTO result = comunaMapper.toDto(comuna);
        comunaSearchRepository.save(comuna);
        return result;
    }

    /**
     * Get all the comunas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComunaDTO> findAll() {
        log.debug("Request to get all Comunas");
        return comunaRepository.findAll().stream()
            .map(comunaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one comuna by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ComunaDTO> findOne(Long id) {
        log.debug("Request to get Comuna : {}", id);
        return comunaRepository.findById(id)
            .map(comunaMapper::toDto);
    }

    /**
     * Delete the comuna by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Comuna : {}", id);
        comunaRepository.deleteById(id);
        comunaSearchRepository.deleteById(id);
    }

    /**
     * Search for the comuna corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ComunaDTO> search(String query) {
        log.debug("Request to search Comunas for query {}", query);
        return StreamSupport
            .stream(comunaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(comunaMapper::toDto)
            .collect(Collectors.toList());
    }
}
