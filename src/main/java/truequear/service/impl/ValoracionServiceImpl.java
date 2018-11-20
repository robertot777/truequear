package truequear.service.impl;

import truequear.service.ValoracionService;
import truequear.domain.Valoracion;
import truequear.repository.ValoracionRepository;
import truequear.repository.search.ValoracionSearchRepository;
import truequear.service.dto.ValoracionDTO;
import truequear.service.mapper.ValoracionMapper;
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
 * Service Implementation for managing Valoracion.
 */
@Service
@Transactional
public class ValoracionServiceImpl implements ValoracionService {

    private final Logger log = LoggerFactory.getLogger(ValoracionServiceImpl.class);

    private final ValoracionRepository valoracionRepository;

    private final ValoracionMapper valoracionMapper;

    private final ValoracionSearchRepository valoracionSearchRepository;

    public ValoracionServiceImpl(ValoracionRepository valoracionRepository, ValoracionMapper valoracionMapper, ValoracionSearchRepository valoracionSearchRepository) {
        this.valoracionRepository = valoracionRepository;
        this.valoracionMapper = valoracionMapper;
        this.valoracionSearchRepository = valoracionSearchRepository;
    }

    /**
     * Save a valoracion.
     *
     * @param valoracionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ValoracionDTO save(ValoracionDTO valoracionDTO) {
        log.debug("Request to save Valoracion : {}", valoracionDTO);

        Valoracion valoracion = valoracionMapper.toEntity(valoracionDTO);
        valoracion = valoracionRepository.save(valoracion);
        ValoracionDTO result = valoracionMapper.toDto(valoracion);
        valoracionSearchRepository.save(valoracion);
        return result;
    }

    /**
     * Get all the valoracions.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ValoracionDTO> findAll() {
        log.debug("Request to get all Valoracions");
        return valoracionRepository.findAll().stream()
            .map(valoracionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one valoracion by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ValoracionDTO> findOne(Long id) {
        log.debug("Request to get Valoracion : {}", id);
        return valoracionRepository.findById(id)
            .map(valoracionMapper::toDto);
    }

    /**
     * Delete the valoracion by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Valoracion : {}", id);
        valoracionRepository.deleteById(id);
        valoracionSearchRepository.deleteById(id);
    }

    /**
     * Search for the valoracion corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ValoracionDTO> search(String query) {
        log.debug("Request to search Valoracions for query {}", query);
        return StreamSupport
            .stream(valoracionSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(valoracionMapper::toDto)
            .collect(Collectors.toList());
    }
}
