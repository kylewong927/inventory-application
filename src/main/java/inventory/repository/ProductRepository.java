package inventory.repository;

import inventory.model.db.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<ProductEntity, Integer> {
}
