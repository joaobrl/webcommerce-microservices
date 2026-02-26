package com.api.product.registration.controller;

import com.api.product.registration.dto.ProductRequestDto;
import com.api.product.registration.dto.ProductResponseDto;
import com.api.product.registration.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/register")
    public ResponseEntity registerProduct(@Valid @RequestBody ProductRequestDto productRequest, UriComponentsBuilder uriBuilder) {
        var product = productService.registerProduct(productRequest);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductResponseDto(product));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponseDto>> listProducts() {
            var products = productService.listProducts()
                    .stream()
                    .map(ProductResponseDto::new)
                    .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        var product = productService.getProductById(id);
        return ResponseEntity.ok(new ProductResponseDto(product));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequestDto productRequest) {
        var updatedProduct = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok(new ProductResponseDto(updatedProduct));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct (@PathVariable Long Id) {
        var product = productService.deleteProduct(Id);
        return ResponseEntity.noContent().build();
    }

}
