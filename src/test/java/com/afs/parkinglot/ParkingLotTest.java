package com.afs.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    void should_get_ticket_when_park_given_enough_paring_space_and_a_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);
        ParkingTicket parkingTicket = parkingLot.park(car);
        assertNotNull(parkingTicket);
        assertNotNull(parkingTicket.getCar());
    }
}
