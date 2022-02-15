package org.atumar4031.manager;

import org.atumar4031.ElectronicsStore;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.utilities.Applicant;

import java.util.List;

public interface iManagerService {
    //List<Cashier>
    void hireCashie(List<Applicant> applicant, ElectronicsStore store, int limit);
    void fireCashier(String applicant) throws CashierNotFoundException;
}
