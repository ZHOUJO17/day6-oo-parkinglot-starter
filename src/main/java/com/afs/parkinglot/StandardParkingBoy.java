package com.afs.parkinglot;

import java.util.Arrays;
import java.util.List;

public class StandardParkingBoy {
    private final List<ParkingLot> parkingLots;

    public StandardParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public ParkingTicket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (RuntimeException e) {
                // Continue to next parking lot if current one is full
            }
        }
        throw new RuntimeException("No available position.");
    }

    public Car fetch(ParkingTicket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.fetch(ticket);
            } catch (RuntimeException e) {
                // Continue to next parking lot if ticket not found in current one
            }
        }
        throw new RuntimeException("Unrecognized parking ticket.");
    }
}
