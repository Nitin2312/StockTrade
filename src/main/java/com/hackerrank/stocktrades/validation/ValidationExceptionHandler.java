package com.hackerrank.stocktrades.validation;

import com.hackerrank.stocktrades.exception.StockNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Exception occurred! " + ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Exception occurred! " + ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {StockNotFoundException.class})
    public ResponseEntity<Object> handleStockNotFoundExpception(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Requested resource not found " + ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
