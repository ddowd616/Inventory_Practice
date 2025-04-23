package com.example.Inventory_Practice.Controller;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import com.example.Inventory_Practice.Service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseEntity<InventoryEntity> createItem(@RequestBody InventoryEntity newItem){
        return new ResponseEntity<>(inventoryService.createItem(newItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
   public Long deleteItem(@PathVariable Long id) {
        return inventoryService.deleteItem(id);
    }
}
