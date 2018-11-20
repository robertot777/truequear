package truequear.repository;

import truequear.domain.RespuestaOferta;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the RespuestaOferta entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RespuestaOfertaRepository extends JpaRepository<RespuestaOferta, Long> {

}
