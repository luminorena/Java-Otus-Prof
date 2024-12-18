package ru.otus.spring.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.otus.spring.dtos.ProductDTO;
import ru.otus.spring.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {
    private static final Logger log = LoggerFactory.getLogger(ProductsController.class);
    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductDTO> getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createNewProduct(@RequestBody ProductDTO productDTO) {
        if (!productService.isIdUnique(productDTO.id())) {
            ProductDTO product = productService.createNewProduct(productDTO);
            return new ProductDTO(product.id(), product.title(), product.price());
        } else {
            log.error("Id should be unique: {}", productDTO.id());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }


}
