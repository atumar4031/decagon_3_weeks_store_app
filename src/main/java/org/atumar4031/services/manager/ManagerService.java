package org.atumar4031.services.manager;

import org.atumar4031.Store;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.exceptions.MinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.model.Applicant;

import java.util.List;

public interface ManagerService {
    //List<Cashier>
    void hireCashier(List<Applicant> applicant, Store store, int limit) throws StaffAlreadyExistException, MinimalRequarimentException;
    void fireCashier(String applicant, Store store) throws CashierNotFoundException;
}
