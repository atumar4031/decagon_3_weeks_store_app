package org.atumar4031.services.applicant;

import org.atumar4031.Store;
import org.atumar4031.exceptions.ApplicantAlreadyExistException;
import org.atumar4031.exceptions.InvalidEmailException;
import org.atumar4031.model.Applicant;

public class ApplicantServiceImpl implements applicantService {
    @Override
    public void apply(Applicant applicant, Store store) throws ApplicantAlreadyExistException, InvalidEmailException {

            if (validateEmail(applicant.getEmail())) {
                if (!store.getApplicants().contains(applicant)){
                    store.getApplicants().add(applicant);
                }else {
                    throw new ApplicantAlreadyExistException("This applicant already exist");
                }
            } else
                throw new InvalidEmailException("Email not valid");

    }
    public boolean validateEmail(String email){
        return email.contains("@");
    }
}
