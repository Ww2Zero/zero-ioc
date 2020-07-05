package com.zero.ioc.entity04;

public class Student {

    private String userName;
    private Phone phone;


    public String hello(String str) {
        return userName + " " + phone.getPhoneName() + " hello " + str;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "userName='" + userName + '\'' +
                ", phone=" + phone +
                '}';
    }
}
