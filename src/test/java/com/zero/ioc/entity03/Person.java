package com.zero.ioc.entity03;

public class Person {

    private String name;

    private Phone phone;

    public Person(String name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    public String hello(String friendName) {
        return name + phone.call(friendName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
