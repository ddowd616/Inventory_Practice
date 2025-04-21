package com.example.Inventory_Practice.Service;

import com.example.Inventory_Practice.Repo.InventoryRepository;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@org.springframework.stereotype.Service
public class InventoryService {
    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository){
        this.repository = repository;
    }

}
