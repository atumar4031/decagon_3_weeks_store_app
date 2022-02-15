package org.atumar4031.manager;

import org.atumar4031.ElectronicsStore;
import org.atumar4031.cashier.Cashier;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.utilities.Applicant;
import java.util.ArrayList;
import java.util.List;

public class ManagerService implements iManagerService {
    private Manager manager;

    private List<Cashier> cashiers = new ArrayList<>();

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    private int vacancies;

    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(Cashier cashier) {
        this.cashiers.add(cashier);
    }
    public ManagerService() {
        this.manager = new Manager();
    }
//List<Cashier>
    @Override
    public  void hireCashie(List<Applicant> applicants,ElectronicsStore store, int vacancies) {
        this.vacancies = vacancies;
        for (Applicant applicant : applicants) {
            if (this.vacancies > 0) {

                if (applicant.getQualification().equalsIgnoreCase("MSC") &&
                        applicant.getAge() <= 35 && applicant.getExperience() >= 2) {
                    this.setCashiers(new Cashier(
                            "CSHR/22/0"+Applicant.serial,applicant.getName(),
                            applicant.getEmail(), applicant.getPhone(),
                            applicant.getAddress(), Role.CASHIER,
                            applicant.getGender(), applicant.getAge()));

                    store.setCashierList(this.getCashiers());
                } else {

                    //
                }
            }
            this.setVacancies(this.vacancies - 1);
        }
//        return store.getCashiers();
    }

    @Override
    public void fireCashier(String email) throws CashierNotFoundException {
        List<Cashier> cashiers = this.getCashiers();
        for (Cashier cashier : cashiers){
            if (cashier.getEmail().equalsIgnoreCase(email)){
                cashiers.remove(cashier);
                break;
            }else {
                throw new CashierNotFoundException("Cashier did not exist");
            }
        }
    }
}
