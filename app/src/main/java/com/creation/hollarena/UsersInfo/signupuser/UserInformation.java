package com.creation.hollarena.UsersInfo.signupuser;

/**
 * Created by MMenem on 7/16/2017.
 */


public class UserInformation {

    private String name;
    private String phone;
    private String age;
    private String gender;

    public UserInformation() {

    }

    public UserInformation(String name, String phone, String age, String gender) {
        this.name = name;
        this.phone = phone;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
