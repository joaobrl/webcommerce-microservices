package com.api.product.registration.repository;

import com.api.product.registration.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByEnabledTrue();
}
