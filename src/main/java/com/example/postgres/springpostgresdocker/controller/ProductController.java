package com.example.postgres.springpostgresdocker.controller;

import com.example.postgres.springpostgresdocker.dto.ProductDto;
import com.example.postgres.springpostgresdocker.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationServiceNotRegisteredException;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@RestController
@RequestMapping("products")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @GetMapping
    public Set<ProductDto> getProducts() {
        LOGGER.info("Get Products called.");
        return productService.getProducts();
    }

    @PostMapping
    public void createProduct(@Valid @RequestBody ProductDto productDto) {
        LOGGER.info("Create Product: " + productDto);
        productService.create(productDto);
    }

    @PutMapping
    public void updateProduct(@Valid @RequestBody ProductDto productDto) throws RelationServiceNotRegisteredException {
        LOGGER.info("Update product: " + productDto);
        productService.update(productDto);
    }

    @DeleteMapping
    public void deleteProduct(@RequestBody String name) throws RelationServiceNotRegisteredException {
        LOGGER.info("Delete product with name: " + name);
        productService.delete(name);
    }

}
