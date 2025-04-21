package com.example.Inventory_Practice.Repo;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
