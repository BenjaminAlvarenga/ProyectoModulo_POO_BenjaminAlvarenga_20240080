package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Repository;

import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Entity.autoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface autoresRepository extends JpaRepository<autoresEntity, Long> {
}
