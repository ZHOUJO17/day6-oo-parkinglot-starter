package com.afs.parkinglot;

public class StandardParkingBoy extends ParkingBoy {

    public StandardParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot findTargetParkingLot() {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasAvailablePosition()) {
                return parkingLot;
            }
        }
        return null;
    }
}
