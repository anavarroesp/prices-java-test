package com.inditex.prices.api.rest.controller;

import com.inditex.prices.api.rest.api.PricesApi;
import com.inditex.prices.api.rest.mapper.PriceMapper;
import com.inditex.prices.api.rest.model.PriceDetail;
import com.inditex.prices.domain.usecase.FindPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {
    
    private final FindPriceUseCase findPriceUseCase;
    private final PriceMapper priceMapper;
    
    @Override
    public ResponseEntity<PriceDetail> getPrice(final UUID brandId, final UUID productId, final LocalDateTime applicationDate){
        return ResponseEntity.ok().body(this.priceMapper
                .toPriceDetail(this.findPriceUseCase.findPrice(brandId, productId, applicationDate)));
    }
    
}
