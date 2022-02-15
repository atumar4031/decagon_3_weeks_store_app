package org.atumar4031.exceptions;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String error) {
        super(error);
    }
}
