package com.example.postgres.springpostgresdocker.controller;

import com.example.postgres.springpostgresdocker.dto.ProductDto;
import com.example.postgres.springpostgresdocker.exception.ResourceNotFoundException;
import com.example.postgres.springpostgresdocker.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Set<ProductDto>> getProducts() {
        LOGGER.info("Get Products called.");
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto) {
        LOGGER.info("Create Product: " + productDto);
        productService.create(productDto);
        return new ResponseEntity<>("Product " + productDto + " created.", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@Valid @RequestBody ProductDto productDto) throws ResourceNotFoundException {
        LOGGER.info("Update product: " + productDto);
        productService.update(productDto);
        return new ResponseEntity<>("Product " + productDto + " updated.", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@RequestBody String name) throws ResourceNotFoundException {
        LOGGER.info("Delete product with name: " + name);
        productService.delete(name);
        return new ResponseEntity<>("Product " + name + " deleted.", HttpStatus.OK);
    }

}
