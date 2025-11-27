package com.example.demo.manufacturer.controllers;

import com.example.demo.manufacturer.dto.ItemDetailDto;
import com.example.demo.manufacturer.dto.ItemDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import com.example.demo.manufacturer.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResDto>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailDto> getItemById(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PostMapping
    public ResponseEntity<ItemDetailDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(itemDto));
    }

    @PostMapping("/{countryId}/assign/{itemId}")
    public ResponseEntity<ItemDetailDto> assignItem(@PathVariable Integer countryId, @PathVariable Integer itemId) {
        return ResponseEntity.status(HttpStatus.OK).body(itemService.assignItemToCountry(countryId, itemId));
    }
}
