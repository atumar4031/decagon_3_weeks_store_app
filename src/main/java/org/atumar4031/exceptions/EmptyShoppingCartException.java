package org.atumar4031.exceptions;

public class EmptyShoppingCartException extends Exception {
    public EmptyShoppingCartException(String error) {
        super(error);
    }
}
