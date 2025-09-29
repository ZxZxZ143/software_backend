package com.example.demo.Items;

import java.util.ArrayList;
import java.util.List;

public class ItemsManagement {
    private static ItemsManagement instance;
    private ArrayList<Item> items;

    public static ItemsManagement getInstance() {
        if (instance == null) {
            synchronized (ItemsManagement.class) {
                if (instance == null) {
                    instance = new ItemsManagement();
                }
            }
        }

        return instance;
    }

    private ItemsManagement() {
        items = new ArrayList<>();
        items.add(new Item(0, "lenovo", "gdfsgfgdf", 1234f));
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public int size() {
        return items.size();
    }

    public List<Item> getItems() {
        return items;
    }
}
