package ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Repository;

import ProyectoModulo_BenjaminAlvarenga_20240080.BenjaminAlvarenga_20240080.Entity.librosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface librosRepository extends JpaRepository<librosEntity, Long> {
}
