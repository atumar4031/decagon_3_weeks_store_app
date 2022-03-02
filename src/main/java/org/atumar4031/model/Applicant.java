package org.atumar4031.model;
import org.atumar4031.constants.Gender;
public class Applicant extends User{

    private final String qualification;
    private final int age;
    private final int workingExperience;
    private final Gender gender;
    public static int applicantSerialId = 0;

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
