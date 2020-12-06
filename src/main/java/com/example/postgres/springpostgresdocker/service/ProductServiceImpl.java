package com.example.postgres.springpostgresdocker.service;

import com.example.postgres.springpostgresdocker.dto.ProductDto;
import com.example.postgres.springpostgresdocker.model.Product;
import com.example.postgres.springpostgresdocker.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Bogdan Benga <bogdanbenga@gmail.com></>
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    ModelMapper modelMapper = new ModelMapper();


    @Override
    public void create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Override
    public Set<ProductDto> getProducts() {

        Set<Product> products = new HashSet<>();
                products.addAll(productRepository.findAll());
        Type listType = new TypeToken<Set<ProductDto>>() {
        }.getType();

        return modelMapper.map(products, listType);
    }

    @Override
    public ProductDto update(ProductDto productDto) {

        Optional<Product> product = productRepository.findByName(productDto.getName());
        if (product.isPresent()){
            Product productToUpdate = product.get();
            productToUpdate.setCreated(productDto.getCreated());
            productToUpdate.setName(productDto.getName());
            productToUpdate.setPrice(productDto.getPrice());
            productRepository.save(productToUpdate);
        }
        return productDto;
    }

    @Override
    public boolean delete(String name) {
        Optional<Product> product = productRepository.findByName(name);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }
}
