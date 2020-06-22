package inventory.model.db;

import inventory.model.SubCategory;

import javax.persistence.*;

@Entity
@Table(name = "subCategory")
public class SubCategoryEntity {

    @Id
    private Integer id;

    public String name;

    public Integer categoryId;

    public SubCategoryEntity() {}

    public SubCategoryEntity(SubCategory subCategory){
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.categoryId = subCategory.getCategoryId();
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
