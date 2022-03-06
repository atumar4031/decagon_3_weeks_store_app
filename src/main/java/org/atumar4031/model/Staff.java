package org.atumar4031.model;

import org.atumar4031.enums.Gender;
import org.atumar4031.enums.Role;

public class Staff  extends User{
    private String StaffId;
    private Role role;
    private int age;

    public Staff(){}
    public Staff(String staffId, String name, String email, String phone, String address,
                 Role role, Gender gender, int age) {
        super(name, email, phone, gender, address);
        this.StaffId = staffId;
        this.role = role;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "StaffId= '" + StaffId + '\'' +
                ", role= " + role +
                ", age= " + age +

                super.toString() +
                '}';
    }
}
