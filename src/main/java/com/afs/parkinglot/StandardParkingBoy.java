package com.afs.parkinglot;

public class StandardParkingBoy {
    private ParkingLot parkingLot;
    ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLot.fetch(parkingTicket);
    }
}
