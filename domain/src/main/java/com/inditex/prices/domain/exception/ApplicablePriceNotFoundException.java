package com.inditex.prices.domain.exception;

import java.io.Serial;

public class ApplicablePriceNotFoundException extends RuntimeException {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    public ApplicablePriceNotFoundException() {
    }
    
    public ApplicablePriceNotFoundException(final String message) {
        super(message);
    }
    
    public ApplicablePriceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public ApplicablePriceNotFoundException(final Throwable cause) {
        super(cause);
    }
    
    public ApplicablePriceNotFoundException(final String message, final Throwable cause, final boolean enableSuppression,
        final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
