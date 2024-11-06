package ru.otus;

import java.util.List;

public class ItemService {
    ItemsDao itemsDao = new ItemsDao();

    public void addNewItems() {
        for (int i = 1; i <= 100; i++) {
            Item newItem = new Item(i, "test" + i, i * 100);
            itemsDao.createItem(newItem);
        }
        System.out.println("New items have been added");
    }


    public void getItemsWithIncreasedPrice() {
        List<Item> items = itemsDao.getAllItems();
        for (Item item : items) {
            Item updatedItem = new Item(item.getId(),
                    item.getTitle(),
                    item.getPrice() * 2);
            itemsDao.updateItem(updatedItem);
        }

        System.out.println("The price for items has been increased twice");
    }

}
