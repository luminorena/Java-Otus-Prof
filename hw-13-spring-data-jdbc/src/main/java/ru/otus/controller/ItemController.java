package ru.otus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.dtos.ItemDTO;
import ru.otus.entities.Item;
import ru.otus.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(value = "/all_items", produces = "application/json")
    public List<Item> getAllItems() {
        return itemService.findAllItems();
    }


    @GetMapping(value = "/one_item/{id}", produces = "application/json")
    public ResponseEntity<Object> getOneItem(@PathVariable("id") long id) {
        if (itemService.getItemById(id).isEmpty()) {
            return new ResponseEntity<>("Возможно, элемент уже удалён", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/create_item", produces = "application/json")
    public ResponseEntity<Object> createItem(@RequestBody ItemDTO itemDTO){
        return new ResponseEntity<>(itemService.createNewItemIn(itemDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/item", produces = "application/json")
    public ResponseEntity<Object> updateItem(@RequestBody ItemDTO itemDTO){
        return new ResponseEntity<>(itemService.updateItem(itemDTO), HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/delete/item/{id}", produces = "application/json")
    public ResponseEntity<Item> deleteItem(@PathVariable("id") long id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





}
