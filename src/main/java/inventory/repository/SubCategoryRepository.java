package inventory.repository;

import inventory.model.db.CategoryEntity;
import inventory.model.db.SubCategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity, Integer> {

    List<SubCategoryEntity> findAllByCategoryId(Integer categoryId);
}
