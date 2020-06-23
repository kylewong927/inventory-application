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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    @BeforeEach
    public void setup() {
        categoryRepository.deleteAll();
        subCategoryRepository.deleteAll();
    }

    @Test
    public void testWhenGetAllCategoryThenReturnListOfRecord() throws Exception {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(1);
        categoryEntity1.setName("Food");
        CategoryEntity categoryEntity2 = new CategoryEntity();
        categoryEntity2.setId(2);
        categoryEntity2.setName("Clothes");
        categoryEntities.add(categoryEntity1);
        categoryEntities.add(categoryEntity2);
        Iterable<CategoryEntity> categoryEntityIterable = categoryEntities;
        when(categoryRepository.findAll()).thenReturn(categoryEntityIterable);
        List<Category> categoryList = categoryService.getAllCategory();
        assertEquals(categoryList.size(), 2);
        assertEquals(categoryList.get(0).getId(), categoryEntity1.getId());
        assertEquals(categoryList.get(0).getName(), categoryEntity1.getName());
        assertEquals(categoryList.get(1).getId(), categoryEntity2.getId());
        assertEquals(categoryList.get(1).getName(), categoryEntity2.getName());
    }

    @Test
    public void testWhenGetSpecificSubCategoryThenReturnOneRecord() {
        CategoryEntity categoryEntity1 = new CategoryEntity(new Category(1, "Food"));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        ResponseEntity<Category> categoryResponseEntity = categoryService.getCategory(1);
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(categoryResponseEntity.getBody().getId(), categoryEntity1.getId());
        assertEquals(categoryResponseEntity.getBody().getName(), categoryEntity1.getName());
    }

    @Test
    public void testWhenGetSpecificSubCategoryThenReturnRecordList() {
        List<SubCategoryEntity> subCategoryEntityList = new ArrayList<SubCategoryEntity>();
        SubCategoryEntity subCategoryEntity1 = new SubCategoryEntity(1, "Apple", new Category(1, "Food"));
        SubCategoryEntity subCategoryEntity2 = new SubCategoryEntity(2, "Banana", new Category(1, "Food"));
        subCategoryEntityList.add(subCategoryEntity1);
        subCategoryEntityList.add(subCategoryEntity2);
        when(subCategoryRepository.findAllById(1)).thenReturn(subCategoryEntityList);
        List<SubCategory> SubCategoryList = categoryService.getAllSubCategory(1);
        assertEquals(SubCategoryList.size(), 2);
        assertEquals(SubCategoryList.get(0).getId(), subCategoryEntity1.getId());
        assertEquals(SubCategoryList.get(0).getName(), subCategoryEntity1.getName());
        assertEquals(SubCategoryList.get(1).getId(), subCategoryEntity2.getId());
        assertEquals(SubCategoryList.get(1).getName(), subCategoryEntity2.getName());
    }

    @Test
    public void testWhenGetSpecificCategoryThenReturnOneRecord() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        ResponseEntity<Category> categoryResponseEntity = categoryService.getCategory(1);
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(categoryResponseEntity.getBody().getId(), categoryEntity1.getId());
        assertEquals(categoryResponseEntity.getBody().getName(), categoryEntity1.getName());
    }

    @Test
    public void testWhenGetSpecificCategoryThenReturnNotFound() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Category> categoryResponseEntity = categoryService.getCategory(1);
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void testWhenCreateCategoryThenReturn201() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Category> categoryResponseEntity = categoryService.createCategory(new Category(1, "Food"));
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    public void testWhenCreateCategoryThenReturn409() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        ResponseEntity<Category> categoryResponseEntity = categoryService.createCategory(new Category(1, "Animal"));
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.CONFLICT);
    }

    @Test
    public void testWhenUpdateCategoryThenReturn202() {
        CategoryEntity categoryEntity1 = new CategoryEntity(1, "Food");
        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryEntity1));
        ResponseEntity<Category> categoryResponseEntity = categoryService.updateCategory(new Category(1, "Animal"));
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.ACCEPTED);
    }

    @Test
    public void testWhenUpdateCategoryThenReturn404() {
        when(categoryRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Category> categoryResponseEntity = categoryService.updateCategory(new Category(1, "Animal"));
        assertEquals(categoryResponseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
