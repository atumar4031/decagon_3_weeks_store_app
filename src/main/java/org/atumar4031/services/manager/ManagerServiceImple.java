package org.atumar4031.services.manager;

import org.atumar4031.Store;
import org.atumar4031.exceptions.MinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.model.Cashier;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.model.Applicant;

import java.util.List;

public class ManagerServiceImple implements ManagerService {
    public ManagerServiceImple() {
    }
//List<Cashier>
    @Override
    public  void hireCashier(List<Applicant> applicants,  Store store, int availableSlots) throws StaffAlreadyExistException, MinimalRequarimentException {
        for (Applicant applicant : applicants) {
            if (store.getCashierList().contains(applicant)){
                throw new StaffAlreadyExistException("Staff already exist");
            }
            if (availableSlots > 0) {

                if (applicant.getQualification().equalsIgnoreCase("MSC") &&
                        applicant.getAge() <= 35 && applicant.getWorkingExperience() >= 2) {

                    store.getCashierList().add(new Cashier(
                            "CSHR/22/0"+Applicant.applicantSerialId,applicant.getName(),
                            applicant.getEmail(), applicant.getPhone(),
                            applicant.getAddress(), Role.CASHIER,
                            applicant.getGender(), applicant.getAge()));
                    store.getApplicants().remove(applicant);
                    availableSlots -= 1;
                } else {
                    throw new MinimalRequarimentException("Sorry you did not meet the minimal requament");
                }
            }else {
                System.out.println("No available slots for now");
            }

        }
    }

    @Override
    public void fireCashier(String email, Store store) throws CashierNotFoundException {
        List<Cashier> cashiers = store.getCashierList();
        for (Cashier cashier : cashiers){
            if (cashier.getEmail().equalsIgnoreCase(email)){
                cashiers.remove(cashier);
                store.setCashierList(cashiers);
                break;
            }else {
                throw new CashierNotFoundException("Cashier did not exist");
            }
        }
    }
}
