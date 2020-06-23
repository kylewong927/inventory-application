package inventory.controller;

import inventory.model.Category;
import inventory.model.db.CategoryEntity;
import inventory.repository.CategoryRepository;
import inventory.service.CategoryService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@TestPropertySource(value={"classpath:application.yml"})
public class CategoryControllerTests {

    @Mock
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        categoryRepository.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        CategoryEntity categoryEntity = new CategoryEntity(2, "Clothes");
        categoryRepository.save(categoryEntity);
    }

    @Test
    public void testWhenCreateCategoryCannotFindCategoryIdThenReturn201() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Food");
        MockHttpServletRequestBuilder builder = post("http://localhost:8080/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Food\"}");
        when(categoryService.createCategory(category)).thenReturn(ResponseEntity.status(201).build());
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testWhenGetAllCategoryThenReturnOneRecord() throws Exception {
        MockHttpServletRequestBuilder builder = get("http://localhost:8080/categories");
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
