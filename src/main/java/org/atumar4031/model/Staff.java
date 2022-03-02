package org.atumar4031.model;

import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;

public class Staff  extends User{
    private String StaffId;
    private Role role;

    public Staff(String staffId, String name, String email, String phone, String address,
                 Role role, Gender gender, int age) {
        super(name, email, phone, gender, address);
        this.StaffId = staffId;
        this.role = role;
    }

    public String getStaffId() {
        return StaffId;
    }
    public void setStaffId(String staffId) {
        StaffId = staffId;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "StaffId= '" + StaffId + '\'' +
                ", role= " + role +

                super.toString() +
                '}';
    }
}
