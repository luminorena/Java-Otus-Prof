package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.dtos.ProductDTO;
import ru.otus.spring.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductsRepository productsRepository;

    public Optional<ProductDTO> getProductById(int id) {
        return productsRepository.printOneProduct(id);
    }

    public ProductDTO createNewProduct(ProductDTO productDTO) {
        productsRepository.createNewProduct(productDTO.id(), productDTO.title(), productDTO.price());
        return productDTO;
    }

    public List<ProductDTO> getAllProducts() {
        return productsRepository.printAllProducts();
    }

    public boolean isIdUnique(int id) {
        return productsRepository.isIdUnique(id);
    }


}
