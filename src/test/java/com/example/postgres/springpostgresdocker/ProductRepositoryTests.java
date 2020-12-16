package com.example.postgres.springpostgresdocker;

import com.example.postgres.springpostgresdocker.model.Product;
import com.example.postgres.springpostgresdocker.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@SpringBootTest
@Transactional
@Slf4j
public class ProductRepositoryTests extends AbstractJPATest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void testSaveAndDelete() {

        Product product = new Product();
        product.setCreated(OffsetDateTime.now());
        product.setName("Test 123");
        product.setPrice(new BigDecimal("111.1"));
        productRepository.save(product);

        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<");
        log.info("{}", productRepository);
        log.info("count = {}", productRepository.count());
        long deletedCount = productRepository.deleteByName("Test 123");
        log.info("deleted count = {}", deletedCount);
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<");

        Assertions.assertEquals(1,deletedCount);

    }

}
