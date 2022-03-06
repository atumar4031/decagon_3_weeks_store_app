package org.atumar4031.services.applicant;

import org.atumar4031.Store;
import org.atumar4031.exceptions.ApplicantAlreadyExistException;
import org.atumar4031.exceptions.InvalidInputException;
import org.atumar4031.model.Applicant;

public interface applicantService {
    void apply(Applicant applicant,Store store) throws ApplicantAlreadyExistException,
            InvalidInputException;
}
