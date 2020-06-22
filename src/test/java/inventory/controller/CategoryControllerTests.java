package inventory.controller;

import inventory.model.Category;
import inventory.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testWhenServiceCanFindCategoryIdThenReturn403() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Food");
        MockHttpServletRequestBuilder builder = post("http://localhost:8080/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Food\"}");
        when(categoryService.createCategory(category)).thenReturn(ResponseEntity.status(403).build());
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testWhenServiceCannotFindCategoryIdThenReturn201() throws Exception {
        Category category = new Category();
        category.setId(1);
        category.setName("Food");
        MockHttpServletRequestBuilder builder = post("http://localhost:8080/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": 1, \"name\": \"Food\"}");
        when(categoryService.createCategory(category)).thenReturn(ResponseEntity.status(201).build());
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
