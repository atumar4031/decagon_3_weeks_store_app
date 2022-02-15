package org.atumar4031.utilities;

import org.atumar4031.ElectronicsStore;
import org.atumar4031.cashier.Cashier;
import org.atumar4031.constants.Gender;
import org.atumar4031.constants.Role;

import java.util.ArrayList;
import java.util.List;

public class Staff  extends User{
    private String StaffId;
    private Role role;
    private Gender gender;
    private int age;
    private List<Cashier> cashiers = new ArrayList<>();

    public Staff() {
    }
    public Staff(String staffId, String name, String email, String phone, String address,
                 Role role, Gender gender, int age) {
        super(name, email, phone, address);
        this.StaffId = staffId;
        this.role = role;
        this.gender = gender;
        this.age= age;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public List<Cashier> getCashiers() {
        return cashiers;
    }

    public void setCashiers(Cashier cashier) {
        this.cashiers.add(cashier);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "StaffId= '" + StaffId + '\'' +
                ", role= " + role +
                ", gender= " + gender +
                ", age= " + age +" "+
                super.toString() +
                '}';
    }
}
