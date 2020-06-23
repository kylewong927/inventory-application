package inventory.controller;

import inventory.model.SubCategory;
import inventory.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subCategories")
public class SubCategoryController {
    private static Logger logger = LoggerFactory.getLogger(SubCategoryController.class);

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable("subCategoryId") Integer subCategoryId) {
        logger.info("Received GET call to a subCategory with id " + subCategoryId.toString());
        return subCategoryService.getSubCategory(subCategoryId);
    }

    @PostMapping
    public ResponseEntity createSubCategory(@RequestBody SubCategory subCategory) {
        logger.info("Received POST call to create subCategory");
        return subCategoryService.createSubCategory(subCategory);
    }

    @PutMapping
    public ResponseEntity updateSubCategory(@RequestBody SubCategory subCategory) {
        logger.info("Received PUT call to create subCategory");
        return subCategoryService.updateSubCategory(subCategory);
    }
}
