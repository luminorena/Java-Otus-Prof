package ru.otus.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.di.model.Product;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.yml")
@Component
public class CartImpl implements Cart {
    private List<Product> products = new ArrayList<>();

    @Autowired
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public void addOneProduct(Product product) {
        if (product != null) {
            products.add(product);
            System.out.println("В корзину был добавлен продукт: " + product);
        } else {
            System.out.println("Продукт не найден.");
        }
    }

    @Override
    public void deleteOneProduct(int id) {
        products.removeIf(current -> current.id() == id);
        System.out.println("Продукт с id = " + id + " удалён");
    }
}
