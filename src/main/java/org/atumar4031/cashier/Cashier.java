package org.atumar4031.cashier;

import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;
import org.atumar4031.utilities.Staff;

import java.time.LocalDateTime;

public class Cashier extends Staff {
    LocalDateTime local = LocalDateTime.now();
    public Cashier(){

    }

    public Cashier(String staffId, String name, String email, String phone, String address, Role role, Gender gender, int age) {
        super(staffId, name, email, phone, address, role, gender, age);
    }

    @Override
    public String toString() {
        return super.toString() +", "+local;
    }
}
