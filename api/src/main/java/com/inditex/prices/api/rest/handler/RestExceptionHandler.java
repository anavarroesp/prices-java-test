package com.inditex.prices.api.rest.handler;


import com.inditex.prices.api.rest.model.ProblemDetail;
import com.inditex.prices.domain.exception.ApplicablePriceNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
            return createResponseEntity(
                    HttpStatus.BAD_REQUEST.value(),
                    createProblemDetail(HttpStatus.BAD_REQUEST.value(),
                            "Validation Error", 
                            ex.getMessage(), 
                            request.getDescription(false)));
    }
    
    @ExceptionHandler({ApplicablePriceNotFoundException.class})
    public ResponseEntity<Object> handleApplicablePriceNotFoundException(ApplicablePriceNotFoundException ex, WebRequest request) {
        return createResponseEntity(
                HttpStatus.NOT_FOUND.value(),
                createProblemDetail(HttpStatus.NOT_FOUND.value(),
                        "Applicable Price Not Found", 
                        "no applicable price found for the given parameters", 
                        request.getDescription(false)));
    }
    

    private ProblemDetail createProblemDetail(Integer status, String title, String detail, String instance) {
        ProblemDetail problemDetail = new ProblemDetail();
        problemDetail.setTitle(title);
        problemDetail.setStatus(status);
        problemDetail.setDetail(detail);
        problemDetail.setInstance(instance);
        return problemDetail;
    }

    private ResponseEntity<Object> createResponseEntity(Integer status, ProblemDetail errorDto) {
        return ResponseEntity.status(status)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PROBLEM_JSON_VALUE)
                .body(errorDto);
    }
    
}
