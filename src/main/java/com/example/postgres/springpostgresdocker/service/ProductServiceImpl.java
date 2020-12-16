package com.example.postgres.springpostgresdocker.service;

import com.example.postgres.springpostgresdocker.dto.ProductDto;
import com.example.postgres.springpostgresdocker.exception.ResourceNotFoundException;
import com.example.postgres.springpostgresdocker.model.Product;
import com.example.postgres.springpostgresdocker.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RelationServiceNotRegisteredException;
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

    @Autowired
    ModelMapper modelMapper;


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
    public ProductDto update(ProductDto productDto) throws ResourceNotFoundException {

        Optional<Product> product = productRepository.findByName(productDto.getName());
        if (product.isPresent()) {
            Product productToUpdate = product.get();
            productToUpdate.setCreated(productDto.getCreated());
            productToUpdate.setName(productDto.getName());
            productToUpdate.setPrice(productDto.getPrice());
            productRepository.save(productToUpdate);
        } else {
            throw new ResourceNotFoundException("Update failed! The product with name: "
                    + productDto.getName()
                    + "does not exist.");
        }
        return productDto;
    }

    @Override
    @Transactional
    public void delete(String name) throws ResourceNotFoundException {
        Long deletedItems = productRepository.deleteByName(name);
        if (deletedItems ==0){
            throw new ResourceNotFoundException("Delete failed! The product with name: "
                    + name
                    + "does not exist.");
        }
    }
}
