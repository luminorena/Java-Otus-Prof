package ru.otus.di.service;

import ru.otus.di.model.Product;


public interface Cart {
    void addOneProduct(Product product);

    void deleteOneProduct(int id);
}
