package com.example.demo;

import com.example.demo.manufacturer.db.models.Item;
import com.example.demo.manufacturer.db.repositories.ItemRepo;
import com.example.demo.manufacturer.dto.ItemDetailDto;
import com.example.demo.manufacturer.dto.ItemDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import com.example.demo.manufacturer.mappers.ItemMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepo itemRepo;

    @Test
    @Transactional
    void EntityToDetailDto() {
        Item item = itemRepo.findAll().getFirst();

        ItemDetailDto itemDetailDto = itemMapper.toDetailDto(item);

        Assertions.assertNotNull(itemDetailDto);

        Assertions.assertNotNull(itemDetailDto.getId());
        Assertions.assertNotNull(itemDetailDto.getName());
        Assertions.assertNotNull(itemDetailDto.getPrice());
        Assertions.assertNotNull(itemDetailDto.getQuantity());
        Assertions.assertNotNull(itemDetailDto.getManufacturer());

        Assertions.assertEquals(item.getManufacturer().getId(), itemDetailDto.getManufacturer().getId());
        Assertions.assertEquals(item.getName(), itemDetailDto.getName());
        Assertions.assertEquals(item.getPrice(), itemDetailDto.getPrice());
        Assertions.assertEquals(item.getQuantity(), itemDetailDto.getQuantity());
        Assertions.assertEquals(item.getId(), itemDetailDto.getId());
    }

    @Test
    @Transactional
    void convertDtoToEntityTest() {
        ItemDto itemDto = new ItemDto("name", 123, 12, 1);

        Item item = itemMapper.toEntity(itemDto);

        itemRepo.save(item);

        Assertions.assertNotNull(item);

        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getQuantity());

        Assertions.assertEquals(itemDto.getName(), item.getName());
        Assertions.assertEquals(itemDto.getPrice(), item.getPrice());
        Assertions.assertEquals(itemDto.getQuantity(), item.getQuantity());
    }

    @Test
    @Transactional
    void convertEntityToDtoListTest() {
        List<Item> items = itemRepo.findAll();

        List<ItemResDto>  itemResDtos = itemMapper.toListDto(items);

        Assertions.assertNotNull(itemResDtos);

        Assertions.assertNotEquals(0, itemResDtos.size());

        for (int i = 0; i < itemResDtos.size(); i++) {
            Assertions.assertNotNull(itemResDtos.get(i));

            Assertions.assertNotNull(itemResDtos.get(i).getId());
            Assertions.assertNotNull(itemResDtos.get(i).getName());
            Assertions.assertNotNull(itemResDtos.get(i).getPrice());
            Assertions.assertNotNull(itemResDtos.get(i).getQuantity());

            Assertions.assertEquals(items.get(i).getId(), itemResDtos.get(i).getId());
            Assertions.assertEquals(items.get(i).getName(), itemResDtos.get(i).getName());
            Assertions.assertEquals(items.get(i).getPrice(), itemResDtos.get(i).getPrice());
            Assertions.assertEquals(items.get(i).getQuantity(), itemResDtos.get(i).getQuantity());
        }
    }
}
