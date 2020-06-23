package inventory.controller;

import inventory.model.Product;
import inventory.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getCategory(@PathVariable("productId") Integer productId) {
        logger.info("Received GET call to a product with id " + productId.toString());
        return productService.getProduct(productId);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        logger.info("Received POST call to create product");
        return productService.createProduct(product);
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestParam Integer productId, String name, Integer quantity) {
        logger.info("Received PUT call to update product");
        return productService.updateProductQuantity(productId, quantity, name);
    }
}
