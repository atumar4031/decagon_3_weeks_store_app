package org.atumar4031.exceptions;

public class productNotFoundInTheStore extends RuntimeException {
    public productNotFoundInTheStore(String error) {
        super(error);
    }
}
