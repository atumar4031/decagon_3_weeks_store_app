package org.atumar4031.model;

import org.atumar4031.enums.Gender;

public abstract class User {
    private String name;
    private String email;
    private String phone;
    private String address;
    private Gender gender;

    public User(String name, String email, String phone, Gender gender, String address) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.gender = gender;
    }

    protected User() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }


    @Override
    public String toString() {
        return " User{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", gender= " + gender +
                ", address='" + address + '\'' +
                '}';
    }
}
