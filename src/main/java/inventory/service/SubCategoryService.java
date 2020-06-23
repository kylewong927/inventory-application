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
public class SubCategoryService {

    private static Logger logger = LoggerFactory.getLogger(SubCategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public ResponseEntity<SubCategory> getSubCategory(Integer subCategoryId) {
        logger.info("Get category from database, categoryId is " + subCategoryId.toString());
        Optional<SubCategoryEntity> subCategoryEntity = subCategoryRepository.findById(subCategoryId);
        if (subCategoryEntity.isEmpty()) {
            logger.info("Record not found categoryId is " + subCategoryId.toString());
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(new SubCategory(subCategoryEntity.get()));
    }

    public ResponseEntity createSubCategory(SubCategory subCategory) {
        Optional<SubCategoryEntity> subCategoryDb = subCategoryRepository.findById(subCategory.getId());
        if (subCategoryDb.isPresent()) {
            logger.info("SubCategory already exist " + subCategory.getId().toString());
            return ResponseEntity.status(409).build();
        }
        Optional<CategoryEntity> categoryDb = categoryRepository.findById(subCategory.getCategoryId());
        if (categoryDb.isEmpty()) {
            logger.info("Category id does not exist " + subCategory.getCategoryId().toString());
            return ResponseEntity.status(409).build();
        }
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity(subCategory, categoryDb.get());
        subCategoryRepository.save(subCategoryEntity);
        logger.info("SubCategory " + subCategoryEntity.getId().toString() + " created in database");
        return ResponseEntity.status(201).build();
    }

    public ResponseEntity updateSubCategory(SubCategory subCategory) {
        Optional<SubCategoryEntity> subCategoryDb = subCategoryRepository.findById(subCategory.getId());
        if (subCategoryDb.isEmpty()) {
            logger.info("SubCategory not exist " + subCategory.getId().toString());
            return ResponseEntity.status(404).build();
        } else {
            SubCategoryEntity subCategoryEntity = subCategoryDb.get();
            subCategoryEntity.setName(subCategory.getName());
            subCategoryRepository.save(subCategoryEntity);
            logger.info("SubCategory " + subCategory.getId().toString() + " updated in database");
        }

        return ResponseEntity.status(202).build();
    }
}
