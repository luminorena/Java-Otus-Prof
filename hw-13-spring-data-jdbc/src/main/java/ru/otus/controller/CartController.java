package ru.otus.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.entities.Cart;
import ru.otus.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

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
    public ResponseEntity<Object> createItemInCart(@RequestBody Cart cart){
        Cart newCart = new Cart();
        newCart.setPrice(cart.getPrice());
        newCart.setDiscount(cart.getDiscount());
        newCart.setShortName(cart.getShortName());
        newCart.setDescription(cart.getDescription());
        return new ResponseEntity<>(cartService.createNewItemInCart(newCart), HttpStatus.CREATED);
    }

    @PostMapping(value = "/update/item", produces = "application/json")
    public ResponseEntity<Object> updateItemInCart(Cart cart){
        cart.getPrice();
        cart.getDiscount();
        cart.getShortName();
        cart.getDescription();
        return new ResponseEntity<>(cartService.updateItemInCart(cart), HttpStatus.NO_CONTENT);

    }

    @PostMapping(value = "/delete/item/{id}", produces = "application/json")
    public ResponseEntity<Cart> deleteItemFromCart(@PathVariable("id") long id){
        cartService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





}
