package org.atumar4031.exceptions;

public class ApplicantAlreadyExistException extends RuntimeException {
    public ApplicantAlreadyExistException(String error) {
        super(error);
    }
}
