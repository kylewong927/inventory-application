package inventory.model;

import inventory.model.db.ProductEntity;

public class Product {

    private Integer id;

    private String name;

    private Integer subCategoryId;

    private Integer categoryId;

    private Integer quantity;

    public Product(Integer id, String name, Integer subCategoryId, Integer categoryId, Integer quantity) {
        this.id = id;
        this.name = name;
        this.subCategoryId = subCategoryId;
        this.categoryId = categoryId;
        this.quantity = quantity;
    }

    public Product(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.subCategoryId = productEntity.getSubCategoryId();
        this.categoryId = productEntity.getCategoryId();
        this.quantity = productEntity.getQuantity();
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
}
