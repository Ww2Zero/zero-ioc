package com.zero.ioc.entity03;

public class Phone {

    private String phoneName;

    public Phone(String brand) {
        this.phoneName = brand;
    }

    public String call(String name) {
        return "使用" + phoneName + "给" + name + "打电话";
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }
}
