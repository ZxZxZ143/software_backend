package com.example.demo.Items;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
class ItemsService {
    private final ItemsManagement items = ItemsManagement.getInstance();

    public Item addItem(String title, String description, float price) {
        Item newItem = new Item(items.size(), title, description, price);
        items.addItem(newItem);

        return newItem;
    }

    public List<Item> getItems() {
        return items.getItems();
    }
}
