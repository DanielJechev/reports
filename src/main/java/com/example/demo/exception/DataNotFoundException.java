package com.example.demo.exception;

public class DataNotFoundException  extends RuntimeException {
    public DataNotFoundException(String message) {
        super(message);
    }
}
