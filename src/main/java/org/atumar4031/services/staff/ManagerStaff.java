package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.MinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.constants.Role;
import org.atumar4031.exceptions.CashierNotFoundException;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Staff;

import java.util.List;

public class ManagerStaff implements StaffRecruitmentService {
    public ManagerStaff() {
    }
//List<Cashier>
    @Override
    public  void hireCashier(List<Applicant> applicants,  Store store, int availableSlots) throws StaffAlreadyExistException, MinimalRequarimentException {
        for (Applicant applicant : applicants) {
            if (store.getStaffList().contains(applicant)){
                throw new StaffAlreadyExistException("Staff already exist");
            }
            if (availableSlots > 0) {

                if (applicant.getQualification().equalsIgnoreCase("MSC") &&
                        applicant.getAge() <= 35 && applicant.getWorkingExperience() >= 2) {

                    store.getStaffList().add(new Staff(
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
        List<Staff> StaffList = store.getStaffList();
        for (Staff staff : StaffList){
            if (staff.getEmail().equalsIgnoreCase(email)){
                StaffList.remove(staff);
                store.setStaffList(StaffList);
                break;
            }else {
                throw new CashierNotFoundException("Cashier did not exist");
            }
        }
    }
}
