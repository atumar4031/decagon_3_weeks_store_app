package org.atumar4031.model;

import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;
import org.atumar4031.model.Applicant;
import org.atumar4031.model.Staff;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Staff {

    public Manager() {
    }

    public Manager(String staffId, String name, String email, String phone, String address, Role role, Gender gender, int age) {
        super(staffId, name, email, phone, address, role, gender, age);
    }

}
