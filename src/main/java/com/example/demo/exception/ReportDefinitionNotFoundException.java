package com.example.demo.exception;

public class ReportDefinitionNotFoundException extends RuntimeException {
    public ReportDefinitionNotFoundException(String message) {
        super(message);
    }
}
