package org.atumar4031.utilities;

import org.atumar4031.constants.Gender;
import org.atumar4031.exceptions.InvalidEmailException;
import org.atumar4031.exceptions.NullApplicantException;
import org.atumar4031.manager.Manager;

public class Applicant extends User{

    private String qualification;
    private int age;
    private int experience;
    private Gender gender;
    public static int serial = 0;
    public Manager manager = new Manager();

    public Applicant(){

    }
    public Applicant(String name, String email,
                     String phone, String address,
                     String qualification, int age, Gender gender, int experience) {
        super(name, email, phone, address);
        this.qualification = qualification;
        this.age = age;
        this.gender = gender;
        this.experience = experience;
        serial += 1;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void apply(Applicant a) throws InvalidEmailException, NullApplicantException {
        if(a != null){
            if(validateEmail(a.getEmail())){
            manager.setApplicants(a);
        }
        else
            throw new InvalidEmailException("Email not valid");
        }else
            throw new NullApplicantException("Applicant can not be null");
    }

    public boolean validateEmail(String email){
        if (!email.isEmpty() && email.contains("@"))
            return true;
        else
            return false;
    }
}
