package com.afs.parkinglot;

import java.util.Arrays;
import java.util.List;

public class SuperParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SuperParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot targetParkingLot = findParkingLotWithHighestAvailableRate();
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

    private ParkingLot findParkingLotWithHighestAvailableRate() {
        ParkingLot targetParkingLot = null;
        double maxAvailableRate = -1.0;

        for (ParkingLot parkingLot : parkingLots) {
            double availableRate = parkingLot.getAvailableRate();
            if (parkingLot.hasAvailablePosition() && availableRate > maxAvailableRate) {
                maxAvailableRate = availableRate;
                targetParkingLot = parkingLot;
            }
        }

        return targetParkingLot;
    }
}