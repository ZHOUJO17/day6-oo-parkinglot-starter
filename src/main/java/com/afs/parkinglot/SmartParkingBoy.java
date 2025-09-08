package com.afs.parkinglot;

import java.util.Arrays;
import java.util.List;

public class SmartParkingBoy {
    private final List<ParkingLot> parkingLots;

    public SmartParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    public ParkingTicket park(Car car) {
        ParkingLot targetParkingLot = findParkingLotWithMostAvailablePositions();
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

    private ParkingLot findParkingLotWithMostAvailablePositions() {
        ParkingLot targetParkingLot = null;
        int maxAvailablePositions = -1;

        for (ParkingLot parkingLot : parkingLots) {
            int availablePositions = parkingLot.getAvailablePositions();
            if (availablePositions > 0 && availablePositions > maxAvailablePositions) {
                maxAvailablePositions = availablePositions;
                targetParkingLot = parkingLot;
            }
        }

        return targetParkingLot;
    }
}