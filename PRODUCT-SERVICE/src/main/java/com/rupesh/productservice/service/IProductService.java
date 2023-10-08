package com.rupesh.productservice.service;

import com.rupesh.productservice.model.ProductRequest;
import com.rupesh.productservice.model.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IProductService {


    long addProduct(ProductRequest request);

    ProductResponse getProductById(Long id);

    Page<ProductResponse> findAllProducts(PageRequest of);

    void reduceQuantity(Long productId, int quantity);
}
