package inventory.controller;

import inventory.model.Inventory;
import inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("inventories")
public class InventoryController {

    private static Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{inventoryName}")
    public ResponseEntity<List<Inventory>> getAllInventory() {

        return ResponseEntity.status(200).body(inventoryService.getAllInventory());
    }

    @GetMapping("/{inventoryName}")
    public ResponseEntity<Inventory> getInventory(@PathVariable("inventoryName") String inventoryName) {
        Inventory inventory = inventoryService.getInventory(inventoryName);
        if (inventory == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(inventory);
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.createInventory(inventory);
    }

    @PutMapping("/{inventoryName}")
    public ResponseEntity updateInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.status(202).build();
    }
}
