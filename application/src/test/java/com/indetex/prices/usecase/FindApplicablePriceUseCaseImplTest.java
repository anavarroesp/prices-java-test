package com.indetex.prices.usecase;

import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.entity.Product;
import com.inditex.prices.domain.exception.ApplicablePriceNotFoundException;
import com.inditex.prices.domain.ports.persistence.PricePersistencePort;
import com.inditex.prices.usecase.FindApplicablePriceUseCaseImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FindApplicablePriceUseCaseImplTest {
    
    @Mock
    private PricePersistencePort pricePersistencePort;
    
    @InjectMocks
    private FindApplicablePriceUseCaseImpl findPriceUseCase;
    
    @Test
    void findApplicablePriceMustReturnApplicablePriceWithMaxPriority() {
        // Arrange
        List<Price> prices = generatePrices();
        when(this.pricePersistencePort.findApplicablePrice(any(), any(), any())).thenReturn(prices);
        
        // Act
        final var result = this.findPriceUseCase.findApplicablePrice(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"),
                UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"), LocalDateTime.parse("2020-06-14T10:00:00"));
        
        // Assert
        assertEquals(BigDecimal.valueOf(25.45), result.price());
    }
    
    @Test
    void findApplicablePriceMustReturnApplicableApplicablePriceNotFoundException() {
        // Arrange
        when(this.pricePersistencePort.findApplicablePrice(any(), any(), any())).thenThrow(new ApplicablePriceNotFoundException());
        
        // Act and Assert
        assertThrows(ApplicablePriceNotFoundException.class, () -> this.findPriceUseCase.findApplicablePrice(UUID.randomUUID(), UUID.randomUUID(), LocalDateTime.now()));
    }

    private List<Price> generatePrices() {
        final var brand = Brand.builder()
                .id(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"))
                .name("Zara")
                .build();
        final var product = Product.builder()
                .id(UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"))
                .build();

        final var price1 = Price.builder()
                .product(product)
                .brand(brand)
                .priceCode(1)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(35.50))
                .priority(0)
                .build();

        final var price2 = Price.builder()
                .product(product)
                .brand(brand)
                .priceCode(2)
                .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
                .endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
                .price(BigDecimal.valueOf(25.45))
                .priority(1)
                .build();
        

        return List.of(price1, price2);
    }
    
}
