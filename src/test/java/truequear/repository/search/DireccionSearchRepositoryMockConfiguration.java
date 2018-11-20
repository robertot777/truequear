package truequear.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of DireccionSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class DireccionSearchRepositoryMockConfiguration {

    @MockBean
    private DireccionSearchRepository mockDireccionSearchRepository;

}
