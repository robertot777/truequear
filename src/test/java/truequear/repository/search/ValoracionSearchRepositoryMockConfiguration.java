package truequear.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of ValoracionSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ValoracionSearchRepositoryMockConfiguration {

    @MockBean
    private ValoracionSearchRepository mockValoracionSearchRepository;

}
