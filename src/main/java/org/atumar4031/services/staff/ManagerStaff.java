package org.atumar4031.services.staff;

import org.atumar4031.Store;
import org.atumar4031.exceptions.ApplicantMinimalRequarimentException;
import org.atumar4031.exceptions.StaffAlreadyExistException;
import org.atumar4031.enums.Role;
import org.atumar4031.exceptions.StaffNotAuthorizedException;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Staff;

import java.util.List;

public class ManagerStaff implements StaffRecruitmentService {
    //TODO : test this class operations in main Class
    public ManagerStaff() {
    }
    @Override
    public  void hireCashier(Staff staff, Store store, Applicant applicant, int availableSlots) throws StaffAlreadyExistException, ApplicantMinimalRequarimentException {
            if (availableSlots <= 0) {
                System.out.println("No available slots for now");
            }
            if (!store.getApplicants().contains(applicant)){
                System.out.println("Applicant is not int the applicant list");
                return;
            }
            if (applicant.getQualification().equalsIgnoreCase("MSC") &&
                    applicant.getAge() <= 35 && applicant.getWorkingExperience() >= 1) {
                store.getStaffList().add(new Staff(
                        "CSHR/22/0"+Applicant.applicantSerialId,applicant.getName(),
                        applicant.getEmail(), applicant.getPhone(),
                        applicant.getAddress(), Role.CASHIER,
                        applicant.getGender(), applicant.getAge()));
                store.getApplicants().remove(applicant);
                availableSlots -= 1;
            }else {
                throw new ApplicantMinimalRequarimentException("Sorry! you do not meet minimal requirement");
            }
    }

    @Override
    public void fireCashier(Staff staff, String email, Store store) throws StaffNotAuthorizedException {
       boolean findeStaff = false;
        if(!store.getStaffList().contains(staff) || !staff.getRole().equals(Role.MANAGER)){
            throw new StaffNotAuthorizedException("You are not authorised for this operation");
        }
        List<Staff> StaffList = store.getStaffList();
        Staff staffToremove = new Staff();
        for (Staff staffInList : StaffList){
            if (staffInList.getEmail().equalsIgnoreCase(email)){
                findeStaff = true;
                staffToremove = staffInList;
                break;
            }
        }
        if (findeStaff){
            StaffList.remove(staffToremove);
            store.setStaffList(StaffList);
        }else {
            throw new StaffNotAuthorizedException("Cashier did not exist");
        }
    }
}
