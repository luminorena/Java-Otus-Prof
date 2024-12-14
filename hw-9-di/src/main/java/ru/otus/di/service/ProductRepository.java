package ru.otus.di.service;

import ru.otus.di.model.Product;

import java.util.List;

public interface ProductRepository {
    Product getProduct(int id);

    List<Product> getCartItems();
}
