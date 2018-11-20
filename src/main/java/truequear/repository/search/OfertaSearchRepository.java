package truequear.repository.search;

import truequear.domain.Oferta;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Oferta entity.
 */
public interface OfertaSearchRepository extends ElasticsearchRepository<Oferta, Long> {
}
