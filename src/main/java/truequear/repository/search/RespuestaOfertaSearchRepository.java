package truequear.repository.search;

import truequear.domain.RespuestaOferta;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the RespuestaOferta entity.
 */
public interface RespuestaOfertaSearchRepository extends ElasticsearchRepository<RespuestaOferta, Long> {
}
