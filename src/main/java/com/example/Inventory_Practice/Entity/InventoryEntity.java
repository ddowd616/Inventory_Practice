package com.example.Inventory_Practice.Entity;

import jakarta.persistence.*;


import java.time.Instant;


@Entity
@Table(name="Inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String item_description;
    private Integer qty;
    private Double cost;
    private Instant date_of_last_inventory;

    public InventoryEntity() {

    }

    public InventoryEntity( String item_description, Integer qty, Double cost, Instant date_of_last_inventory) {
        this.item_description = item_description;
        this.qty = qty;
        this.cost = cost;
        this.date_of_last_inventory = date_of_last_inventory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Instant getDate_of_last_inventory() {
        return date_of_last_inventory;
    }

    public void setDate_of_last_inventory(Instant date_of_last_inventory) {
        this.date_of_last_inventory = date_of_last_inventory;
    }
}
