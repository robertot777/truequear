package truequear.service.impl;

import truequear.service.RespuestaOfertaService;
import truequear.domain.RespuestaOferta;
import truequear.repository.RespuestaOfertaRepository;
import truequear.repository.search.RespuestaOfertaSearchRepository;
import truequear.service.dto.RespuestaOfertaDTO;
import truequear.service.mapper.RespuestaOfertaMapper;
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
 * Service Implementation for managing RespuestaOferta.
 */
@Service
@Transactional
public class RespuestaOfertaServiceImpl implements RespuestaOfertaService {

    private final Logger log = LoggerFactory.getLogger(RespuestaOfertaServiceImpl.class);

    private final RespuestaOfertaRepository respuestaOfertaRepository;

    private final RespuestaOfertaMapper respuestaOfertaMapper;

    private final RespuestaOfertaSearchRepository respuestaOfertaSearchRepository;

    public RespuestaOfertaServiceImpl(RespuestaOfertaRepository respuestaOfertaRepository, RespuestaOfertaMapper respuestaOfertaMapper, RespuestaOfertaSearchRepository respuestaOfertaSearchRepository) {
        this.respuestaOfertaRepository = respuestaOfertaRepository;
        this.respuestaOfertaMapper = respuestaOfertaMapper;
        this.respuestaOfertaSearchRepository = respuestaOfertaSearchRepository;
    }

    /**
     * Save a respuestaOferta.
     *
     * @param respuestaOfertaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RespuestaOfertaDTO save(RespuestaOfertaDTO respuestaOfertaDTO) {
        log.debug("Request to save RespuestaOferta : {}", respuestaOfertaDTO);

        RespuestaOferta respuestaOferta = respuestaOfertaMapper.toEntity(respuestaOfertaDTO);
        respuestaOferta = respuestaOfertaRepository.save(respuestaOferta);
        RespuestaOfertaDTO result = respuestaOfertaMapper.toDto(respuestaOferta);
        respuestaOfertaSearchRepository.save(respuestaOferta);
        return result;
    }

    /**
     * Get all the respuestaOfertas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RespuestaOfertaDTO> findAll() {
        log.debug("Request to get all RespuestaOfertas");
        return respuestaOfertaRepository.findAll().stream()
            .map(respuestaOfertaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one respuestaOferta by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RespuestaOfertaDTO> findOne(Long id) {
        log.debug("Request to get RespuestaOferta : {}", id);
        return respuestaOfertaRepository.findById(id)
            .map(respuestaOfertaMapper::toDto);
    }

    /**
     * Delete the respuestaOferta by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RespuestaOferta : {}", id);
        respuestaOfertaRepository.deleteById(id);
        respuestaOfertaSearchRepository.deleteById(id);
    }

    /**
     * Search for the respuestaOferta corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RespuestaOfertaDTO> search(String query) {
        log.debug("Request to search RespuestaOfertas for query {}", query);
        return StreamSupport
            .stream(respuestaOfertaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(respuestaOfertaMapper::toDto)
            .collect(Collectors.toList());
    }
}
