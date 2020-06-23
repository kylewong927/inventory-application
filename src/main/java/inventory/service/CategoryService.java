package inventory.service;

import inventory.model.Category;
import inventory.model.SubCategory;
import inventory.model.db.CategoryEntity;
import inventory.model.db.SubCategoryEntity;
import inventory.repository.CategoryRepository;
import inventory.repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public List<Category> getAllCategory() {
        logger.info("Get all category from database");
        Iterable<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<Category> categoryList = new ArrayList();
        for(CategoryEntity categoryEntity: categoryEntities) {
            categoryList.add(new Category(categoryEntity));
        }
        return categoryList;
    }

    public List<SubCategory> getAllSubCategory(Integer categoryId) {
        logger.info("Get all subCategory from database");
        List<SubCategoryEntity> subCategoryEntityList = subCategoryRepository.findAllById(categoryId);
        List<SubCategory> subCategoryList = new ArrayList();
        for(SubCategoryEntity subCategoryEntity: subCategoryEntityList) {
            subCategoryList.add(new SubCategory(subCategoryEntity));
        }
        return subCategoryList;
    }

    public ResponseEntity<Category> getCategory(Integer categoryId) {
        logger.info("Get category from database, categoryId is " + categoryId.toString());
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(categoryId);
        if (categoryEntity.isEmpty()) {
            logger.info("Record not found categoryId is " + categoryId.toString());
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(new Category(categoryEntity.get()));
    }

    public ResponseEntity createCategory(Category category) {
        logger.info("Create category in database, categoryId is " + category.toString());
        Optional<CategoryEntity> categoryDb = categoryRepository.findById(category.getId());
        if (categoryDb.isPresent()) {
            logger.info("Category already exist " + category.getId().toString());
            return ResponseEntity.status(409).build();
        }
        CategoryEntity categoryEntity = new CategoryEntity(category);
        categoryRepository.save(categoryEntity);
        logger.info("Category " + categoryEntity.getId().toString() + " created in database");
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity updateCategory(Category category) {
        Optional<CategoryEntity> categoryDb = categoryRepository.findById(category.getId());
        if (categoryDb.isEmpty()) {
            logger.info("Category not exist " + category.getId().toString());
            return ResponseEntity.status(404).build();
        } else {
            CategoryEntity categoryEntity = categoryDb.get();
            categoryEntity.setName(category.getName());
            categoryRepository.save(categoryEntity);
            logger.info("Category " + category.getId().toString() + " updated in database");
        }

        return ResponseEntity.status(202).build();
    }
}
