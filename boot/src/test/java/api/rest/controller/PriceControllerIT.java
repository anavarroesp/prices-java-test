package api.rest.controller;

import com.inditex.prices.PricesApplication;
import com.inditex.prices.api.rest.model.PriceDetail;
import com.inditex.prices.api.rest.model.ProblemDetail;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = PricesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerIT {

    private static final String BASE_PATH = "/api/v1/prices";
    
    @Autowired 
    private TestRestTemplate restTemplate;
    
    
    // Test 1: petición a las 10:00 del día 14 del producto 9e059d8f-e5b9-4f69-9238-4688e1bed548   para la brand 272595b8-0a72-4782-83db-5d66bd293120 (ZARA)
    @Test
    void findApplicablePriceWhenFindRecordsMustReturn200OkCase1() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-14T10:00:00";

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
        assertNotNull(response.getBody());
        assertEquals(35.5, response.getBody().getPrice());
        assertEquals(1, response.getBody().getPriceCode());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
    }
    
    //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void findApplicablePriceWhenFindRecordsMustReturn200OkCase2() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-14T16:00:00";

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
        assertNotNull(response.getBody());
        assertEquals(25.45, response.getBody().getPrice());
        assertEquals(2, response.getBody().getPriceCode());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
    }
    
    //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
    @Test
    void findApplicablePriceWhenFindRecordsMustReturn200OkCase3() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-14T21:00:00";

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
        assertNotNull(response.getBody());
        assertEquals(35.50, response.getBody().getPrice());
        assertEquals(1, response.getBody().getPriceCode());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
    }    
    
    //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
    @Test
    void findApplicablePriceWhenFindRecordsMustReturn200OkCase4() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-15T10:00:00";

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
        assertNotNull(response.getBody());
        assertEquals(30.50, response.getBody().getPrice());
        assertEquals(3, response.getBody().getPriceCode());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
    }

    //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
    @Test
    void findApplicablePriceWhenFindRecordsMustReturn200OkCase5() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-16T21:00:00";

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
        assertNotNull(response.getBody());
        assertEquals(38.95, response.getBody().getPrice());
        assertEquals(4, response.getBody().getPriceCode());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
    }
    
    @Test
    void findApplicablePriceWhenNotRecordsFoundMustReturn404NotFound() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293123");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-14T00:00:00";

        final var endpoint = UriComponentsBuilder
                .fromPath("/brands/{brandId}/products/{productId}/applicable-price")
                .queryParam("applicationDate", applicationDate)
                .buildAndExpand(brandId, productId)
                .toUriString();
        
        // Act
        final var response = this.restTemplate
                .exchange(BASE_PATH.concat(endpoint), HttpMethod.GET, requestEntity, ProblemDetail.class);
        
        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().getStatus());
        assertEquals("Applicable Price Not Found", response.getBody().getTitle());
    }
    
    @Test
    void findApplicablePriceWhenInvalidParametersMustReturn400BadRequest() {
        // Arrange
        final var requestHeaders = this.getHttpHeaders();
        final var requestEntity = new HttpEntity<>(requestHeaders);
        final UUID brandId = UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120");
        final UUID productId = UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548");
        final var applicationDate = "2020-06-14T00:00:00";

        final var endpoint = UriComponentsBuilder
                .fromPath("/brands/{brandId}/products/{productId}/applicable-price")
                .queryParam("invalidParameter", applicationDate)
                .buildAndExpand(brandId, productId)
                .toUriString();
        
        // Act
        final var response = this.restTemplate
                .exchange(BASE_PATH.concat(endpoint), HttpMethod.GET, requestEntity, ProblemDetail.class);
        
        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Bad Request", response.getBody().getTitle());
    }

    private HttpHeaders getHttpHeaders() {
        final var requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.ACCEPT, "application/json");
        return requestHeaders;
    }
}
