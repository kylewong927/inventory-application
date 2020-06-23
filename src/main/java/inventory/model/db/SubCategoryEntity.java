package inventory.model.db;

import inventory.model.Category;
import inventory.model.SubCategory;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subCategory")
public class SubCategoryEntity {

    @Id
    private Integer id;

    private String name;

    private Date created;

    private Date updated;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    public CategoryEntity categoryEntity;

    public SubCategoryEntity() {}

    public SubCategoryEntity(SubCategory subCategory, CategoryEntity categoryEntity){
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.categoryEntity = categoryEntity;
    }

    public SubCategoryEntity(Integer id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.categoryEntity = new CategoryEntity(category);
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

    public CategoryEntity getCategoryEntity() {
        return categoryEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
}
