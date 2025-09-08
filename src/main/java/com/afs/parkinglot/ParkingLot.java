package com.afs.parkinglot;

public class ParkingLot {
    private static final String NO_VALID_PARKING_SPACE_ERROR_MSG = "No available position";
    private int capacity;
    ParkingTicket park(Car car) {
        if(capacity <= 0) throw new RuntimeException(NO_VALID_PARKING_SPACE_ERROR_MSG);
        capacity--;
        return new ParkingTicket(car);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }
}
