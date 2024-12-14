package ru.otus.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.otus.di.model.Product;

import java.util.ArrayList;
import java.util.List;

@PropertySource("classpath:application.yml")
@Component
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList<>();

    @Autowired
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.id() == id) {
                return product;
            }
        }

        return null;
    }

    @Override
    public List<Product> getCartItems() {
        return new ArrayList<>(products);
    }
}
