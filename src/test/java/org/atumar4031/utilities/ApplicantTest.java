package org.atumar4031.utilities;

import org.atumar4031.constants.Gender;
import org.atumar4031.exceptions.InvalidEmailException;
import org.atumar4031.exceptions.NullApplicantException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ApplicantTest {
    private Applicant applicant1;
    private Applicant applicant2;
    private Applicant apply;
    private LocalDateTime t = LocalDateTime.now();
    @Before
    public void setUp() throws Exception {
        apply = new Applicant();
        applicant1 = new Applicant("Umar","umar@gmail.com",
                "08166666666","Yar  yara",
                "MSc",30, Gender.MALE, 2);
        applicant2 = new Applicant("Asma'u","asmau@gmail.com",
                "08166616752","T wada",
                "BSC",30,Gender.FEMALE, 2);
    }

    @Test
    public void TestingApplicant() throws InvalidEmailException, NullApplicantException {

        //given
        int expected = 2;
        apply.apply(applicant1);
        apply.apply(applicant2);
        //when
        assertEquals(expected, apply.manager.getApplicants().size());
        //then
    }
}