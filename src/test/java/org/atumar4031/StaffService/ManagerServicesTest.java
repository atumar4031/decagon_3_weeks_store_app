package org.atumar4031.StaffService;

import org.atumar4031.Store;
import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.exceptions.MinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Staff;
import org.atumar4031.services.staff.ManagerStaff;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerServicesTest {

    private ManagerStaff managerServices;
    private Staff StaffManager;
    private List<Applicant> applicantList;
    private List<Applicant> requirementChecker;
    private Applicant Abubakar;
    private Applicant samira;
    private Applicant umar;
    Store store;

    @Before
    public void setUp() throws Exception {

        StaffManager = new Staff("10101","usman","usman@gmail.com","0000999888","", Role.MANAGER, Gender.MALE,21);
        store = new Store(101, "phoneStore");
        managerServices = new ManagerStaff();
        applicantList = new ArrayList<>();
        requirementChecker = new ArrayList<>();
        Abubakar = new Applicant("Umar","umar@gmail.com",
                "08166666666","Yar  yara",
                "MSc",30, Gender.MALE, 2);
        samira = new Applicant("Asmau","asmau@gmail.com",
                "08166616752","T wada",
                "MSC",30,Gender.FEMALE, 2);
        umar = new Applicant("Asmau","asmau@gmail.com",
                "08166616752","T wada",
                "sc",30,Gender.FEMALE, 2);
        applicantList.add(Abubakar);
        applicantList.add(samira);
        requirementChecker.add(umar);
    }

    @Test
    public void compareSizeOfListBeforeAndAfterHire() throws StaffAlreadyExistException, MinimalRequarimentException {
        //given
        int sizeBeforeHire = store.getStaffList().size();
        managerServices.hireCashier(applicantList,store, 2);
        int sizeAfterHire = store.getStaffList().size();
        //when
        assertTrue(sizeAfterHire > sizeBeforeHire);
        //then
    }

    @Test
    public void checkAvailableVacancyAfterHired() throws StaffAlreadyExistException, MinimalRequarimentException {
        //given
        int vacancies = 2;
        managerServices.hireCashier(applicantList,store, vacancies);
        //when
//        assertTrue(managerServiceImple.getAvailableSlot() < vacancies);
        //then
    }

    // testing Fire method
    @Test
    public void shouldCheckIfCashierExistBeforeFired() throws CashierNotFoundException, StaffAlreadyExistException, MinimalRequarimentException {
        //given
        managerServices.hireCashier(applicantList,store, 2);
        int beforeRemove = store.getStaffList().size();
        managerServices.fireCashier("umar@gmail.com", store);

    }

    @Test
    public void shouldCheckforCashierNotFoundExceptionWhileFiring() throws CashierNotFoundException, StaffAlreadyExistException, MinimalRequarimentException {
        //given
        managerServices.hireCashier(applicantList,store, 2);
//        managerService.getCashiers();
        assertThrows(CashierNotFoundException.class,
                () -> managerServices.fireCashier("asmau@gmail.com",store));
    }

    @Test
    public void chechForMinimalRequirement() throws CashierNotFoundException, StaffAlreadyExistException, MinimalRequarimentException {
        //given
//        managerService.getCashiers();
        assertThrows(MinimalRequarimentException.class,
                () -> managerServices.hireCashier(requirementChecker,store, 2));
    }

}