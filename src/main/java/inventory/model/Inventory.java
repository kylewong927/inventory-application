package inventory.model;

import inventory.model.db.InventoryEntity;

public class Inventory {
    public String id;
    public String name;
    public Category category;
    public SubCategory subCategory;
    public int quantity;

    public Inventory(InventoryEntity inventoryEntity) {
        this.id = inventoryEntity.getId().toString();
        this.name = inventoryEntity.getName();
        this.category = inventoryEntity.getCategory();
        this.subCategory = inventoryEntity.getSubCategory();
        this.quantity = inventoryEntity.getQuantity();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
