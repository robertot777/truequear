package truequear.repository.search;

import truequear.domain.Direccion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Direccion entity.
 */
public interface DireccionSearchRepository extends ElasticsearchRepository<Direccion, Long> {
}
