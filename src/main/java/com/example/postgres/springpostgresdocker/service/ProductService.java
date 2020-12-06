package com.example.postgres.springpostgresdocker.service;


import com.example.postgres.springpostgresdocker.dto.ProductDto;

import java.util.Set;


/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public interface ProductService {

    void create(ProductDto productDto);

    Set<ProductDto> getProducts();

    ProductDto update(ProductDto productDto);

    boolean delete(String name);
}
