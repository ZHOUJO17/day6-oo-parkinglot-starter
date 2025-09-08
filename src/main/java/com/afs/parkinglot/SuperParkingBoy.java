package com.afs.parkinglot;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findTargetParkingLot() {
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