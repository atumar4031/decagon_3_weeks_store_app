package org.atumar4031.services.applicant;

import org.atumar4031.Store;
import org.atumar4031.enums.Gender;
import org.atumar4031.exceptions.ApplicantAlreadyExistException;
import org.atumar4031.exceptions.InvalidInputException;
import org.atumar4031.model.Applicant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

class applicantServiceImplTest {


    private Applicant Ahmad;
    private Applicant Fatima;
    ApplicantServiceImpl applicantService;
    private Store phoneStore;

    @BeforeEach
    void setUp() throws IOException {
        applicantService = new ApplicantServiceImpl();
        phoneStore = new Store(101,"phoneStore");
//        apply = new Applicant();
        Ahmad = new Applicant("Ahmad","ahmad@gmail.com",
                "08166666666","Edo Tech Park",
                "MSc",25, Gender.MALE, 2);
        Fatima = new Applicant("Fatima","fatima@gmail.com",
                "08166616752","T wada",
                "BSC",30,Gender.FEMALE, 2);
    }

    @Test
    void apply() throws ApplicantAlreadyExistException, InvalidInputException {

        int expected = 1;
        applicantService.apply(Ahmad, phoneStore);
        assertEquals(expected, phoneStore.getApplicants().size());

    }
    @Test
    public void checkMultipleApplications() throws ApplicantAlreadyExistException, InvalidInputException {
        applicantService.apply(Fatima,phoneStore);
        assertThrows(ApplicantAlreadyExistException.class, ()->applicantService.apply(Fatima,phoneStore));
    }

    @Test
    public void validateEmailException() throws ApplicantAlreadyExistException, InvalidInputException {
        Fatima.setEmail("fatima.gmail.com");
        assertThrows(InvalidInputException.class, ()->applicantService.apply(Fatima,phoneStore));
    }
}