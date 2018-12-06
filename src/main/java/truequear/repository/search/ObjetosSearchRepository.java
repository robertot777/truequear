package truequear.repository.search;

import truequear.domain.Objetos;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Objetos entity.
 */
public interface ObjetosSearchRepository extends ElasticsearchRepository<Objetos, Long> {
}
