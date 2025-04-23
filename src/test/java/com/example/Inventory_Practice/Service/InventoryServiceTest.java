package com.example.Inventory_Practice.Service;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import com.example.Inventory_Practice.Repo.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class InventoryServiceTest {

    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    InventoryService inventoryService;

    InventoryEntity newItem;
    InventoryEntity savedItem;
    List<InventoryEntity> items;

    @Test
    void fetchItems() {
        newItem = new InventoryEntity("bolt",2,6.00, LocalDate.of(2023,4,19));
        savedItem = new InventoryEntity("bolt",2,6.00, LocalDate.of(2023,4,19));
        items = new ArrayList<>(List.of(newItem,savedItem));
        MockitoAnnotations.openMocks(this);
        when(inventoryRepository.findAll()). thenReturn(items);
        List<InventoryEntity> listOfItemRequest = inventoryService.fetchItems();
        verify(inventoryRepository,times(1)).findAll();
        assertThat(listOfItemRequest).isEqualTo(items);
    }

    @Test
    void createItem() {
        newItem = new InventoryEntity("bolt",2,6.00, LocalDate.of(2023,4,19));
        savedItem = new InventoryEntity("bolt",2,6.00, LocalDate.of(2023,4,19));
        MockitoAnnotations.openMocks(this);
        when(inventoryRepository.save(newItem)).thenReturn(savedItem);
        InventoryEntity actualRequest = inventoryService.createItem(newItem);
        verify(inventoryRepository,times(1)).save(any(InventoryEntity.class));
        assertThat(actualRequest).isEqualTo(savedItem);
    }

    @Test
    void deleteItem(){
        MockitoAnnotations.openMocks(this);
        when(inventoryRepository.save(new InventoryEntity(null,"bolt",2,6.00, LocalDate.of(2023,4,19)))).thenReturn(new InventoryEntity(1L,"bolt",2,6.00, LocalDate.of(2023,4,19)));
        inventoryService.deleteItem(1L);
        Optional<InventoryEntity> isDeletedItem = inventoryRepository.findById(1L);
        assertThat(isDeletedItem.isEmpty());
    }
}
