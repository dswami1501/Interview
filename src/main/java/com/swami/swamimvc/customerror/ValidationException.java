package com.swami.swamimvc.customerror;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}
