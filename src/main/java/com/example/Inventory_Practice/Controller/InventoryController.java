package com.example.Inventory_Practice.Controller;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import com.example.Inventory_Practice.Repo.InventoryRepository;
import com.example.Inventory_Practice.Service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;


    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;

    }

    @GetMapping
    public List<InventoryEntity> fetchItems() {
        return inventoryService.fetchItems();
    }

    @PostMapping
    public ResponseEntity<InventoryEntity> createItem(@RequestBody InventoryEntity newItem) {
        System.out.println("Received item: " + newItem); // Log the received item
        try {
            InventoryEntity savedItem = inventoryService.createItem(newItem);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
   public Long deleteItem(@PathVariable Long id) {
        return inventoryService.deleteItem(id);
    }
}
