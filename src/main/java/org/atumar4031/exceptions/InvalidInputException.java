package org.atumar4031.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String error){
        super(error);
    }
}
