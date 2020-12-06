package com.example.postgres.springpostgresdocker;

import com.example.postgres.springpostgresdocker.dto.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTests {

    @LocalServerPort
    int randomServerPort;

    private String CREATE_URL;
    private String DELETE_URL;
    private String UPDATE_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    @BeforeEach
    public void initURLS(){
        CREATE_URL = "http://localhost:" + randomServerPort + "/create-product/";
        DELETE_URL = "http://localhost:" + randomServerPort + "/delete-product/";
        UPDATE_URL = "http://localhost:" + randomServerPort + "/update-product/";

    }

    @Test
    void contextLoads() {
    }

    @Test
    void testAddAndDeleteProduct() throws URISyntaxException {

        ProductDto productDto = new ProductDto();
        productDto.setPrice(new BigDecimal("22.1"));
        productDto.setName("Test-Coca-Cola");
        productDto.setCreated(new Date());

        ResponseEntity<String> result = createProduct(productDto);

        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

        deleteProduct(restTemplate, productDto);

    }

    @Test
    void testUpdateProduct() throws URISyntaxException {


        ProductDto productDto = new ProductDto();
        productDto.setPrice(new BigDecimal("22.1"));
        productDto.setName("Test-Coca-Cola");
        productDto.setCreated(new Date());

        //Create product
        ResponseEntity<String> createResult = createProduct(productDto);
        Assertions.assertEquals(200, createResult.getStatusCodeValue());

        //Update product
        productDto.setPrice(new BigDecimal("12.12"));
        ResponseEntity<ProductDto> updateResult = updateProduct(productDto);
        Assertions.assertEquals(200, updateResult.getStatusCodeValue());

        //Delete product
        ResponseEntity<Long> deleteResult = deleteProduct(restTemplate, productDto);
        Assertions.assertEquals(200, deleteResult.getStatusCodeValue());

    }

    private ResponseEntity<ProductDto> updateProduct(ProductDto productDto) {
        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto);
        return restTemplate.exchange(UPDATE_URL, HttpMethod.PUT, entity, ProductDto.class);
    }

    private ResponseEntity<String> createProduct(ProductDto productDto) throws URISyntaxException {
        URI uri = new URI(CREATE_URL);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<ProductDto> request = new HttpEntity<>(productDto, headers);
        return restTemplate.postForEntity(uri, request, String.class);
    }

    private ResponseEntity<Long> deleteProduct(RestTemplate restTemplate, ProductDto productDto) {
        HttpEntity<String> entity = new HttpEntity<>(productDto.getName());
        return restTemplate.exchange(DELETE_URL, HttpMethod.DELETE, entity, Long.class);
    }

}
