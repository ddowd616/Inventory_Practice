package com.example.Inventory_Practice.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Entity
@Table(name="Inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String item_description;
    private Integer qty;
    private Double cost;
    private Instant date_of_last_inventory;




}
