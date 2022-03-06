package org.atumar4031.model;
import org.atumar4031.enums.Gender;
public class Applicant extends User{

    private  String qualification;
    private  int age;
    private  int workingExperience;
    private  Gender gender;
    public static int applicantSerialId = 0;

    public Applicant(){}
    public Applicant(String name, String email,
                     String phone, String address,
                     String qualification, int age, Gender gender, int workingExperience) {
        super(name, email, phone,gender, address);
        this.qualification = qualification;
        this.age = age;
        this.gender = gender;
        this.workingExperience = workingExperience;
        applicantSerialId += 1;
    }
    public Gender getGender() {
        return gender;
    }
    public String getQualification() {
        return qualification;
    }
    public int getAge() {
        return age;
    }
    public int getWorkingExperience() {
        return workingExperience;
    }

}
