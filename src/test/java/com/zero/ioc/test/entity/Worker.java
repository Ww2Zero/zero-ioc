package com.zero.ioc.test.entity;

import com.zero.ioc.test.entity.CGLIB.Person;

public class Worker extends Person {


    private String workId;

    public Worker(String workId) {
        this.workId = workId;
    }

    @Override
    public String hello(String name) {
        return workId + " is worker ," + name;
    }
}
