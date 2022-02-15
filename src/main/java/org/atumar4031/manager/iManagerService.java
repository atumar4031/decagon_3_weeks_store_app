package org.atumar4031.manager;

import org.atumar4031.PhoneAndAccessoriesStore;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.utilities.Applicant;

import java.util.List;

public interface iManagerService {
    //List<Cashier>
    void hireCashie(List<Applicant> applicant, PhoneAndAccessoriesStore store, int limit);
    void fireCashier(String applicant) throws CashierNotFoundException;
}
