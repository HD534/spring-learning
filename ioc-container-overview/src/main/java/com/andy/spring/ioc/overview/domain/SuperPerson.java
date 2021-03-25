package com.andy.spring.ioc.overview.domain;

import com.andy.spring.ioc.overview.annotation.Super;

@Super
public class SuperPerson extends Person{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperPerson{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
