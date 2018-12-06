package truequear.web.rest;

import com.codahale.metrics.annotation.Timed;
import truequear.service.ObjetosService;
import truequear.web.rest.errors.BadRequestAlertException;
import truequear.web.rest.util.HeaderUtil;
import truequear.service.dto.ObjetosDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Objetos.
 */
@RestController
@RequestMapping("/api")
public class ObjetosResource {

    private final Logger log = LoggerFactory.getLogger(ObjetosResource.class);

    private static final String ENTITY_NAME = "objetos";

    private final ObjetosService objetosService;

    public ObjetosResource(ObjetosService objetosService) {
        this.objetosService = objetosService;
    }

    /**
     * POST  /objetos : Create a new objetos.
     *
     * @param objetosDTO the objetosDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new objetosDTO, or with status 400 (Bad Request) if the objetos has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/objetos")
    @Timed
    public ResponseEntity<ObjetosDTO> createObjetos(@Valid @RequestBody ObjetosDTO objetosDTO) throws URISyntaxException {
        log.debug("REST request to save Objetos : {}", objetosDTO);
        if (objetosDTO.getId() != null) {
            throw new BadRequestAlertException("A new objetos cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjetosDTO result = objetosService.save(objetosDTO);
        return ResponseEntity.created(new URI("/api/objetos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /objetos : Updates an existing objetos.
     *
     * @param objetosDTO the objetosDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated objetosDTO,
     * or with status 400 (Bad Request) if the objetosDTO is not valid,
     * or with status 500 (Internal Server Error) if the objetosDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/objetos")
    @Timed
    public ResponseEntity<ObjetosDTO> updateObjetos(@Valid @RequestBody ObjetosDTO objetosDTO) throws URISyntaxException {
        log.debug("REST request to update Objetos : {}", objetosDTO);
        if (objetosDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObjetosDTO result = objetosService.save(objetosDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, objetosDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /objetos : get all the objetos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of objetos in body
     */
    @GetMapping("/objetos")
    @Timed
    public List<ObjetosDTO> getAllObjetos() {
        log.debug("REST request to get all Objetos");
        return objetosService.findAll();
    }

    /**
     * GET  /objetos/:id : get the "id" objetos.
     *
     * @param id the id of the objetosDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the objetosDTO, or with status 404 (Not Found)
     */
    @GetMapping("/objetos/{id}")
    @Timed
    public ResponseEntity<ObjetosDTO> getObjetos(@PathVariable Long id) {
        log.debug("REST request to get Objetos : {}", id);
        Optional<ObjetosDTO> objetosDTO = objetosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objetosDTO);
    }

    /**
     * DELETE  /objetos/:id : delete the "id" objetos.
     *
     * @param id the id of the objetosDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/objetos/{id}")
    @Timed
    public ResponseEntity<Void> deleteObjetos(@PathVariable Long id) {
        log.debug("REST request to delete Objetos : {}", id);
        objetosService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/objetos?query=:query : search for the objetos corresponding
     * to the query.
     *
     * @param query the query of the objetos search
     * @return the result of the search
     */
    @GetMapping("/_search/objetos")
    @Timed
    public List<ObjetosDTO> searchObjetos(@RequestParam String query) {
        log.debug("REST request to search Objetos for query {}", query);
        return objetosService.search(query);
    }

}
