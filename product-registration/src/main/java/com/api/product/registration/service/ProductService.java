package com.api.product.registration.service;

import com.api.product.registration.dto.ProductRequestDto;
import com.api.product.registration.model.Product;
import com.api.product.registration.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public Product registerProduct(ProductRequestDto productRequest) {
        log.info("Starting product registration process for: {}", productRequest.getName());

        if (productRequest.getStock() == null) {
            log.debug("Stock not provided for product {}, defaulting to 0", productRequest.getName());
            productRequest.setStock(0);
        }

        var product = new Product(productRequest);
        var savedProduct = repository.save(product);

        log.info("Product successfully registered with ID: {}", savedProduct.getId());
        return savedProduct;
    }

    public List<Product> listProducts() {
        log.debug("Fetching all enabled products from database");
        return repository.findByEnabledTrue();
    }

    public Product getProductById(Long id) {
        log.debug("Searching for product with ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product search failed: ID {} not found", id);
                    return new RuntimeException("Product not found");
                });
    }

    @Transactional
    public Product updateProduct(Long id, ProductRequestDto productRequest) {
        if (productRequest == null) {
            log.error("Update failed: Request body is null for product ID {}", id);
            throw new IllegalArgumentException("Update data cannot be null.");
        }

        log.info("Updating product ID: {}", id);
        var product = getProductById(id);

        product.Update(productRequest);
        var updatedProduct = repository.save(product);

        log.info("Product with ID: {} successfully updated", id);
        return updatedProduct;
    }

    @Transactional
    public Product deleteProduct(Long id) {
        log.info("Soft-deleting product with ID: {}", id);
        var product = getProductById(id);

        product.setEnabled(false);
        var deletedProduct = repository.save(product);

        log.info("Product with ID: {} has been disabled", id);
        return deletedProduct;
    }
}