package com.example.demo;

import com.example.demo.manufacturer.db.models.Item;
import com.example.demo.manufacturer.dto.ItemDetailDto;
import com.example.demo.manufacturer.dto.ItemDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import com.example.demo.manufacturer.service.ItemService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @Test
    public void getAllItems() {
        List<ItemResDto> items = itemService.getAllItems();

        Assertions.assertNotNull(items);

        items.forEach(item -> {
            Assertions.assertNotNull(item.getId());
            Assertions.assertNotNull(item.getName());
            Assertions.assertNotNull(item.getPrice());
            Assertions.assertNotNull(item.getQuantity());
        });
    }

    @Test
    public void getItemById() {
        ItemDetailDto item = itemService.getItemById(2);

        Assertions.assertNotNull(item);
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getQuantity());
        Assertions.assertNotNull(item.getManufacturer().getId());
    }

    @Test
    public void createItem() {
        ItemDto itemDto = new ItemDto("name", 123, 3, 2);

        ItemDetailDto  item = itemService.create(itemDto);

        Assertions.assertNotNull(item);
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getQuantity());
        Assertions.assertNotNull(item.getManufacturer().getId());
    }

    @Test
    public void updateItem() {
        ItemDto itemDto = new ItemDto("name", 123, 3, 2);
        ItemDetailDto item = itemService.getItemById(2);
        Assertions.assertNotNull(item);

        ItemDetailDto updatedItem = itemService.updateItemById(2, itemDto);

        Assertions.assertNotNull(updatedItem);
        Assertions.assertNotNull(updatedItem.getId());
        Assertions.assertNotNull(updatedItem.getName());
        Assertions.assertNotNull(updatedItem.getPrice());
        Assertions.assertNotNull(updatedItem.getQuantity());
        Assertions.assertNotNull(updatedItem.getManufacturer().getId());
        Assertions.assertEquals(updatedItem.getManufacturer().getId(), item.getManufacturer().getId());
    }

    @Test
    public void deleteItemById() {
        itemService.deleteItemById(1);
    }

    @Test
    public void assignItemToCountry() {
        ItemDetailDto item = itemService.assignItemToCountry(3, 2);

        Assertions.assertNotNull(item);
        Assertions.assertEquals(3, item.getManufacturer().getId());
    }
}
