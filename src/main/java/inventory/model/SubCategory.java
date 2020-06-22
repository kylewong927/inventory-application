package inventory.model;

import inventory.model.db.SubCategoryEntity;

public class SubCategory {
    public Integer id;

    public String name;

    public Integer categoryId;

    public SubCategory() {}

    public SubCategory(SubCategoryEntity subCategoryEntity) {
        this.id = subCategoryEntity.getId();
        this.name = subCategoryEntity.getName();
        this.categoryId = subCategoryEntity.getCategoryId();
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
