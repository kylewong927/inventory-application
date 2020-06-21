package inventory.service;

import inventory.model.Inventory;
import inventory.model.db.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        Iterable<InventoryEntity> inventoryEntities = inventoryRepository.findAll();
        List<Inventory> inventoryList = new ArrayList();
        for(InventoryEntity inventoryEntity: inventoryEntities) {
            inventoryList.add(new Inventory(inventoryEntity));
        }
        return inventoryList;
    }

    public Inventory getInventory(String inventoryName) {
        if (inventoryName != null) {
            Optional<InventoryEntity> inventoryEntity = inventoryRepository.findByName(inventoryName.toLowerCase());
            return (inventoryEntity.isPresent()) ?  new Inventory(inventoryEntity.get()) : null;
        }
        return null;
    }

    public Inventory createInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = new InventoryEntity(inventory);
        inventoryEntity = inventoryRepository.save(inventoryEntity);
        return new Inventory(inventoryEntity);
    }
}
