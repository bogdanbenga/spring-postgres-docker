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
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public Set<ProductDto> getProducts() {
        LOGGER.info("Get Products called.");
        return productService.getProducts();
    }

    @RequestMapping(value = "/create-product", method = RequestMethod.POST)
    public void createProduct(@Valid @RequestBody ProductDto productDto) {
        LOGGER.info("Create Product: " + productDto);
        productService.create(productDto);
    }

    @RequestMapping(value = "/update-product", method = RequestMethod.PUT)
    public void updateProduct(@Valid @RequestBody ProductDto productDto) throws RelationServiceNotRegisteredException {
        LOGGER.info("Update product: " + productDto);
        productService.update(productDto);
    }

    @RequestMapping(value = "/delete-product", method = RequestMethod.DELETE)
    public void deleteProduct(@RequestBody String name) throws RelationServiceNotRegisteredException {
        LOGGER.info("Delete product with name: " + name);
        productService.delete(name);
    }

}
