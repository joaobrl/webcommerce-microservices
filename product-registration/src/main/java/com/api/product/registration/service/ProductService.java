package com.api.product.registration.service;

import com.api.product.registration.dto.ProductRequestDto;
import com.api.product.registration.model.Product;
import com.api.product.registration.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    public Product registerProduct(ProductRequestDto productRequest) {
        if (productRequest.getStock() == null) {
            productRequest.setStock(0);
        }
        var product = new Product(productRequest);
        return repository.save(product);
    }

    public List<Product> listProducts() {
        return repository.findByEnabledTrue();
    }

    public Product getProductById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, ProductRequestDto productRequest) {
        if (productRequest == null) {
            log.error("Attempt to update user with insufficient data.");
            throw new IllegalArgumentException("Update data cannot be null.");
        }

    var product = getProductById(id);

    ProductRequestDto productUpdate = new ProductRequestDto(
            productRequest.getName(),
            productRequest.getDescription(),
            productRequest.getPrice(),
            productRequest.getStock()
    );

    product.Update(productUpdate);
    log.info("Product with ID: {} updated.", id);

    return repository.save(product);
    }

    public Product deleteProduct(Long id) {
        var product = getProductById(id);
        product.setEnabled(false);
        return repository.save(product);
    }
}
