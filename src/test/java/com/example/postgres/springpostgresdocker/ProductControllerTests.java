package com.example.postgres.springpostgresdocker;

import com.example.postgres.springpostgresdocker.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Clock;
import java.time.OffsetDateTime;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTests extends ProductControllerTestHelper {


    @Test
    void contextLoads() {
    }

    @Test
    void testAddAndDeleteProduct() throws URISyntaxException {

        ProductDto productDto = new ProductDto();
        productDto.setPrice(new BigDecimal("22.1"));
        productDto.setName("Test-Coca-Cola");
        productDto.setCreated(OffsetDateTime.now(Clock.systemDefaultZone()));

        ResponseEntity<String> result = createProduct(productDto);

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

        deleteProduct(productDto);

    }

    @Test
    void testUpdateProduct() throws URISyntaxException {

        ProductDto productDto = new ProductDto();
        productDto.setPrice(new BigDecimal("22.1"));
        productDto.setName("Test-Coca-Cola 1");
        productDto.setCreated(OffsetDateTime.now(Clock.systemDefaultZone()));

        //Create product
        ResponseEntity<String> createResult = createProduct(productDto);
        Assertions.assertEquals(200, createResult.getStatusCodeValue());

        //Update product
        productDto.setPrice(new BigDecimal("12.12"));
        ResponseEntity<String> updateResult = updateProduct(productDto);
        Assertions.assertEquals(200, updateResult.getStatusCodeValue());

        //Delete product
        ResponseEntity<String> deleteResult = deleteProduct(productDto);
        Assertions.assertEquals(200, deleteResult.getStatusCodeValue());

    }
}
