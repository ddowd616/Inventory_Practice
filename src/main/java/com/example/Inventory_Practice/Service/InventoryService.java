package com.example.Inventory_Practice.Service;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import com.example.Inventory_Practice.Repo.InventoryRepository;


import java.util.List;


@org.springframework.stereotype.Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService( InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;
    }

    public List<InventoryEntity> fetchItems() {
        return inventoryRepository.findAll();
    }

    public InventoryEntity createItem(InventoryEntity newItem) {
        return inventoryRepository.save(newItem);
    }

    public Long deleteItem(Long id) {
        inventoryRepository.deleteById(id);
        return id;
    }
}
