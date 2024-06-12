package com.inditex.prices.domain.exception;

import lombok.Getter;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

@Getter
public class DomainException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    protected Map<String, Object> parameters = new HashMap<>();

    public DomainException() {
    }

    public DomainException(final String message) {
        super(message);
    }

    public DomainException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DomainException(final Throwable cause) {
        super(cause);
    }

    public DomainException(final String message, final Throwable cause, final boolean enableSuppression,
                                 final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DomainException withParameter(final String key, final Object value) {
        this.parameters.put(key, value);
        return this;
    }

    public DomainException withParameters(final Map<String, Object> parameters) {
        this.parameters = parameters;
        return this;
    }
}
