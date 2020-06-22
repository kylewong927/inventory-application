package inventory.controller;

import inventory.model.Category;
import inventory.model.SubCategory;
import inventory.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategory() {
        logger.info("Received GET call to get all category");
        return ResponseEntity.status(200).body(categoryService.getAllCategory());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId) {
        logger.info("Received GET call to a category with id " + categoryId.toString());
        return categoryService.getCategory(categoryId);
    }

    @GetMapping("/{categoryId}/subcategories")
    public ResponseEntity<List<SubCategory>> getAllSubCategory(@PathVariable("categoryId") Integer categoryId) {
        logger.info("Received GET call to get all subCategory");
        return ResponseEntity.status(200).body(categoryService.getAllSubCategory(categoryId));
    }
    
    @PostMapping
    public ResponseEntity createCategory(@RequestBody Category category) {
        logger.info("Received POST call to create category");
        return categoryService.createCategory(category);
    }

    @PutMapping
    public ResponseEntity updateCategory(@RequestBody Category category) {
        logger.info("Received PUT call to create category");
        return categoryService.updateCategory(category);
    }


}
