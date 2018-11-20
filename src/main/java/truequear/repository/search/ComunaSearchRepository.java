package truequear.repository.search;

import truequear.domain.Comuna;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Comuna entity.
 */
public interface ComunaSearchRepository extends ElasticsearchRepository<Comuna, Long> {
}
