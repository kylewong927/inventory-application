package inventory.model.db;

import inventory.model.Category;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @Column(nullable=false)
    private Integer id;

    private String name;

    private Date created;

    private Date updated;

    public CategoryEntity() {}

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryEntity(Category category){
        this.id = category.getId();
        this.name = category.getName();
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

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
