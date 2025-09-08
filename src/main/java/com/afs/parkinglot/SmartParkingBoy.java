package com.afs.parkinglot;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findTargetParkingLot() {
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