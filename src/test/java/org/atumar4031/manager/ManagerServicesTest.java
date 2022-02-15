package org.atumar4031.manager;

import org.atumar4031.ElectronicsStore;
import org.atumar4031.constants.Gender;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.utilities.Applicant;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerServicesTest {

    private ManagerService managerService;
    private Manager manager;
    private List<Applicant> applicantList;
    private Applicant applicant1;
    private Applicant applicant2;
    ElectronicsStore store;
    LocalDateTime t;
    @Before
    public void setUp() throws Exception {
        t = LocalDateTime.now();
        manager = new Manager();
        store = new ElectronicsStore();
        managerService = new ManagerService();
        applicantList = new ArrayList<>();
        applicant1 = new Applicant("Umar","umar@gmail.com",
                "08166666666","Yar  yara",
                "MSc",30, Gender.MALE, 2);
        applicant2 = new Applicant("Asmau","asmau@gmail.com",
                "08166616752","T wada",
                "BSC",30,Gender.FEMALE, 2);
        applicantList.add(applicant1);
        applicantList.add(applicant2);
    }

    @Test
    public void compareSizeOfListBeforeAndAfterHire() {
        //given
        int sizeBeforeHire = managerService.getCashiers().size();
        managerService.hireCashie(applicantList,store, 2);
        int sizeAfterHire = managerService.getCashiers().size();
        //when
        assertTrue(sizeAfterHire > sizeBeforeHire);
        //then
    }

    @Test
    public void checkAvailableVacancyAfterHired(){
        //given
        int vacancies = 2;
        managerService.hireCashie(applicantList,store, vacancies);
        //when
        assertTrue(managerService.getVacancies() < vacancies);
        //then
    }

    // testing Fire method
    @Test
    public void shouldCheckIfCashierExistBeforeFired() throws CashierNotFoundException {
        //given
        managerService.hireCashie(applicantList,store, 2);
        int beforeRemove = managerService.getCashiers().size();
        managerService.fireCashier("umar@gmail.com");

    }

    @Test
    public void shouldCheckforCashierNotFoundExceptionWhileFiring() throws CashierNotFoundException {
        //given
        managerService.hireCashie(applicantList,store, 2);
//        managerService.getCashiers();
        assertThrows(CashierNotFoundException.class,
                () -> managerService.fireCashier("asmau@gmail.com"));
    }

}