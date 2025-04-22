package com.example.Inventory_Practice.Controller;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import com.example.Inventory_Practice.Service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.text.html.parser.Entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InventoryService inventoryService;

    InventoryEntity newInventoryItem;
    InventoryEntity savedInventoryItem;
    List<InventoryEntity> items = new ArrayList<>();


    @BeforeEach
    void setUp() {
        savedInventoryItem = new InventoryEntity("lug nut",3,5.76,Instant.parse("2021-03-19T11:19:42.12Z"));
        savedInventoryItem.setId(1L);
        Mockito.when(inventoryService.createItem(Mockito.any(InventoryEntity.class))).thenReturn(savedInventoryItem);
    }

    @Test
    void shouldAcceptGetRequestToFetchInventoryItem() throws Exception {

        newInventoryItem = new InventoryEntity("bolt",2,6.00, Instant.parse("2021-02-09T11:19:42.12Z"));

        items.add(newInventoryItem);
        Mockito.when(inventoryService.fetchItems()).thenReturn(items);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
        Mockito.verify(inventoryService).fetchItems();
    }

    @Test
    void shouldPostRequestToCreateItem() throws Exception {


        String savedItemJson = objectMapper.writeValueAsString(savedInventoryItem);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/inventory")
                    .contentType(APPLICATION_JSON)
                    .content(savedItemJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.item_description").value("lug nut"))
                .andExpect(jsonPath("$.qty").value(3))
                .andExpect(jsonPath("$.cost").value(5.76))
                .andExpect(jsonPath("$.date_of_last_inventory").value("2021-03-19T11:19:42.120Z"));
        Mockito.verify(inventoryService).createItem(any(InventoryEntity.class));
    }

    @Test
    void shouldAcceptPostRequestCreateItem() throws Exception {
        InventoryEntity postItem = new InventoryEntity("the key to adam's heart",1,1000000000.0,Instant.parse("2021-02-09T11:19:42.12Z"));
        postItem.setId(1L);
        String postItemJson = new ObjectMapper().writeValueAsString(postItem);
        String savedItemJson = new ObjectMapper().writeValueAsString(savedInventoryItem);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/inventory")
                    .contentType(APPLICATION_JSON)
                    .content(postItemJson))
                .andExpect(status().isCreated())
                .andExpect(content().json(savedItemJson));
        ArgumentCaptor<InventoryEntity> captor = ArgumentCaptor.forClass(InventoryEntity.class);
        Mockito.verify(inventoryService,times(1)).createItem(captor.capture());
        assertThat(captor.getValue()).usingRecursiveComparison().isEqualTo(postItem);
    }

}
