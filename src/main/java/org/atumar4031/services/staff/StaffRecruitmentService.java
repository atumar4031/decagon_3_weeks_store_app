package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.ApplicantMinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Staff;

public interface StaffRecruitmentService {
    //List<Cashier>
    void hireCashier(Staff staff,Store store,Applicant applicant, int limit) throws StaffAlreadyExistException, ApplicantMinimalRequarimentException, StaffNotAuthorizedException;
    void fireCashier(Staff staff, String applicant, Store store) throws StaffNotAuthorizedException;
}
