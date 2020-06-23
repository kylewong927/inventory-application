package inventory.model;

import inventory.model.db.CategoryEntity;

public class Category {

    private Integer id;

    private String name;

    private Category() {}

    private Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
