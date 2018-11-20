package truequear.repository.search;

import truequear.domain.Valoracion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Valoracion entity.
 */
public interface ValoracionSearchRepository extends ElasticsearchRepository<Valoracion, Long> {
}
