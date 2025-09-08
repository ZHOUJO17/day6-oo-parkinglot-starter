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
    }

    @Test
    void should_get_a_car_when_fetch_given_valid_parking_ticket() {
        ParkingLot parkingLot = new ParkingLot(10);
        Car originalCar = new Car();
        ParkingTicket parkingTicket = parkingLot.park(originalCar);
        Car resultCar = parkingLot.fetch(parkingTicket);
        assertNotNull(resultCar);
        assertEquals(originalCar,resultCar);
    }

}
