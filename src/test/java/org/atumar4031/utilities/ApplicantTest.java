package org.atumar4031.utilities;

import org.atumar4031.Store;
import org.atumar4031.constants.Gender;
import org.atumar4031.exceptions.ApplicantAlreadyExistException;
import org.atumar4031.exceptions.InvalidEmailException;
import org.atumar4031.exceptions.NullApplicantException;
import org.atumar4031.model.Applicant;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ApplicantTest {
    private Applicant applicant1;
    private Applicant applicant2;
    private Store phoneStore;
    private LocalDateTime t = LocalDateTime.now();
    @Before
    public void setUp() throws Exception {
        phoneStore = new Store(101,"phoneStore");
//        apply = new Applicant();
        applicant1 = new Applicant("Umar","umar@gmail.com",
                "08166666666","Yar  yara",
                "MSc",30, Gender.MALE, 2);
        applicant2 = new Applicant("Asma'u","asmau@gmail.com",
                "08166616752","T wada",
                "BSC",30,Gender.FEMALE, 2);
    }

    @Test
    public void TestingApplicant() throws InvalidEmailException, NullApplicantException, ApplicantAlreadyExistException {

        //given
        int expected = 1;
        applicant1.apply(phoneStore);
        //when
        assertEquals(expected, phoneStore.getApplicants().size());
        //then
    }

    @Test
    public void checkMultipleApplications() throws InvalidEmailException, NullApplicantException, ApplicantAlreadyExistException {

        applicant1.apply(phoneStore);
        //when
        assertThrows(ApplicantAlreadyExistException.class, ()->applicant1.apply(phoneStore));;
        //then
    }
}