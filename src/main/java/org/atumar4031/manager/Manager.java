package org.atumar4031.manager;

import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;
import org.atumar4031.utilities.Applicant;
import org.atumar4031.utilities.Staff;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Staff {
    private static List<Applicant> applicants= new ArrayList<>();
    public Manager() {
    }
    public Manager(String staffId, String name, String email, String phone, String address, Role role, Gender gender, int age) {
        super(staffId, name, email, phone, address, role, gender, age);
    }

    public List<Applicant> getApplicants() {
        return applicants;
    }

    public void setApplicants(Applicant applicant) {
        applicants.add(applicant);
    }

}
