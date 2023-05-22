package com.example.mock.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
