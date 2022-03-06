package org.atumar4031.StaffService;

import org.atumar4031.Store;
import org.atumar4031.enums.Gender;
import org.atumar4031.enums.Role;
import org.atumar4031.exceptions.ApplicantMinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Staff;
import org.atumar4031.services.staff.ManagerStaff;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerServicesTest {
    // TODO refactor and reduce exception;
    private ManagerStaff managerServices;
    private Staff usmanManager;
    private List<Applicant> applicantList;
    private Applicant Abubakar;
    private Applicant samira;
    private Applicant asmau;
    Store store;

    @Before
    public void setUp() throws Exception {

        usmanManager = new Staff("10101","usman","usman@gmail.com","0000999888","", Role.MANAGER, Gender.MALE,21);
//        umarManager = new Staff("10102","umar","umar@gmail.com","0000999888","", Role.MANAGER, Gender.MALE,21);
        store = new Store(101, "phoneStore");
        managerServices = new ManagerStaff();
//        applicantList = new ArrayList<>();
        Abubakar = new Applicant("Umar","umar@gmail.com",
                "08166666666","Yar  yara",
                "MSc",30, Gender.MALE, 2);
        samira = new Applicant("Samira","samira@gmail.com",
                "08166616752","T wada",
                "MSC",30,Gender.FEMALE, 2);
        asmau = new Applicant("Asmau","asmau@gmail.com",
                "08166616752","T wada",
                "Bsc",30,Gender.FEMALE, 2);
//        store.getApplicants().add(Abubakar);
        store.getApplicants().add(samira);
        store.getApplicants().add(asmau);
        store.getStaffList().add(usmanManager);
    }

    @Test
    public void compareSizeOfListBeforeAndAfterHire() throws StaffAlreadyExistException, ApplicantMinimalRequarimentException {
        int sizeBeforeHire = store.getStaffList().size();
        managerServices.hireCashier(usmanManager,store, samira,2);
        int sizeAfterHire = store.getStaffList().size();
        assertTrue(sizeAfterHire > sizeBeforeHire);
    }

    @Test
    public void shouldEnsureStaffIsAuthorisedTodoThisOperation() throws  StaffAlreadyExistException, ApplicantMinimalRequarimentException, StaffNotAuthorizedException {

        assertThrows(StaffNotAuthorizedException.class,
                ()->managerServices.fireCashier(usmanManager,"umar@gmail.com", store));
    }

    @Test
    public void shouldCheckForCashierNotFoundExceptionWhileFiring() throws StaffNotAuthorizedException, StaffAlreadyExistException, ApplicantMinimalRequarimentException {
        managerServices.hireCashier(usmanManager,store,Abubakar, 2);
        assertThrows(StaffNotAuthorizedException.class,
                () -> managerServices.fireCashier(usmanManager,"asmau@gmail.com",store));
    }

    @Test
    public void checkForMinimalRequirement() throws StaffAlreadyExistException, ApplicantMinimalRequarimentException {
        assertThrows(ApplicantMinimalRequarimentException.class,
                () -> managerServices.hireCashier(usmanManager,store,asmau, 2));
    }

}