package inventory.service;

import inventory.model.Category;
import inventory.model.SubCategory;
import inventory.model.db.CategoryEntity;
import inventory.model.db.SubCategoryEntity;
import inventory.repository.CategoryRepository;
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
public class SubCategoryServiceTests {
    @InjectMocks
    private SubCategoryService subCategoryService;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        categoryRepository.deleteAll();
        subCategoryRepository.deleteAll();
    }

    @Test
    public void testWhenGetSpecificSubCategoryThenReturnOneRecord() {
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity(1, "Apple", new Category(1, "Food"));
        when(subCategoryRepository.findById(1)).thenReturn(Optional.of(subCategoryEntity1));
        ResponseEntity<SubCategory> subCategoryResponseEntity = subCategoryService.getSubCategory(1);
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(subCategoryResponseEntity.getBody().getId(), subCategoryEntity1.getId());
        assertEquals(subCategoryResponseEntity.getBody().getName(), subCategoryEntity1.getName());
    }

    @Test
    public void testWhenGetSpecificCategoryThenReturnNotFound() {
        when(subCategoryRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<SubCategory> subCategoryResponseEntity = subCategoryService.getSubCategory(1);
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testWhenCreateSubCategoryThenReturn201() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        when(subCategoryRepository.findById(1)).thenReturn(Optional.empty());
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        ResponseEntity<SubCategory> subCategoryResponseEntity = subCategoryService.createSubCategory(new SubCategory(1, "Apple", 1));
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testWhenCreateSubCategoryThenReturn409() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity(1, "Apple", new Category(1, "Food"));
        when(subCategoryRepository.findById(1)).thenReturn(Optional.of(subCategoryEntity1));
        ResponseEntity<SubCategory> subCategoryResponseEntity = subCategoryService.createSubCategory(new SubCategory(1, "Apple", 1));
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void testWhenUpdateCategoryThenReturn202() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity(1, "Apple", new Category(1, "Food"));
        when(subCategoryRepository.findById(1)).thenReturn(Optional.of(subCategoryEntity1));
        ResponseEntity<SubCategory> subCategoryResponseEntity = subCategoryService.updateSubCategory(new SubCategory(1, "Apple1", 1));
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void testWhenUpdateCategoryThenReturn404() {
        when(subCategoryRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<SubCategory> subCategoryResponseEntity = subCategoryService.updateSubCategory(new SubCategory(1, "Apple1", 1));
        assertEquals(subCategoryResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
