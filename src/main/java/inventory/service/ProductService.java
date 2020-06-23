package inventory.service;

import inventory.model.Product;
import inventory.model.SubCategory;
import inventory.model.db.CategoryEntity;
import inventory.model.db.ProductEntity;
import inventory.model.db.SubCategoryEntity;
import inventory.repository.CategoryRepository;
import inventory.repository.ProductRepository;
import inventory.repository.SubCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private static Logger logger = LoggerFactory.getLogger(SubCategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Product> getProduct(Integer productId) {
        logger.info("Get product from database, product is " + productId.toString());
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()) {
            logger.info("Record not found productId is " + productId.toString());
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(new Product(productEntity.get()));
    }

    public ResponseEntity createProduct(Product product) {
        Optional<SubCategoryEntity> subCategoryDb = subCategoryRepository.findById(product.getSubCategoryId());
        if (subCategoryDb.isEmpty()) {
            logger.info("No SubCategory id: " + product.getSubCategoryId().toString());
            return ResponseEntity.status(404).build();
        }
        Optional<CategoryEntity> categoryDb = categoryRepository.findById(product.getCategoryId());
        if (categoryDb.isEmpty()) {
            logger.info("No Category id: " + product.getCategoryId().toString());
            return ResponseEntity.status(404).build();
        }
        Optional<ProductEntity> productDb = productRepository.findById(product.getId());
        if (productDb.isPresent()) {
            logger.info("Product already exist id: " + product.getId().toString());
            return ResponseEntity.status(409).build();
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setSubCategoryId(product.getSubCategoryId());
        productEntity.setCategoryId(product.getCategoryId());
        productEntity.setQuantity(product.getQuantity());
        productRepository.save(productEntity);
        logger.info("Product " + product.getId().toString() + " created in database");
        return ResponseEntity.status(201).body(product.getId().toString());
    }

    public ResponseEntity updateProductQuantity(Integer productId, Integer quantity, String name) {
        Optional<ProductEntity> productDb = productRepository.findById(productId);
        if (productDb.isEmpty()) {
            logger.info("No Product, id: " + productId.toString());
            return ResponseEntity.status(404).build();
        }
        ProductEntity productEntity = productDb.get();
        productEntity.setQuantity(quantity);
        productEntity.setName(name);
        productRepository.save(productEntity);
        logger.info("Product " + productEntity.getId().toString() + " updated in database");
        return ResponseEntity.status(202).build();
    }
}
