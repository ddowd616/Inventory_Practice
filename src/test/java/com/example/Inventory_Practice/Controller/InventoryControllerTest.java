package com.example.Inventory_Practice.Controller;

import com.example.Inventory_Practice.Entity.InventoryEntity;
import com.example.Inventory_Practice.Service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.swing.text.html.parser.Entity;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InventoryController inventoryController;

    @MockitoBean
    private InventoryService inventoryService;

    InventoryEntity newInventoryItem;

    @Test
    void shouldAcceptGetRequestToFetchInventoryItem() throws Exception {
        newInventoryItem = new InventoryEntity(1L, "")
        mockMvc.perform(MockMvcRequestBuilders.get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$",hasSize(1)));
    }

}
