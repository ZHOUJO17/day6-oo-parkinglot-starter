package com.afs.parkinglot;

import java.util.Objects;

public class ParkingTicket {
    private Car car;

    public ParkingTicket(Car car) {
        this.car = car;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(car.getLicenseNumber());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || !obj.getClass().equals(getClass())) return false;
        ParkingTicket parkingTicket = (ParkingTicket) obj;
        return car == parkingTicket.getCar();
    }

    public Car getCar() {
        return car;
    }
}
