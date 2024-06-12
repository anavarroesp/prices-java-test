package com.inditex.prices.persistence;

import com.inditex.prices.domain.entity.Brand;
import com.inditex.prices.domain.entity.Price;
import com.inditex.prices.domain.entity.Product;
import com.inditex.prices.persistence.entity.BrandEntity;
import com.inditex.prices.persistence.entity.PriceEntity;
import com.inditex.prices.persistence.entity.ProductEntity;
import com.inditex.prices.persistence.mapper.PriceEntityMapper;
import com.inditex.prices.persistence.repository.PriceEntityRepository;
import com.inditex.prices.persistence.specification.PriceEntitySpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PricePersistenceAdapterTest {

    @Mock
    private PriceEntityRepository priceEntityRepository;
    
    @Mock
    private PriceEntitySpecification priceEntitySpecification;
    
    @Mock
    private PriceEntityMapper priceEntityMapper;
    
    @InjectMocks
    private PricePersistenceAdapter pricePersistenceAdapter;
    
    @Test
    void findApplicablePricesWhenExistMustReturnPrices() {
        //Arrange
        final var brandId = UUID.randomUUID();
        final var productId = UUID.randomUUID();
        final var date = LocalDateTime.now();
        Specification<PriceEntity> spec = Specification.where(this.priceEntitySpecification.hasBrand(brandId))
                .and(this.priceEntitySpecification.hasProduct(productId))
                .and(this.priceEntitySpecification.greaterThanOrEqualToStartDate(date))
                .and(this.priceEntitySpecification.lessThanOrEqualToEndDate(date));
        List<PriceEntity> priceEntities = this.generatePriceEntities();
        List<Price> prices = this.generatePrices();
        
        when(this.priceEntitySpecification.hasBrand(brandId)).thenReturn(any());
        when(this.priceEntitySpecification.hasProduct(productId)).thenReturn(any());
        when(this.priceEntitySpecification.greaterThanOrEqualToStartDate(date)).thenReturn(any());
        when(this.priceEntitySpecification.lessThanOrEqualToEndDate(date)).thenReturn(any());
        when(this.priceEntityRepository.findAll(spec)).thenReturn(priceEntities);
        when(this.priceEntityMapper.toDomain(priceEntities)).thenReturn(prices);
        
        //Act
        final var result = this.pricePersistenceAdapter.findApplicablePrice(brandId, productId, date);
        
        //Assert
        assertNotNull(result);
        assertEquals(prices.size(), result.size());
        assertEquals(prices.get(0).price(), result.get(0).price());
    }
    
    @Test
    void findApplicablePriceWhenNotExistMustReturnEmptyList() {
        //Arrange
        final var brandId = UUID.randomUUID();
        final var productId = UUID.randomUUID();
        final var date = LocalDateTime.now();
        Specification<PriceEntity> spec = Specification.where(this.priceEntitySpecification.hasBrand(brandId))
                .and(this.priceEntitySpecification.hasProduct(productId))
                .and(this.priceEntitySpecification.greaterThanOrEqualToStartDate(date))
                .and(this.priceEntitySpecification.lessThanOrEqualToEndDate(date));
        
        when(this.priceEntitySpecification.hasBrand(brandId)).thenReturn(any());
        when(this.priceEntitySpecification.hasProduct(productId)).thenReturn(any());
        when(this.priceEntitySpecification.greaterThanOrEqualToStartDate(date)).thenReturn(any());
        when(this.priceEntitySpecification.lessThanOrEqualToEndDate(date)).thenReturn(any());
        when(this.priceEntityRepository.findAll(spec)).thenReturn(List.of());
        
        //Act
        final var result = this.pricePersistenceAdapter.findApplicablePrice(brandId, productId, date);
        
        //Assert
        assertNotNull(result);
        assertEquals(0, result.size());
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
        
        final var price3 = Price.builder()
                .product(product)
                .brand(brand)
                .priceCode(3)
                .startDate(LocalDateTime.parse("2020-06-15T00:00:00"))
                .endDate(LocalDateTime.parse("2020-06-15T11:00:00"))
                .price(BigDecimal.valueOf(30.50))
                .priority(1)
                .build();
        
        final var price4 = Price.builder()
                .product(product)
                .brand(brand)
                .priceCode(4)
                .startDate(LocalDateTime.parse("2020-06-15T16:00:00"))
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .price(BigDecimal.valueOf(38.95))
                .priority(1)
                .build();
        
        return List.of(price1, price2, price3, price4);
    }
    
    private List<PriceEntity> generatePriceEntities() {
        final var brand = new BrandEntity();
        brand.setId(UUID.fromString("272595b8-0a72-4782-83db-5d66bd293120"));
        brand.setName("Zara");
        final var product = new ProductEntity();
        product.setId(UUID.fromString("9e059d8f-e5b9-4f69-9238-4688e1bed548"));
        
        
        final var price1 = new PriceEntity();
        price1.setId(UUID.fromString("8b1fd405-c2ae-42e5-8b88-355a62acabf4"));
        price1.setBrand(brand);
        price1.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        price1.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price1.setPriceCode(1);
        price1.setProduct(product);
        price1.setPriority(0);
        price1.setPrice(BigDecimal.valueOf(35.50));
        price1.setCurrency("EUR");
        
        final var price2 = new PriceEntity();
        price2.setId(UUID.fromString("9828db55-2903-44b1-b802-bd68edda18df"));
        price2.setBrand(brand);
        price2.setStartDate(LocalDateTime.parse("2020-06-14T15:00:00"));
        price2.setEndDate(LocalDateTime.parse("2020-06-14T18:30:00"));
        price2.setPriceCode(2);
        price2.setProduct(product);
        price2.setPriority(1);
        price2.setPrice(BigDecimal.valueOf(25.45));
        price2.setCurrency("EUR");
        
        final var price3 = new PriceEntity();
        price3.setId(UUID.fromString("915496d3-1d6e-4b36-a0a3-2b6977a1a076"));
        price3.setBrand(brand);
        price3.setStartDate(LocalDateTime.parse("2020-06-15T00:00:00"));
        price3.setEndDate(LocalDateTime.parse("2020-06-15T11:00:00"));
        price3.setPriceCode(3);
        price3.setProduct(product);
        price3.setPriority(1);
        price3.setPrice(BigDecimal.valueOf(30.50));
        price3.setCurrency("EUR");
        
        final var price4 = new PriceEntity();
        price4.setId(UUID.fromString("7aeef94c-7160-4113-bccd-280bbe8dbcb8"));
        price4.setBrand(brand);
        price4.setStartDate(LocalDateTime.parse("2020-06-15T16:00:00"));
        price4.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        price4.setPriceCode(4);
        price4.setProduct(product);
        price4.setPriority(1);
        price4.setPrice(BigDecimal.valueOf(38.95));
        price4.setCurrency("EUR");
        
        return List.of(price1, price2, price3, price4);
    }
    
    
}
