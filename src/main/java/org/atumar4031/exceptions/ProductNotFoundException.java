package org.atumar4031.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String error) {
        super(error);
    }
}
