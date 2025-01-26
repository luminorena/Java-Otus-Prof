package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dtos.CartDTO;
import ru.otus.entities.Cart;
import ru.otus.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;

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

    public Cart createNewItemInCart(CartDTO cartDTO){
        Cart cart = new Cart(null, cartDTO.price(), cartDTO.discount(),
                cartDTO.shortName(), cartDTO.description());
        return cartRepository.save(cart);

    }

    public Cart updateItemInCart(CartDTO cartDTO) {
        Cart cart = new Cart(cartDTO.id(), cartDTO.price(), cartDTO.discount(),
                cartDTO.shortName(), cartDTO.description());
        return cartRepository.save(cart);
    }

    public void deleteItemById(long id) {
        cartRepository.deleteById(id);
    }
}
