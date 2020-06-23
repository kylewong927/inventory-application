package inventory.model;

import inventory.model.db.SubCategoryEntity;

public class SubCategory {

    private Integer id;

    private String name;

    private Integer categoryId;

    public SubCategory() {}

    public SubCategory(Integer id, String name, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    public SubCategory(SubCategoryEntity subCategoryEntity) {
        this.id = subCategoryEntity.getId();
        this.name = subCategoryEntity.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
