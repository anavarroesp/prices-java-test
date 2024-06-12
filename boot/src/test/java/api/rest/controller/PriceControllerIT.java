package api.rest.controller;

import com.inditex.prices.PricesApplication;
import com.inditex.prices.api.rest.model.PriceDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = PricesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerIT {

    private static final String BASE_PATH = "/api/v1/prices";
    
    @Autowired 
    private TestRestTemplate restTemplate;
    
    @Test
    void findApplicablePriceMustReturn200Ok() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-14T00:00:00";

        final var endpoint = UriComponentsBuilder
                .fromPath("/brands/{brandId}/products/{productId}/applicable-price")
                .queryParam("applicationDate", applicationDate)
                .buildAndExpand(brandId, productId)
                .toUriString();
        
        // Act
        final var response = this.restTemplate
                .exchange(BASE_PATH.concat(endpoint), HttpMethod.GET, requestEntity, PriceDetail.class);
        
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private HttpHeaders getHttpHeaders() {
        final var requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.ACCEPT, "application/json");
        return requestHeaders;
    }
}
