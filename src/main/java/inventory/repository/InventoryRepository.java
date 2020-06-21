package inventory.repository;

import inventory.model.db.InventoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends CrudRepository<InventoryEntity, UUID> {

    Optional<InventoryEntity> findByName(String Name);
}
