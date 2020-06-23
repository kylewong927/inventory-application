package inventory.service;

import inventory.model.Category;
import inventory.model.Product;
import inventory.model.SubCategory;
import inventory.model.db.CategoryEntity;
import inventory.model.db.ProductEntity;
import inventory.model.db.SubCategoryEntity;
import inventory.repository.CategoryRepository;
import inventory.repository.ProductRepository;
import inventory.repository.SubCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        categoryRepository.deleteAll();
        subCategoryRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testWhenCreateProductThenReturn201() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity(new SubCategory(1, "Shoe", 1), categoryEntity1);
        when(subCategoryRepository.findById(1)).thenReturn(Optional.of(subCategoryEntity1));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        ResponseEntity<SubCategory> subCategoryResponseEntity = productService.createProduct(new Product(1,"Nike", 1, 1, 19));
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testWhenCreateProductThenReturn409() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity(new SubCategory(1, "Shoe", 1), categoryEntity1);
        ProductEntity productEntity = new ProductEntity(1, "Nike", 1, 1, 19);
        when(subCategoryRepository.findById(1)).thenReturn(Optional.of(subCategoryEntity1));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        when(productRepository.findById(1)).thenReturn(Optional.of(productEntity));
        ResponseEntity<SubCategory> subCategoryResponseEntity = productService.createProduct(new Product(1,"Nike", 1, 1, 19));
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void testWhenUpdateProductThenReturn202() {
        ProductEntity productEntity = new ProductEntity(1, "Nike", 1, 1, 19);
        when(productRepository.findById(productEntity.getId())).thenReturn(Optional.of(productEntity));
        ResponseEntity<SubCategory> subCategoryResponseEntity = productService.updateProductQuantity(productEntity.getId(), 1, "NB");
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void testWhenUpdateProductThenReturn404() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<SubCategory> subCategoryResponseEntity = productService.updateProductQuantity(1, 1, "NB");
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
