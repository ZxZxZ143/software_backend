package com.example.demo.Items;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemsController {
    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("/ping")
    public boolean ping() {
        return true;
    }

    @PostMapping("/addItem")
    public Item addItem(@RequestBody ItemDto item) {
        return itemsService.addItem(item.getTitle(), item.getDescription(), item.getPrice());
    }

    @GetMapping("/getItems")
    public List<Item> getItems() {
        return itemsService.getItems();
    }
}
