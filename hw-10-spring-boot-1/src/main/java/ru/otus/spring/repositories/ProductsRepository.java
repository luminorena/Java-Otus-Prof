package ru.otus.spring.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.spring.dtos.ProductDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductsRepository {
    private static final Logger log = LoggerFactory.getLogger(ProductsRepository.class);
    List<ProductDTO> productsList = new ArrayList<>();

    public List<ProductDTO> printAllProducts() {
        return productsList;
    }

    public Optional<ProductDTO> printOneProduct(int productId) {
        int adjustedIndex = productId - 1;
        if (adjustedIndex >= 0 && adjustedIndex < productsList.size()) {
            return Optional.of(new ProductDTO(productsList.get(adjustedIndex).id(),
                    productsList.get(adjustedIndex).title(), productsList.get(adjustedIndex).price()));
        }
        return Optional.empty();
    }

    public void createNewProduct(int id, String title, double price) {
        ProductDTO product = new ProductDTO(id, title, price);
        productsList.add(product);
        log.info("Created product: {}", product);
    }

    public boolean isIdUnique(int id) {
        for (int i = 0; i < productsList.size(); i++) {
            return i != id;
        }
        return false;
    }
}
