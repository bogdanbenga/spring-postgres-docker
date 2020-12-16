package com.example.postgres.springpostgresdocker.service;


import com.example.postgres.springpostgresdocker.dto.ProductDto;

import javax.management.relation.RelationServiceNotRegisteredException;
import java.util.Set;


/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
public interface ProductService {

    void create(ProductDto productDto);

    Set<ProductDto> getProducts();

    ProductDto update(ProductDto productDto) throws RelationServiceNotRegisteredException;

    void delete(String name) throws RelationServiceNotRegisteredException;
}
