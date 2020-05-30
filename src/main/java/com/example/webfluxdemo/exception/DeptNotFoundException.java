package com.example.webfluxdemo.exception;

/**
 * Created by rajeevkumarsingh on 22/10/17.
 */
public class DeptNotFoundException extends RuntimeException {

    public DeptNotFoundException(String deptId) {
        super("Dept not found with id " + deptId);
    }
}
