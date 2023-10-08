package com.rupesh.productservice.resource;

import com.rupesh.productservice.model.ProductRequest;
import com.rupesh.productservice.model.ProductResponse;
import com.rupesh.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {


    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Long> addProduct(@RequestBody final ProductRequest request) {
        return new ResponseEntity<>(
                productService.addProduct(request),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable final Long id) {
        return ResponseEntity
                .ok(productService
                        .getProductById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll(
            @RequestParam(name="page",defaultValue = "0", required = false) final int page,
            @RequestParam(name="size",defaultValue = "25", required = false) final int size
    ) {
        return ResponseEntity
                .ok(productService
                        .findAllProducts(PageRequest.of(page, size)));
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") final Long productId,
            @RequestParam("quantity") final int quantity
    ){
        productService.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
