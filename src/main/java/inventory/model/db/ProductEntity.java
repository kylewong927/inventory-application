package inventory.model.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    private Integer id;

    private String name;

    private Integer subCategoryId;

    private Integer categoryId;

    private Integer quantity;

    private Date created;

    private Date updated;

    @Column(name = "lastUpdate", updatable= false, insertable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public ProductEntity() {}

    public ProductEntity(Integer id, String name, Integer subCategoryId, Integer categoryId, Integer quantity) {
        this.id = id;
        this.name = name;
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.quantity = quantity;
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

    public Integer getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Integer subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
