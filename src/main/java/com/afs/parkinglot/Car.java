package com.afs.parkinglot;

import java.util.Objects;

public class Car {
    private String licenseNumber;

    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(licenseNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !obj.getClass().equals(getClass())) return false;
        Car car = (Car) obj;
        return licenseNumber.equals(car.getLicenseNumber());
    }
}
