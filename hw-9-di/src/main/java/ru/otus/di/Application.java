package ru.otus.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import ru.otus.di.model.Product;
import ru.otus.di.service.CartImpl;
import ru.otus.di.service.ProductRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

@ComponentScan
@Scope("prototype")
public class Application {

    @Value("${count}")
    public int count;

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        ProductRepositoryImpl productRepository = context.getBean(ProductRepositoryImpl.class);

        System.out.println("Получение товара по id 2: " + productRepository.getProduct(2));

        CartImpl cart = context.getBean(CartImpl.class);

        Product newProduct = new Product(11, "test11", 1100);
        cart.addOneProduct(newProduct);
        cart.deleteOneProduct(1);

        System.out.println("В корзине находятся товары: " + productRepository.getCartItems());
    }

    @Bean
    public List<Product> productList() {
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            products.add(new Product(i, "Product " + i, i * 10.0));
        }
        System.out.println("Продукты в корзину добавлены: " + products);
        return products;
    }
}
