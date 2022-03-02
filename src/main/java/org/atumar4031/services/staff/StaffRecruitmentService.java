package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.exceptions.MinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.model.Applicant;

import java.util.List;

public interface StaffRecruitmentService {
    //List<Cashier>
    void hireCashier(List<Applicant> applicant, Store store, int limit) throws StaffAlreadyExistException, MinimalRequarimentException;
    void fireCashier(String applicant, Store store) throws CashierNotFoundException;
}
