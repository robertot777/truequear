package truequear.service.impl;

import truequear.service.OfertaService;
import truequear.domain.Oferta;
import truequear.repository.OfertaRepository;
import truequear.repository.search.OfertaSearchRepository;
import truequear.service.dto.OfertaDTO;
import truequear.service.mapper.OfertaMapper;
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
 * Service Implementation for managing Oferta.
 */
@Service
@Transactional
public class OfertaServiceImpl implements OfertaService {

    private final Logger log = LoggerFactory.getLogger(OfertaServiceImpl.class);

    private final OfertaRepository ofertaRepository;

    private final OfertaMapper ofertaMapper;

    private final OfertaSearchRepository ofertaSearchRepository;

    public OfertaServiceImpl(OfertaRepository ofertaRepository, OfertaMapper ofertaMapper, OfertaSearchRepository ofertaSearchRepository) {
        this.ofertaRepository = ofertaRepository;
        this.ofertaMapper = ofertaMapper;
        this.ofertaSearchRepository = ofertaSearchRepository;
    }

    /**
     * Save a oferta.
     *
     * @param ofertaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OfertaDTO save(OfertaDTO ofertaDTO) {
        log.debug("Request to save Oferta : {}", ofertaDTO);

        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        oferta = ofertaRepository.save(oferta);
        OfertaDTO result = ofertaMapper.toDto(oferta);
        ofertaSearchRepository.save(oferta);
        return result;
    }

    /**
     * Get all the ofertas.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfertaDTO> findAll() {
        log.debug("Request to get all Ofertas");
        return ofertaRepository.findAll().stream()
            .map(ofertaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one oferta by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OfertaDTO> findOne(Long id) {
        log.debug("Request to get Oferta : {}", id);
        return ofertaRepository.findById(id)
            .map(ofertaMapper::toDto);
    }

    /**
     * Delete the oferta by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Oferta : {}", id);
        ofertaRepository.deleteById(id);
        ofertaSearchRepository.deleteById(id);
    }

    /**
     * Search for the oferta corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfertaDTO> search(String query) {
        log.debug("Request to search Ofertas for query {}", query);
        return StreamSupport
            .stream(ofertaSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(ofertaMapper::toDto)
            .collect(Collectors.toList());
    }
}
