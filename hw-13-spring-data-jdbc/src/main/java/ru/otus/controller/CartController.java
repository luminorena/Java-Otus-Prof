package ru.otus.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.dtos.CartDTO;
import ru.otus.entities.Cart;
import ru.otus.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/all_items", produces = "application/json")
    public List<Cart> getAllItemsFromCart() {
        return cartService.findAllItemsInCart();
    }


    @GetMapping(value = "/one_item/{id}", produces = "application/json")
    public ResponseEntity<Object> getOneItem(@PathVariable("id") long id) {
        if (cartService.getCartById(id).isEmpty()) {
            return new ResponseEntity<>("Возможно, элемент уже удалён", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/create_item", produces = "application/json")
    public ResponseEntity<Object> createItemInCart(@RequestBody CartDTO cartDTO){
        return new ResponseEntity<>(cartService.createNewItemInCart(cartDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/item", produces = "application/json")
    public ResponseEntity<Object> updateItemInCart(@RequestBody CartDTO cartDTO){
        return new ResponseEntity<>(cartService.updateItemInCart(cartDTO), HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/delete/item/{id}", produces = "application/json")
    public ResponseEntity<Cart> deleteItemFromCart(@PathVariable("id") long id){
        cartService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





}
