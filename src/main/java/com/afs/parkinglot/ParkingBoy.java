package com.afs.parkinglot;

import java.util.Arrays;
import java.util.List;

public abstract class ParkingBoy {
    protected final List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot targetParkingLot = findTargetParkingLot();
        if (targetParkingLot == null) {
            throw new RuntimeException("No available position.");
        }
        return targetParkingLot.park(car);
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

    protected abstract ParkingLot findTargetParkingLot();
}