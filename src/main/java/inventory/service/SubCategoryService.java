package inventory.service;

import inventory.model.Category;
import inventory.model.SubCategory;
import inventory.model.db.CategoryEntity;
import inventory.model.db.SubCategoryEntity;
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
            return ResponseEntity.status(403).build();
        }
        SubCategoryEntity subCategoryEntity = new SubCategoryEntity(subCategory);
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
            subCategoryEntity.setCategoryId(subCategory.getCategoryId());
            subCategoryRepository.save(subCategoryEntity);
            logger.info("SubCategory " + subCategory.getId().toString() + " updated in database");
        }

        return ResponseEntity.status(204).build();
    }
}
