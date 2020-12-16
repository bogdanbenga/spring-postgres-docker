package com.example.postgres.springpostgresdocker;

import com.example.postgres.springpostgresdocker.dto.AuthRequestDto;
import com.example.postgres.springpostgresdocker.dto.ProductDto;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ProductControllerTestHelper extends AbstractJPATest {

    @LocalServerPort
    int randomServerPort;

    private String PRODUCTS_URL;
    private String AUTH_URL;
    private String AUTH_PREFIX = "Bearer";

    private static String TOKEN = null;
    private final RestTemplate restTemplate = new RestTemplate();


    @BeforeEach
    public void init() throws JSONException, URISyntaxException {
        initUrls();

        if (TOKEN == null) {
            authenticate();
        }
    }

    void initUrls() {
        PRODUCTS_URL = "http://localhost:" + randomServerPort + "/products";
        AUTH_URL = "http://localhost:" + randomServerPort + "/authenticate";
    }

    void authenticate() throws JSONException, URISyntaxException {
        URI uri = new URI(AUTH_URL);

        AuthRequestDto authRequestDto = new AuthRequestDto();
        authRequestDto.setUserName("spring_test");
        authRequestDto.setPassword("pass");

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<AuthRequestDto> request = new HttpEntity<>(authRequestDto, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        TOKEN = result.getBody();
    }

    ResponseEntity<ProductDto> updateProduct(ProductDto productDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", AUTH_PREFIX + " " + TOKEN);

        HttpEntity<ProductDto> entity = new HttpEntity<>(productDto, headers);
        return restTemplate.exchange(PRODUCTS_URL, HttpMethod.PUT, entity, ProductDto.class);
    }

    ResponseEntity<String> createProduct(ProductDto productDto) throws URISyntaxException {
        URI uri = new URI(PRODUCTS_URL);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", AUTH_PREFIX + " " + TOKEN);

        HttpEntity<ProductDto> request = new HttpEntity<>(productDto, headers);
        return restTemplate.postForEntity(uri, request, String.class);
    }

    ResponseEntity<String> deleteProduct(ProductDto productDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", AUTH_PREFIX + " " + TOKEN);

        HttpEntity<String> entity = new HttpEntity<>(productDto.getName(), headers);
        return restTemplate.exchange(PRODUCTS_URL, HttpMethod.DELETE, entity, String.class);
    }

}
