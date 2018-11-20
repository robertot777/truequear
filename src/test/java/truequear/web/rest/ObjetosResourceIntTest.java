package truequear.web.rest;

import truequear.TruequearApp;

import truequear.domain.Objetos;
import truequear.repository.ObjetosRepository;
import truequear.repository.search.ObjetosSearchRepository;
import truequear.service.ObjetosService;
import truequear.service.dto.ObjetosDTO;
import truequear.service.mapper.ObjetosMapper;
import truequear.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;


import static truequear.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import truequear.domain.enumeration.TipoObjetos;
/**
 * Test class for the ObjetosResource REST controller.
 *
 * @see ObjetosResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TruequearApp.class)
public class ObjetosResourceIntTest {

    private static final String DEFAULT_TIPO_OBJETO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_OBJETO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_AGREGAR_ARCHIBO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_AGREGAR_ARCHIBO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_AGREGAR_ARCHIBO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_AGREGAR_ARCHIBO_CONTENT_TYPE = "image/png";

    private static final TipoObjetos DEFAULT_TIPO_OBJETOS = TipoObjetos.ALIMENTACIONBEBIDA;
    private static final TipoObjetos UPDATED_TIPO_OBJETOS = TipoObjetos.ARTEANTIGUEDADE;

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    @Autowired
    private ObjetosRepository objetosRepository;

    @Autowired
    private ObjetosMapper objetosMapper;

    @Autowired
    private ObjetosService objetosService;

    /**
     * This repository is mocked in the truequear.repository.search test package.
     *
     * @see truequear.repository.search.ObjetosSearchRepositoryMockConfiguration
     */
    @Autowired
    private ObjetosSearchRepository mockObjetosSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restObjetosMockMvc;

    private Objetos objetos;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ObjetosResource objetosResource = new ObjetosResource(objetosService);
        this.restObjetosMockMvc = MockMvcBuilders.standaloneSetup(objetosResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Objetos createEntity(EntityManager em) {
        Objetos objetos = new Objetos()
            .tipoObjeto(DEFAULT_TIPO_OBJETO)
            .agregarArchibo(DEFAULT_AGREGAR_ARCHIBO)
            .agregarArchiboContentType(DEFAULT_AGREGAR_ARCHIBO_CONTENT_TYPE)
            .tipoObjetos(DEFAULT_TIPO_OBJETOS)
            .descripcion(DEFAULT_DESCRIPCION);
        return objetos;
    }

    @Before
    public void initTest() {
        objetos = createEntity(em);
    }

    @Test
    @Transactional
    public void createObjetos() throws Exception {
        int databaseSizeBeforeCreate = objetosRepository.findAll().size();

        // Create the Objetos
        ObjetosDTO objetosDTO = objetosMapper.toDto(objetos);
        restObjetosMockMvc.perform(post("/api/objetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objetosDTO)))
            .andExpect(status().isCreated());

        // Validate the Objetos in the database
        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeCreate + 1);
        Objetos testObjetos = objetosList.get(objetosList.size() - 1);
        assertThat(testObjetos.getTipoObjeto()).isEqualTo(DEFAULT_TIPO_OBJETO);
        assertThat(testObjetos.getAgregarArchibo()).isEqualTo(DEFAULT_AGREGAR_ARCHIBO);
        assertThat(testObjetos.getAgregarArchiboContentType()).isEqualTo(DEFAULT_AGREGAR_ARCHIBO_CONTENT_TYPE);
        assertThat(testObjetos.getTipoObjetos()).isEqualTo(DEFAULT_TIPO_OBJETOS);
        assertThat(testObjetos.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);

        // Validate the Objetos in Elasticsearch
        verify(mockObjetosSearchRepository, times(1)).save(testObjetos);
    }

    @Test
    @Transactional
    public void createObjetosWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = objetosRepository.findAll().size();

        // Create the Objetos with an existing ID
        objetos.setId(1L);
        ObjetosDTO objetosDTO = objetosMapper.toDto(objetos);

        // An entity with an existing ID cannot be created, so this API call must fail
        restObjetosMockMvc.perform(post("/api/objetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objetosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Objetos in the database
        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeCreate);

        // Validate the Objetos in Elasticsearch
        verify(mockObjetosSearchRepository, times(0)).save(objetos);
    }

    @Test
    @Transactional
    public void checkTipoObjetoIsRequired() throws Exception {
        int databaseSizeBeforeTest = objetosRepository.findAll().size();
        // set the field null
        objetos.setTipoObjeto(null);

        // Create the Objetos, which fails.
        ObjetosDTO objetosDTO = objetosMapper.toDto(objetos);

        restObjetosMockMvc.perform(post("/api/objetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objetosDTO)))
            .andExpect(status().isBadRequest());

        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = objetosRepository.findAll().size();
        // set the field null
        objetos.setDescripcion(null);

        // Create the Objetos, which fails.
        ObjetosDTO objetosDTO = objetosMapper.toDto(objetos);

        restObjetosMockMvc.perform(post("/api/objetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objetosDTO)))
            .andExpect(status().isBadRequest());

        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllObjetos() throws Exception {
        // Initialize the database
        objetosRepository.saveAndFlush(objetos);

        // Get all the objetosList
        restObjetosMockMvc.perform(get("/api/objetos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objetos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoObjeto").value(hasItem(DEFAULT_TIPO_OBJETO.toString())))
            .andExpect(jsonPath("$.[*].agregarArchiboContentType").value(hasItem(DEFAULT_AGREGAR_ARCHIBO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].agregarArchibo").value(hasItem(Base64Utils.encodeToString(DEFAULT_AGREGAR_ARCHIBO))))
            .andExpect(jsonPath("$.[*].tipoObjetos").value(hasItem(DEFAULT_TIPO_OBJETOS.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())));
    }
    
    @Test
    @Transactional
    public void getObjetos() throws Exception {
        // Initialize the database
        objetosRepository.saveAndFlush(objetos);

        // Get the objetos
        restObjetosMockMvc.perform(get("/api/objetos/{id}", objetos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(objetos.getId().intValue()))
            .andExpect(jsonPath("$.tipoObjeto").value(DEFAULT_TIPO_OBJETO.toString()))
            .andExpect(jsonPath("$.agregarArchiboContentType").value(DEFAULT_AGREGAR_ARCHIBO_CONTENT_TYPE))
            .andExpect(jsonPath("$.agregarArchibo").value(Base64Utils.encodeToString(DEFAULT_AGREGAR_ARCHIBO)))
            .andExpect(jsonPath("$.tipoObjetos").value(DEFAULT_TIPO_OBJETOS.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingObjetos() throws Exception {
        // Get the objetos
        restObjetosMockMvc.perform(get("/api/objetos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateObjetos() throws Exception {
        // Initialize the database
        objetosRepository.saveAndFlush(objetos);

        int databaseSizeBeforeUpdate = objetosRepository.findAll().size();

        // Update the objetos
        Objetos updatedObjetos = objetosRepository.findById(objetos.getId()).get();
        // Disconnect from session so that the updates on updatedObjetos are not directly saved in db
        em.detach(updatedObjetos);
        updatedObjetos
            .tipoObjeto(UPDATED_TIPO_OBJETO)
            .agregarArchibo(UPDATED_AGREGAR_ARCHIBO)
            .agregarArchiboContentType(UPDATED_AGREGAR_ARCHIBO_CONTENT_TYPE)
            .tipoObjetos(UPDATED_TIPO_OBJETOS)
            .descripcion(UPDATED_DESCRIPCION);
        ObjetosDTO objetosDTO = objetosMapper.toDto(updatedObjetos);

        restObjetosMockMvc.perform(put("/api/objetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objetosDTO)))
            .andExpect(status().isOk());

        // Validate the Objetos in the database
        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeUpdate);
        Objetos testObjetos = objetosList.get(objetosList.size() - 1);
        assertThat(testObjetos.getTipoObjeto()).isEqualTo(UPDATED_TIPO_OBJETO);
        assertThat(testObjetos.getAgregarArchibo()).isEqualTo(UPDATED_AGREGAR_ARCHIBO);
        assertThat(testObjetos.getAgregarArchiboContentType()).isEqualTo(UPDATED_AGREGAR_ARCHIBO_CONTENT_TYPE);
        assertThat(testObjetos.getTipoObjetos()).isEqualTo(UPDATED_TIPO_OBJETOS);
        assertThat(testObjetos.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);

        // Validate the Objetos in Elasticsearch
        verify(mockObjetosSearchRepository, times(1)).save(testObjetos);
    }

    @Test
    @Transactional
    public void updateNonExistingObjetos() throws Exception {
        int databaseSizeBeforeUpdate = objetosRepository.findAll().size();

        // Create the Objetos
        ObjetosDTO objetosDTO = objetosMapper.toDto(objetos);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restObjetosMockMvc.perform(put("/api/objetos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(objetosDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Objetos in the database
        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Objetos in Elasticsearch
        verify(mockObjetosSearchRepository, times(0)).save(objetos);
    }

    @Test
    @Transactional
    public void deleteObjetos() throws Exception {
        // Initialize the database
        objetosRepository.saveAndFlush(objetos);

        int databaseSizeBeforeDelete = objetosRepository.findAll().size();

        // Get the objetos
        restObjetosMockMvc.perform(delete("/api/objetos/{id}", objetos.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Objetos> objetosList = objetosRepository.findAll();
        assertThat(objetosList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Objetos in Elasticsearch
        verify(mockObjetosSearchRepository, times(1)).deleteById(objetos.getId());
    }

    @Test
    @Transactional
    public void searchObjetos() throws Exception {
        // Initialize the database
        objetosRepository.saveAndFlush(objetos);
        when(mockObjetosSearchRepository.search(queryStringQuery("id:" + objetos.getId())))
            .thenReturn(Collections.singletonList(objetos));
        // Search the objetos
        restObjetosMockMvc.perform(get("/api/_search/objetos?query=id:" + objetos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(objetos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoObjeto").value(hasItem(DEFAULT_TIPO_OBJETO)))
            .andExpect(jsonPath("$.[*].agregarArchiboContentType").value(hasItem(DEFAULT_AGREGAR_ARCHIBO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].agregarArchibo").value(hasItem(Base64Utils.encodeToString(DEFAULT_AGREGAR_ARCHIBO))))
            .andExpect(jsonPath("$.[*].tipoObjetos").value(hasItem(DEFAULT_TIPO_OBJETOS.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Objetos.class);
        Objetos objetos1 = new Objetos();
        objetos1.setId(1L);
        Objetos objetos2 = new Objetos();
        objetos2.setId(objetos1.getId());
        assertThat(objetos1).isEqualTo(objetos2);
        objetos2.setId(2L);
        assertThat(objetos1).isNotEqualTo(objetos2);
        objetos1.setId(null);
        assertThat(objetos1).isNotEqualTo(objetos2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ObjetosDTO.class);
        ObjetosDTO objetosDTO1 = new ObjetosDTO();
        objetosDTO1.setId(1L);
        ObjetosDTO objetosDTO2 = new ObjetosDTO();
        assertThat(objetosDTO1).isNotEqualTo(objetosDTO2);
        objetosDTO2.setId(objetosDTO1.getId());
        assertThat(objetosDTO1).isEqualTo(objetosDTO2);
        objetosDTO2.setId(2L);
        assertThat(objetosDTO1).isNotEqualTo(objetosDTO2);
        objetosDTO1.setId(null);
        assertThat(objetosDTO1).isNotEqualTo(objetosDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(objetosMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(objetosMapper.fromId(null)).isNull();
    }
}
