package org.atumar4031.exceptions;

public class EmptyInputException extends RuntimeException{
    public EmptyInputException(String error){
        super(error);
    }
}
