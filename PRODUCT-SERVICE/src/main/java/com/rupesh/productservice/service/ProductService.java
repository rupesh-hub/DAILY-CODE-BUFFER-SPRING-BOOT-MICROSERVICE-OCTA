package com.rupesh.productservice.service;

import com.rupesh.productservice.entity.Product;
import com.rupesh.productservice.exception.NotFoundException;
import com.rupesh.productservice.model.ProductRequest;
import com.rupesh.productservice.model.ProductResponse;
import com.rupesh.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public long addProduct(final ProductRequest request) {
        log.info("adding product ..... ");

        return productRepository
                .save(Product
                        .builder()
                        .name(request.getName())
                        .quantity(request.getQuantity())
                        .price(request.getPrice())
                        .build())
                .getId();
    }

    @Override
    public ProductResponse getProductById(final Long id) {
        return productRepository.findById(id)
                .map(product ->
                        ProductResponse
                                .builder()
                                .id(id)
                                .name(product.getName())
                                .price(product.getPrice())
                                .quantity(product.getQuantity())
                                .build())
                .orElseThrow(() -> new NotFoundException("no product found with id " + id, "PRODUCT_NOT_FOUND"));
    }

    @Override
    public Page<ProductResponse> findAllProducts(final PageRequest pageRequest) {
        return Optional
                .ofNullable(productRepository.findAll(pageRequest)
                        .map(product ->
                                ProductResponse
                                        .builder()
                                        .id(product.getId())
                                        .name(product.getName())
                                        .price(product.getPrice())
                                        .quantity(product.getQuantity())
                                        .build()))
                .orElse(null);
    }

    @Override
    public void reduceQuantity(Long productId, int quantity) {
        log.info("Reducing quantity {} for product {}", quantity, productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(()->new NotFoundException("product with given id not found", "PRODUCT_NOT_FOUND"));

        if(product.getQuantity() < quantity){
            throw new RuntimeException("Product does not have sufficient quantity.");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product quantity reduced to {}", product.getQuantity());
    }

}
