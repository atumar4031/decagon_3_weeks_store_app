package org.atumar4031.exceptions;

public class EmptyShoppingCartException extends RuntimeException {
    public EmptyShoppingCartException(String error) {
        super(error);
    }
}
