package inventory.model;

import inventory.model.db.CategoryEntity;

public class Category {

    public Integer id;

    public String name;

    public Category() {}

    public Category(CategoryEntity categoryEntity) {
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
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
}
