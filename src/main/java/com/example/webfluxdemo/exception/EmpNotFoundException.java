package com.example.webfluxdemo.exception;

/**
 * Created by rajeevkumarsingh on 22/10/17.
 */
public class EmpNotFoundException extends RuntimeException {

    public EmpNotFoundException(String empId) {
        super("Employee not found with id " + empId);
    }
}
