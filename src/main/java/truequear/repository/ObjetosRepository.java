package truequear.repository;

import truequear.domain.Objetos;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Objetos entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ObjetosRepository extends JpaRepository<Objetos, Long> {

}
