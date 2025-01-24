package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.entities.Cart;
import ru.otus.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart> findAllItemsInCart(){
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartById(long id){
       return cartRepository.findById(id);
    }

    public Cart createNewItemInCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart updateItemInCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteItemById(long id) {
        cartRepository.deleteById(id);
    }
}
